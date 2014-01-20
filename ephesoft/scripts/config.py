ORIGINAL = 'test.ephesoft.kumulus.sg'
ORIGINAL_PORT = 8080
PROXIED = 'localhost'
PROXIED_PORT = 80
USE_SSL = False

DB_HOST = 'test.ephesoft.kumulus.sg'
DB_PORT = 3306
DB_USER = 'root'
DB_PASS = '123456!'
DB_NAME = 'ephesoft'

def fq_original():
    return '%s:%s' % (ORIGINAL, ORIGINAL_PORT)

def fq_proxied():
    return '%s:%s' % (PROXIED, PROXIED_PORT)
