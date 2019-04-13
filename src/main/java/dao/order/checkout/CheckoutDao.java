//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao.order.checkout;

import bean.order.info.CheckoutBean;
import bean.order.info.Key;
import bean.order.res.CheckoutResult;
import dao.app.key.APIDao;
import dao.app.key.IKeyMapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import utils.database.DBUtil;

public class CheckoutDao {
    public CheckoutDao() {
    }

    public static CheckoutBean getCheckoutBeanByPPID(int ppid) {
        SqlSession session = DBUtil.getSession().openSession();
        ICheckoutMapper checkoutMapper = (ICheckoutMapper)session.getMapper(ICheckoutMapper.class);
        CheckoutBean bean = checkoutMapper.getCheckoutBeanByPPID(ppid);
        session.close();
        return bean;
    }

    public static CheckoutResult getCheckoutList(int uid) {
        SqlSession session = DBUtil.getSession().openSession();
        IKeyMapper keyMapper = (IKeyMapper)session.getMapper(IKeyMapper.class);
        ICheckoutMapper checkoutMapper = (ICheckoutMapper)session.getMapper(ICheckoutMapper.class);
        CheckoutResult result = new CheckoutResult();
        ArrayList<CheckoutBean> data = new ArrayList();
        APIDao.updateKeysUsageByUID(uid);
        List<Key> keys = keyMapper.getKeysByUID(uid);
        Iterator var7 = keys.iterator();

        while(var7.hasNext()) {
            Key key = (Key)var7.next();
            CheckoutBean bean = getCheckoutBeanByPPID(key.getPpid());
            if (bean == null) {
                System.out.println("Error- checkout bean is null");
            } else {
                bean.setAppKey(key.getAppKey());
                bean.setCount(key.getCount());
                bean.setSum((float)(key.getCount() / bean.getBatch()) * bean.getPrice());
                data.add(bean);
            }
        }

        result.setData(data);
        return result;
    }
}
