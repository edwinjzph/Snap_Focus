package com.elyzians.edwinjzph;

public class post {
    String discription;
    String type;
    String url;
    String youtubeurl;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getYoutubeurl() {
        return this.youtubeurl;
    }

    public void setYoutubeurl(String str) {
        this.youtubeurl = str;
    }

    public String getDiscription() {
        return this.discription;
    }

    public void setDiscription(String str) {
        this.discription = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public post() {
    }

    public post(String str, String str2, String str3, String str4) {
        this.discription = str;
        this.url = str2;
        this.type = str3;
        this.youtubeurl = str4;
    }
}
