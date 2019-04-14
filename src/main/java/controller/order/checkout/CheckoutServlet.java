//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package controller.order.checkout;

import bean.order.res.CheckoutResult;
import bean.user.info.User;
import dao.order.checkout.CheckoutDao;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.json.JsonUtil;

@WebServlet({"/getCheckoutInfo"})
public class CheckoutServlet extends HttpServlet {
    public CheckoutServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("userInfo");
        CheckoutResult result = null;
        if (user == null) {
            result = new CheckoutResult();
            result.onSelfException(403, "未登录，无法查询");
        } else {
            result = CheckoutDao.getCheckoutList(user.getUid());
            result.onSuccess("success");
        }

        JsonUtil.returnJson(response, result);
    }
}
