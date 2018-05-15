
package com.example.sushantpaygude.finalproject.POJOs.ServerPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServerRegistration implements Serializable {

    @SerializedName("users")
    @Expose
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
