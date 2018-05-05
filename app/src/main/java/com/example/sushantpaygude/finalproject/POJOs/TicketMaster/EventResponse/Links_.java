
package com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links_ {

    @SerializedName("self")
    @Expose
    private Self_ self;

    public Self_ getSelf() {
        return self;
    }

    public void setSelf(Self_ self) {
        this.self = self;
    }

}
