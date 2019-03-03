package bean.user.result;

public class UserResult {
    private int status;
    private String info;
    private String url;


    public void onSuccess(String info, String url){
        setStatus(200);
        setInfo(info);
        setUrl(url);
    }

    public void onMatchWrong(String info, String url){
        setStatus(403);
        setInfo(info);
        setUrl(url);
    }

    public void onFail(int status, String info, String url){
        setStatus(status);
        setInfo(info);
        setUrl(url);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
