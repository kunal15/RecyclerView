package com.khatrigmail.kunal15.recyclerview;

/**
 * Created by Admin on 03-07-2016.
 */
public class MessageModel {
    private String personName;
    private String mobile;

    public MessageModel(String personName, String mobile) {
        this.personName = personName;
        this.mobile = mobile;
    }

    public MessageModel() {

    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
