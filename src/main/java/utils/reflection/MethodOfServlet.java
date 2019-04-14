//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package utils.reflection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MethodOfServlet {
    public MethodOfServlet() {
    }

    public static void doMethodMapping(Object that, HttpServletRequest request, HttpServletResponse response) {
        String methodName = request.getParameter("method");
        Object resBean = null;
        Class clazz = that.getClass();

        try {
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(that, request, response);
        } catch (NoSuchMethodException var9) {
            var9.printStackTrace();

            try {
                response.sendError(404);
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        } catch (IllegalAccessException var10) {
            var10.printStackTrace();
        } catch (InvocationTargetException var11) {
            var11.printStackTrace();
        }

    }
}
