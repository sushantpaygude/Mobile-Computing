
package com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Twitter {

    @SerializedName("handle")
    @Expose
    private String handle;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

}
