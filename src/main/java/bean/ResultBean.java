package bean;

public class ResultBean {

    private int status;
    private String info;

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void onNotFound(){
        setStatus(404);
        setInfo("未找到，结果集为空");
    }

    public void onSuccess(String info){
        setStatus(200);
        setInfo(info);
    }

    public void  onFail(String info){
        setStatus(400);
        setInfo(info);
    }

    public void onSystemExp(String info)    {
        setStatus(500);
        setInfo(info);
    }

    public void onSelfException(int status, String info){
        setStatus(status);
        setInfo(info);
    }
}
