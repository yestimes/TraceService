#coding:utf-8
import redis
import json

class RedisHelper(object):
    def __init__(self, conn_pool):
        self.__conn = redis.Redis(connection_pool=conn_pool)#连接Redis

    def publish(self, channel, msg):#定义发布方法
        self.__conn.publish( channel, msg)
        return True

    def subscribe(self, channel):#定义订阅方法
        pub = self.__conn.pubsub()
        pub.subscribe(channel)
        pub.parse_response()
        return pub

    def push_res(self, key_name, key_value):
        '''
        将结果写回redis
        :param key_name:  任务流水号
        :param key_value: 返回的预测结果
        :return:           执行状态
        '''
        try:
                self.__conn.set(key_name, key_value, ex=1)
        except:
                return -1

        return 0

    def pop_args(self, key_name):
        '''
        获取预测需要的参数
        :param key_name: 任务流水号
        :return: 成功则包含参数的Json或者
        '''

        if not self.__conn.exists(key_name):
            return 'null'
        else:
            return self.__conn.get(key_name)





