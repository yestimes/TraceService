//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dao.order.checkout;

import bean.order.info.CheckoutBean;
import bean.product.info.PayPackage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ICheckoutMapper {
    @Select({"SELECT ppid, batch, price FROM t_pay_package WHERE ppid = #{ppid}"})
    PayPackage getPayPackageByPpid(int var1);

    @Select({"SELECT t_product.prodname, t_product.prodpict_path, t_pay_package.batch, t_pay_package.price FROM t_product, t_pay_package WHERE t_product.pid = t_pay_package.pid and t_pay_package.ppid = #{ppid}"})
    CheckoutBean getCheckoutBeanByPPID(@Param("ppid") int var1);
}
