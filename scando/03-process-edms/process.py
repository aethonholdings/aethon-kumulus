#!/usr/bin/python

PATH = '/mayan-stage'
TEMP = '/mnt/tmp'
DEBUG = True

import os, subprocess, shlex, time, sys, traceback, shutil, hashlib, zipfile

class ConvertException(Exception): pass

def log(s): print s

def e2s(e):
    exc_type, exc_obj, exc_tb = sys.exc_info()
    trace = ' -> '.join(['%s:%s' % (f.split('\\')[-1:][0], l)
                         for (f, l, m, c) in traceback.extract_tb(exc_tb)])
    return '%s: %s [%s]' % (type(e).__name__, str(e), trace)

def valid(file):
    if file.startswith('.') and file != '.0':
        try:
            int(file[1:])
        except ValueError:
            return False
        else:
            return True
    else:
        return False

def cmdexec(cmd, dirname='.'):
    log(cmd)
    if DEBUG:
        p = subprocess.Popen(shlex.split(cmd), cwd=dirname)
    else:
        p = subprocess.Popen(shlex.split(cmd), cwd=dirname,
                             stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    if p.wait() != 0:
        raise ConvertException(p.returncode)

def loop():
    for dirname, dirnames, filenames in os.walk(PATH):
        if '.0' in filenames:
            name = hashlib.sha1(dirname).hexdigest()
            if os.path.exists('%s/%s' % (TEMP, name)):
                if DEBUG:
                    continue
                else:
                    shutil.rmtree('%s/%s' % (TEMP, name))
            os.mkdir('%s/%s' % (TEMP, name))
            _s = '%s/searchable.pdf' % dirname
            if os.path.exists(_s):
                os.remove(_s)
            log('==> %s' % dirname)
            f = [int(file[1:]) for file in filenames if valid(file)]
            f.sort()
            f = ['.'+str(file) for file in f]
            with open(dirname+'/.all', 'w') as file:
                for i in f:
                    file.write(i+"\r\n")
            cmdexec('convert @.all raw.pdf', dirname)
            try:
                cmdexec('./pdfocr.rb -k -w %s -t -i "%s/raw.pdf" '
                        '-o "%s/searchable.pdf"' % ('%s/%s' % (TEMP, name),
                                                    dirname, dirname))
                if not os.path.exists("%s/searchable.pdf" % dirname):
                    raise ConvertException('File not found')
            except Exception, e:
                log('Warning: %s' % e2s(e))
                log('==> Could not create searchable pdf')
                cmdexec('ln -s raw.pdf searchable.pdf', dirname)
            if not os.path.exists("%s/%s.pdf" % (dirname, name)):
                cmdexec('ln -s searchable.pdf "%s.pdf"' % name, dirname)
            with zipfile.ZipFile('%s/searchable.zip' % dirname, 'w') as myzip:
                myzip.write("%s/%s.pdf" % (dirname, name))
            with open('%s/.comment' % dirname, 'r') as cfile:
                cmdexec('./manage.py bulk_upload --noinput '
                        "--metadata '{\"comment\": \"%s\", \"path\": \"%s\"}' "
                        '"%s/searchable.zip"'
                        % (cfile.read(), dirname, dirname))
            os.remove('%s/searchable.zip' % dirname)
            os.remove('%s/.0' % dirname)

if __name__ == '__main__':
    while True:
        try:
            if not DEBUG and os.path.exists(TEMP):
                shutil.rmtree(TEMP)
            if not os.path.exists(TEMP):
                os.mkdir(TEMP)
            loop()
        except Exception, e:
            log(e2s(e))
        finally:
            time.sleep(10)
