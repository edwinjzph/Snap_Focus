package com.elyzians.edwinjzph;

public class userrequest {
    String Email;
    String Phone;
    String Service;

    public String getService() {
        return this.Service;
    }

    public void setService(String str) {
        this.Service = str;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String str) {
        this.Email = str;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String str) {
        this.Phone = str;
    }

    public userrequest() {
    }

    public userrequest(String str, String str2, String str3) {
        this.Service = str;
        this.Email = str2;
        this.Phone = str3;
    }
}
