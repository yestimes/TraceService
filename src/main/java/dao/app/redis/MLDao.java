//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao.app.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.RedisURI.Builder;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

public class MLDao {
    public MLDao() {
    }

    public static void pushTash(String channel, String order, String trace) {
        RedisURI uri = Builder.redis("localhost").withDatabase(0).build();
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisPubSubConnection<String, String> connection = redisClient.connectPubSub();
        RedisPubSubCommands<String, String> sync = connection.sync();
        sync.publish(channel, order);
        sync.set(order, trace);
    }

    public static int getTaskResult(String order) {
        return 0;
    }
}
