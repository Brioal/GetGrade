package com.brioal;

/**
 * Created by null on 15-11-4.



 */
public class Info {
    private String url ;
    private String cookie ;
    private String data ;

    public Info() {
    }

    public Info(String url, String cookie, String data) {
        this.url = url;
        this.cookie = cookie;
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
