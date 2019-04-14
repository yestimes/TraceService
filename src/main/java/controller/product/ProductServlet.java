//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package controller.product;

import bean.product.res.ProductDetailResult;
import bean.product.res.ProductListResult;
import dao.product.ProductDao;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.argsCheck.StringChecker;
import utils.json.JsonUtil;
import utils.reflection.MethodOfServlet;

@WebServlet({"/product"})
public class ProductServlet extends HttpServlet {
    public ProductServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        MethodOfServlet.doMethodMapping(this, request, response);
    }

    public void toProductDetail(HttpServletRequest request, HttpServletResponse response) {
        String pid = request.getParameter("pid");
        ProductDetailResult result = ProductDao.getProductDetail(pid);
        request.getSession().setAttribute("prod-detail-res", result);

        try {
            response.sendRedirect("product-detail.html");
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void getProductList(HttpServletRequest request, HttpServletResponse response) {
        ProductListResult result = ProductDao.getProdctList();
        JsonUtil.returnJson(response, result);
    }

    public void getProductDetail(HttpServletRequest request, HttpServletResponse response) {
        String pid = request.getParameter("pid");
        ProductDetailResult result = null;
        if (StringChecker.isStringBlank(pid)) {
            result = (ProductDetailResult)request.getSession().getAttribute("prod-detail-res");
        } else {
            result = ProductDao.getProductDetail(pid);
        }

        JsonUtil.returnJson(response, result);
    }
}
