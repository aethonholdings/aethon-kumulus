import shelve, threading

def uni2str(u):
    return '1' + u.encode('utf-8') if isinstance(u, unicode) else '0' + u

def str2uni(s):
    return s[1:].decode('utf-8') if s[0] == '1' else s[1:]

class CShelve(object):
    def __init__(self, filename, flag, autosync):
        self.s = shelve.open(filename, flag)
        self.m = threading.Lock()
        self.autosync = autosync
    def keys(self):
        with self.m:
            return [str2uni(key) for key in self.s.keys()]
    def __getitem__(self, key):
        with self.m:
            return self.s[uni2str(key)]
    def __setitem__(self, key, value):
        with self.m:
            self.s[uni2str(key)] = value
            if self.autosync:
                self.s.sync()
    def __delitem__(self, key):
        with self.m:
            del self.s[uni2str(key)]
            if self.autosync:
                self.s.sync()
    def __len__(self):
        with self.m:
            return len(self.s)
    def has_key(self, key):
        with self.m:
            return uni2str(key) in self.s
    def sync(self):
        with self.m:
            self.s.sync()
    def close(self):
        with self.m:
            self.s.close()
    def __enter__(self):
        return self
    def __exit__(self, type, value, traceback):
        self.close()
