
package com.example.sushantpaygude.finalproject.POJOs.ServerPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Users implements Serializable {

    @SerializedName("Success")
    @Expose
    private Integer success;
    @SerializedName("Message")
    @Expose
    private String message;

    @SerializedName("User")
    @Expose
    private String user;

    @SerializedName("EmailId")
    @Expose
    private String emailid;
    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
