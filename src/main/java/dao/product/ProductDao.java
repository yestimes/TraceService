//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao.product;

import bean.product.res.ProductDetailResult;
import bean.product.res.ProductListResult;
import org.apache.ibatis.session.SqlSession;
import utils.argsCheck.StringChecker;
import utils.database.DBUtil;

public class ProductDao {
    public ProductDao() {
    }

    public static ProductListResult getProdctList() {
        ProductListResult result = new ProductListResult();
        SqlSession session = DBUtil.getSession().openSession();
        IProductMapper productMapper = (IProductMapper)session.getMapper(IProductMapper.class);
        result.setData(productMapper.getProductList());
        session.close();
        return result;
    }

    public static ProductDetailResult getProductDetail(String pid) {
        ProductDetailResult result = new ProductDetailResult();
        if (StringChecker.isStringBlank(pid)) {
            result.onSelfException(403, "pid为空");
        } else {
            SqlSession session = DBUtil.getSession().openSession();
            IProductMapper productMapper = (IProductMapper)session.getMapper(IProductMapper.class);
            result.setData(productMapper.getProductDetail(pid));
            result.getData().setPid(Integer.parseInt(pid));
            session.close();
        }

        return result;
    }
}
