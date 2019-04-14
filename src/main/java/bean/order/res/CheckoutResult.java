//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package bean.order.res;

import bean.ResultBean;
import bean.order.info.CheckoutBean;
import java.util.List;

public class CheckoutResult extends ResultBean {
    private List<CheckoutBean> data;

    public CheckoutResult() {
    }

    public List<CheckoutBean> getData() {
        return this.data;
    }

    public void setData(List<CheckoutBean> data) {
        this.data = data;
    }

    public void onSelfException(int status, String info) {
        super.onSelfException(status, info);
    }

    public void onSystemExp(String info) {
        super.onSystemExp(info);
    }

    public void onNotFound() {
        super.onNotFound();
    }

    public void onSuccess(String info) {
        super.onSuccess(info);
    }

    public void onFail(String info) {
        super.onFail(info);
    }
}
