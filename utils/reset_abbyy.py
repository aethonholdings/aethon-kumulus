import requests
import xml.etree.ElementTree as et
import webbrowser

username = 'Bucephalus'
password = 'A08p7kFT1HNA+3eurpn4Xb94'
list_url = 'https://%s%s%s@cloud.ocrsdk.com/listTasks'

proxy = requests.Session()
proxy.auth = (username, password)
proxy.verify = True

webbrowser.open(list_url % (username, ':', password))

ls = proxy.get(list_url % ('','',''))

for task in [task.attrib['id'] for task in et.fromstring(ls.text)._children]:
    print proxy.get('https://cloud.ocrsdk.com/deleteTask?taskId='+task).status_code
