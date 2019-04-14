//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao.product;

import bean.product.info.Product;
import bean.product.info.ProductDetail;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface IProductMapper {
    @Select({"select * from t_product"})
    List<Product> getProductList();

    @Select({"select title, t_product.prodname, pict, descr, detail from t_prodinfo, t_product where t_prodinfo.pid = #{pid} and t_prodinfo.pid  = t_product.pid "})
    ProductDetail getProductDetail(String var1);
}
