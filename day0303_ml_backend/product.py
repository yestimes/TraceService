#coding:utf-8

import pandas as pd
import scipy as sp
from sklearn.externals.joblib import Parallel, delayed
import os

import time
import redis
import numpy as np
import lightgbm as lgb

from proc_utils.LogUtils import get_time_now
from proc_utils.RedisDao import RedisHelper

cache = 'model'
model_name = 'gbdt.clf'


def get_feature(traceStr):
    points = []

    for point in traceStr[:-1].split(";"):
        point = point.split(",")
        points.append(((float(point[0]), float(point[1])), float(point[2])))

    xs = pd.Series([point[0][0] for point in points])
    ys = pd.Series([point[0][1] for point in points])

    distance_deltas = pd.Series(
        [sp.spatial.distance.euclidean(points[i][0], points[i + 1][0]) for i in range(len(points) - 1)])

    time_deltas = pd.Series([points[i + 1][1] - points[i][1] for i in range(len(points) - 1)])
    xs_deltas = xs.diff(1)
    ys_deltas = ys.diff(1)

    speeds = pd.Series(
        [np.log1p(distance) - np.log1p(delta) for (distance, delta) in zip(distance_deltas, time_deltas)])
    angles = pd.Series(
        [np.log1p((points[i + 1][0][1] - points[i][0][1])) - np.log1p((points[i + 1][0][0] - points[i][0][0])) for i in
         range(len(points) - 1)])

    speed_diff = speeds.diff(1).dropna()
    angle_diff = angles.diff(1).dropna()

    count = len(traceStr.split(";"))

    speed_diff_median = speed_diff.median()

    speed_diff_mean = speed_diff.mean()

    speed_diff_var = speed_diff.var()

    speed_diff_max = speed_diff.max()

    angle_diff_var = angle_diff.var()

    time_delta_min = time_deltas.min()

    time_delta_max = time_deltas.max()

    time_delta_var = time_deltas.var()

    distance_deltas_max = distance_deltas.max()

    mean_speed = speeds.mean()
    median_speed = speeds.median()
    var_speed = speeds.var()

    max_angle = angles.max()
    var_angle = angles.var()

    kurt_angle = angles.kurt()

    y_min = ys.min()

    y_max = ys.max()
    y_var = ys.var()

    x_min = xs.min()

    x_max = xs.max()
    x_var = xs.var()

    x_back_num = min((xs.diff(1).dropna() > 0).sum(), (xs.diff(1).dropna() < 0).sum())
    y_back_num = min((ys.diff(1).dropna() > 0).sum(), (ys.diff(1).dropna() < 0).sum())

    xs_delta_var = xs_deltas.var()

    xs_delta_max = xs_deltas.max()
    xs_delta_min = xs_deltas.min()

    feset = [count, speed_diff_median, speed_diff_mean, speed_diff_var,
             speed_diff_max, angle_diff_var, time_delta_min, time_delta_max,
             time_delta_var, distance_deltas_max, mean_speed, median_speed,
             var_speed, max_angle, var_angle, kurt_angle, y_min, y_max,
             y_var, x_min, x_max, x_var, x_back_num, y_back_num,
             xs_delta_var, xs_delta_max, xs_delta_min]

    return feset



dump_file = os.path.join(cache, "gbdt.clf")
gbm = ''
if os.path.exists(dump_file):
     gbm = lgb.Booster(model_file=dump_file)
     print(get_time_now() + ' Load model[file: %s]' % dump_file)
else:
    print("Model not exist")
    import sys
    sys.exit(-1)

def is_human(feature_set):
    fs = np.array([feature_set])
    return gbm.predict(fs)[0]



if __name__ == '__main__':

        # trace_rev = '192,2542,184;206,2568,214;213,2568,226;248,2594,301;290,2594,358;346,2594,436;409,2594,445;430,2594,484;465,2620,535;472,2620,580;479,2620,652;493,2620,685;521,2620,772;528,2620,793;556,2620,844;598,2620,880;633,2620,937;661,2620,988;682,2620,1048;689,2620,1252;703,2633,1282;717,2633,1330;738,2633,1396;752,2633,1435;766,2633,1477;787,2633,1543;829,2633,1573;892,2633,1624;941,2633,1672;969,2633,1786;1011,2633,1801;1039,2633,1840;1074,2633,1894;1095,2620,1945;1102,2620,1984;1109,2620,2047;1130,2620,2083;1172,2620,2131;1242,2620,2179;1312,2620,2239;1396,2620,2302;1452,2620,2344;1466,2620,2386;1501,2620,2440;1515,2620,2482;1529,2620,2548;1543,2607,2584;1613,2607,2653;1641,2607,2680;1683,2581,2746;1704,2581,2785;1711,2581,2833;1711,2581,4645;1711,2568,5092;'

        # print("Res: " + str(is_human(trace_rev)))

        # redis连接
        redis_conf_dict = {'host': '127.0.0.1', 'port': 6379}

        conn_pool = redis.ConnectionPool(host=redis_conf_dict['host'], port=redis_conf_dict['port'])
        conn = redis.Redis(connection_pool=conn_pool)

        redis_util = RedisHelper(conn_pool)

        #连接Redis

        print("Ready for task ")
        while True:

            redis_sub = redis_util.subscribe('taskChannel')
            msg = redis_sub.parse_response()

            print(get_time_now() + 'Recive task :', msg[2])

            # args is String
            # trace_args = conn.get(msg[2])
            trace_args = str(redis_util.pop_args(msg[2]))

            # redis 过来的数据： b'....'
            print("Trace: ", trace_args[2: -1])

            # calculate time cost
            start = time.clock()

            feature_vector = get_feature(trace_args[2: -1])
            res = is_human(feature_vector)


            elapsed = (time.clock() - start)
            print("Predict Time used:", elapsed)

            print(get_time_now(), " task_id", msg[2], 'predicted :', res)

            # debug

            # result push back
            # redis_util.push_res("D" + str(msg[2])[2:-1], detect)

            # 容忍到90%
            conn.set("D" + str(msg[2])[2:-1], int(res + 0.1))

            conn.publish("resChannel", "D" + str(msg[2])[2:-1])


