//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao.order.purchase;

import org.apache.ibatis.session.SqlSession;
import utils.database.DBUtil;

public class PurchaseDao {
    public PurchaseDao() {
    }

    public static int getPPID(int pid) {
        SqlSession session = DBUtil.getSession().openSession();
        IPurchaseMapper purchaseMapper = (IPurchaseMapper)session.getMapper(IPurchaseMapper.class);
        int ppid = purchaseMapper.getPPID(pid);
        session.close();
        return ppid;
    }

    public static int keyBackup(int uid, int ppid, String appkey) {
        SqlSession session = DBUtil.getSession().openSession();
        IPurchaseMapper purchaseMapper = (IPurchaseMapper)session.getMapper(IPurchaseMapper.class);
        int ret = purchaseMapper.backupKey(uid, ppid, appkey);
        session.commit();
        session.close();
        return ret;
    }

    public static boolean isKeyExist(int uid, int ppid) {
        SqlSession session = DBUtil.getSession().openSession();
        IPurchaseMapper purchaseMapper = (IPurchaseMapper)session.getMapper(IPurchaseMapper.class);
        int ret = purchaseMapper.getKeysCount(uid, ppid);
        session.close();
        return ret > 0;
    }
}
