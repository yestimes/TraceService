package controller.user;

import bean.user.info.User;
import bean.user.result.UserResult;
import dao.user.UserDao;
import utils.json.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@WebServlet(value = {"/user"})
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String methodName = request.getParameter("method");

        Object resBean = null;
        Class clazz = this.getClass();
        try {
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            resBean = method.invoke(this, request, response);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void userLogin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserResult result = UserDao.userLogin(user);
        if (result.getStatus() == 200){
            request.getSession().setAttribute("userInfo", user);
        }else {
            System.out.printf("Fail [username:%s, password: %s  ]\n", username, password);
        }

        JsonUtil.returnJson(response, result);

    }


}
