//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package controller.app.transfer;

import bean.app.res.TraceValidateResult;
import dao.app.key.APIDao;
import dao.app.redis.MLDao;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.RandomStringUtils;
import utils.json.JsonUtil;

@WebServlet({"/traceValidate"})
public class MLServiceServlet extends HttpServlet {
    public MLServiceServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int dataLength = Integer.parseInt(request.getParameter("dataLength"));
        String traceDataString = request.getParameter("traceData");
        String key = request.getParameter("key");
        System.out.println("Key: " + key);
        TraceValidateResult result = new TraceValidateResult();
        APIDao.usageCount(result, key);
        if (result.getStatus() == 200) {
            long milisec = Long.parseLong(request.getParameter("time"));
            if (!result.dataCompleteCheck(request, request.getParameter("number"), new Date(milisec))) {
                result.setIsMachine(false);
            } else {
                String orderSn = RandomStringUtils.randomAlphanumeric(16);
                MLDao.pushTash("tackChannel", orderSn, traceDataString);
                int flag = MLDao.getTaskResult("R" + orderSn);
                if (flag > 0) {
                    result.onHuman("pass");
                } else {
                    result.onMachine();
                }
            }
        } else {
            System.out.printf("Set Wrong, error code: %d, key: %s;\n", result.getStatus(), key);
        }

        JsonUtil.returnJson(response, result);
    }
}
