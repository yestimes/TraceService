//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package bean.product.res;

import bean.ResultBean;
import bean.product.info.Product;
import java.util.List;

public class ProductListResult extends ResultBean {
    private List<Product> data;

    public ProductListResult() {
    }

    public List<Product> getData() {
        return this.data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public void onSuccess(String info) {
        super.onSuccess(info);
    }

    public void onFail(String info) {
        super.onFail(info);
    }
}
