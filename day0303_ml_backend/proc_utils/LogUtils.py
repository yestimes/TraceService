#coding:utf-8

import time

def get_time_now():
    return '[' + time.strftime('%Y.%m.%d', time.localtime(time.time())) + ']'