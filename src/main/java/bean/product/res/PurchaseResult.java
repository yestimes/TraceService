//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package bean.product.res;

import bean.ResultBean;

public class PurchaseResult extends ResultBean {
    private String key;
    private String url;

    public PurchaseResult() {
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void onFail(String info) {
        super.onFail(info);
    }

    public void onSuccess(String info) {
        super.onSuccess(info);
    }

    public void onNotFound() {
        super.onNotFound();
    }

    public void onSystemExp(String info) {
        super.onSystemExp(info);
    }

    public void onSelfException(int status, String info) {
        super.onSelfException(status, info);
    }
}
