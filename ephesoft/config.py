ORIGINAL = 'test.ephesoft.kumulus.sg'
ORIGINAL_PORT = 8080
PROXIED = 'localhost'
PROXIED_PORT = 80
USE_SSL = False
STATE_DB = 'state.shelve'

DB_HOST = 'kumulus.cokd1jwuhqlu.ap-southeast-1.rds.amazonaws.com'
DB_PORT = 3306
DB_USER = 'kumulus'
DB_PASS = 'd7!8d826ddx1'
DB_NAME = 'kumulus'

def fq_original():
    return '%s:%s' % (ORIGINAL, ORIGINAL_PORT)

def fq_proxied():
    return '%s:%s' % (PROXIED, PROXIED_PORT)
