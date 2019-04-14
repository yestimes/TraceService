//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package controller.app.conf;

import bean.app.res.PictConfResult;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.json.JsonUtil;
import utils.reflection.MethodOfServlet;

@WebServlet({"/pictConf"})
public class PictureConfigServlet extends HttpServlet {
    public PictureConfigServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        MethodOfServlet.doMethodMapping(this, request, response);
    }

    public void getInitConf(HttpServletRequest request, HttpServletResponse response) {
        PictConfResult result = new PictConfResult();
        request.getSession().setAttribute(result.getNumber(), result.getTime());
        JsonUtil.returnJson(response, result);
    }
}
