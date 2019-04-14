//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package bean.product.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Product {
    private int pid;
    private String prodname;
    private String prodpict_path;
    @JsonIgnoreProperties
    private String prodservice;

    public Product() {
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProdname() {
        return this.prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getProdpict_path() {
        return this.prodpict_path;
    }

    public void setProdpict_path(String prodpict_path) {
        this.prodpict_path = prodpict_path;
    }

    public String getProdservice() {
        return this.prodservice;
    }

    public void setProdservice(String prodservice) {
        this.prodservice = prodservice;
    }
}
