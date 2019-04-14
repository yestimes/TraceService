//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package bean.app.res;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TraceValidateResult {
    private int status;
    private String info;
    private boolean flag;
    private boolean isMachine;

    public TraceValidateResult() {
    }

    public void onService(int status, String info) {
        this.setStatus(status);
        this.setInfo(info);
    }

    public void onKeyWrong() {
        this.setStatus(403);
        this.setInfo("eject");
        this.setFlag(false);
        this.setIsMachine(true);
    }

    public void onKeyNotExist() {
        this.setStatus(403);
        this.setInfo("API key not exist");
        this.setFlag(false);
        this.setIsMachine(true);
    }

    public void onSelfException(int status, String info) {
        this.setInfo(info);
        this.setStatus(status);
        this.setFlag(false);
        this.setIsMachine(false);
    }

    public void onCountLimit() {
        this.setStatus(403);
        this.setInfo("使用次数已用完，请等待下一分钟");
        this.setFlag(false);
        this.setIsMachine(true);
    }

    public void onMachine() {
        this.setStatus(200);
        this.setInfo(this.info);
        this.setFlag(true);
        this.setIsMachine(true);
    }

    public void onHuman(String info) {
        this.setStatus(200);
        this.setInfo(info);
        this.setFlag(true);
        this.setIsMachine(false);
    }

    public boolean dataCompleteCheck(HttpServletRequest request, String numner, Date dataTome) {
        boolean res = false;
        HttpSession session = request.getSession();
        Date preParedTime = (Date)session.getAttribute(numner);
        res = dataTome.equals(preParedTime);
        this.setFlag(res);
        return res;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isFlag() {
        return this.flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isMachine() {
        return this.isMachine;
    }

    public void setIsMachine(boolean machine) {
        this.isMachine = machine;
    }
}
