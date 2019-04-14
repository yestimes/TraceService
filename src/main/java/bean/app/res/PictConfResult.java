//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package bean.app.res;

import java.util.Date;

public class PictConfResult {
    private Date time;
    private String number;
    private String imgSrc;
    private double x;
    private double y;

    public PictConfResult() {
        int qX = 400;
        int qY = 250;
        int s = 50;
        this.setTime(new Date());
        this.setNumber("0000000" + 1.0E8D * Math.random());
        this.setImgSrc("assets/img/instance/rc-" + (int)(Math.random() * 70.0D + 1.0D) + ".jpeg");
        this.setX(Math.random() * ((double)qX - 3.5D * (double)s) + (double)(2 * s));
        this.setY(Math.random() * (double)(qY - 15 - 25 - s) + 15.0D);
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
