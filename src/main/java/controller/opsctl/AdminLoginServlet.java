//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package controller.opsctl;

import bean.user.info.User;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginServlet extends HttpServlet {
    public AdminLoginServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        User info = (User)request.getSession().getAttribute("userInfo");
        if (info == null) {
            try {
                response.sendRedirect("/login.html");
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

    }
}
