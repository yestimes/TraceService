//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao.app.key;

import bean.app.res.KeyUsage;
import bean.app.res.TraceValidateResult;
import bean.order.info.Key;
import bean.order.info.KeysOrder;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.RedisURI.Builder;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import java.util.Iterator;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import utils.argsCheck.StringChecker;
import utils.database.DBUtil;

public class APIDao {
    private static RedisURI uri = Builder.redis("localhost").withDatabase(0).build();

    public APIDao() {
    }

    public static TraceValidateResult usageCount(TraceValidateResult result, String key) {
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        long total = 1L;

        try {
            if (syncCommands.get(key) == null) {
                KeyUsage usage = getKeyUsage(key);
                if (usage == null) {
                    System.out.printf("Key %s Not exist in MySQL ", key);
                    syncCommands.setex(key, 300L, "-1");
                    result.onSelfException(404, "API Key不存在");
                } else {
                    syncCommands.set(usage.getAppKey(), usage.getCount() + 1 + "");
                    result.onService(200, "in service");
                }
            } else {
                total = (long)Integer.parseInt((String)syncCommands.get(key));
                if (total == -1L) {
                    result.onSelfException(403, "该Key无效，请联系网站管理员更换");
                    System.out.printf("Key %s is invalidated and used too often", key);
                } else {
                    long value = syncCommands.incr(key);
                    result.onService(200, "in service");
                    System.out.printf("Key %s In service, usage: %d", key, value);
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
            System.out.println("流量控制组件:执行计数操作失败,无法执行计数");
        } finally {
            connection.close();
        }

        return result;
    }

    public static KeyUsage getKeyUsage(String key) {
        SqlSession session = DBUtil.getSession().openSession();
        IKeyMapper keyMapper = (IKeyMapper)session.getMapper(IKeyMapper.class);
        KeyUsage usage = keyMapper.getKeyUsage(key);
        session.close();
        return usage;
    }

    public static void updateKeysUsageByUID(int uid) {
        SqlSession session = DBUtil.getSession().openSession();
        IKeyMapper keyMapper = (IKeyMapper)session.getMapper(IKeyMapper.class);
        new KeysOrder();
        List<Key> keys = keyMapper.getKeysByUID(uid);
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        Iterator var8 = keys.iterator();

        while(var8.hasNext()) {
            Key key = (Key)var8.next();
            String value = (String)syncCommands.get(key.getAppKey());
            if (!StringChecker.isStringBlank(value)) {
                System.out.printf("Key: %s, value: %s \n", key.getAppKey(), value);
                keyMapper.updateAPIUsage(Integer.parseInt(value), uid, key.getAppKey());
            }
        }

        session.commit();
        session.close();
    }
}
