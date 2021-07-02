package com.elyzians.edwinjzph;

public class uploadinfo {
    public String Address;
    public String Date;
    public String Phonenumber;
    public String Potrait;
    public String amount;
    public String f392TR;
    public String imageName;
    public String imageURL;
    public String status;

    public uploadinfo() {
    }

    public void setImageURL(String str) {
        this.imageURL = str;
    }

    public uploadinfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.imageName = str;
        this.imageURL = str2;
        this.Date = str4;
        this.Address = str3;
        this.Phonenumber = str6;
        this.Potrait = str5;
        this.status = str7;
        this.amount = str8;
        this.f392TR = str9;
    }

    public String getImageName() {
        return this.imageName;
    }

    public String getImageURL() {
        return this.imageURL;
    }
}
