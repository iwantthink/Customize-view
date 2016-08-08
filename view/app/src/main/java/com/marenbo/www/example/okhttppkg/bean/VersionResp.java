package com.marenbo.www.example.okhttppkg.bean;

/**
 * Created by Administrator on 2016/7/24.
 */
public class VersionResp {

    private String status;

    private String msg;

    private String url;

    private String latest;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    @Override
    public String toString() {
        return "VersionResp{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                ", latest='" + latest + '\'' +
                '}';
    }
}
