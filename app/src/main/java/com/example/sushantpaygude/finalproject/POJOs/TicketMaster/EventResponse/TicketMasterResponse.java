package com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse;;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pankaj on 4/29/18.
 */

public class TicketMasterResponse {
    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;
    @SerializedName("_links")
    @Expose
    private Links___ links ;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Links___ getLinks() {
        return links;
    }

    public void setLinks(Links___ links) {
        this.links = links;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @SerializedName("page")
    @Expose

    private Page page;
}
