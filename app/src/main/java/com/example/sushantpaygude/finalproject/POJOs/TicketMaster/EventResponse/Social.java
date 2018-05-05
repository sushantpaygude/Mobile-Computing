
package com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Social {

    @SerializedName("twitter")
    @Expose
    private Twitter twitter;

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

}
