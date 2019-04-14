//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package controller.order.purchase;

import bean.product.res.ProductDetailResult;
import bean.product.res.PurchaseResult;
import bean.user.info.User;
import dao.order.purchase.PurchaseDao;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.RandomStringUtils;
import utils.json.JsonUtil;

@WebServlet({"/purchase"})
public class PurchaseServlet extends HttpServlet {
    public PurchaseServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        PurchaseResult result = new PurchaseResult();
        User userInfo = (User)request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            result.onSelfException(403, "没有登录");
        } else {
            ProductDetailResult detailCache = (ProductDetailResult)request.getSession().getAttribute("prod-detail-res");
            if (detailCache == null) {
                result.onSelfException(400, "请在商品详情页提交开通请求");
            } else {
                int uid = userInfo.getUid();
                int pid = detailCache.getData().getPid();
                int ppid = PurchaseDao.getPPID(pid);
                if (PurchaseDao.isKeyExist(uid, ppid)) {
                    result.onSelfException(400, "已有密钥，无需重复开通");
                } else {
                    String appKey = RandomStringUtils.randomAlphanumeric(16);
                    System.out.println("AppKeys: " + appKey);
                    System.out.printf("User id : %d; Product id : %d; PayPackage id : %d\n", uid, pid, ppid);
                    int ret = PurchaseDao.keyBackup(uid, ppid, appKey);
                    result.setKey(appKey);
                    result.onSuccess("您的密钥获取完毕");
                    System.out.println("Purchase done, res : " + ret);
                }
            }
        }

        JsonUtil.returnJson(response, result);
    }
}
