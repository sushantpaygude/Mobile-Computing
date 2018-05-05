
package com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingEvents_ {

    @SerializedName("_total")
    @Expose
    private Integer total;
    @SerializedName("tmr")
    @Expose
    private Integer tmr;
    @SerializedName("mfx-nl")
    @Expose
    private Integer mfxNl;
    @SerializedName("mfx-de")
    @Expose
    private Integer mfxDe;
    @SerializedName("ticketmaster")
    @Expose
    private Integer ticketmaster;
    @SerializedName("mfx-dk")
    @Expose
    private Integer mfxDk;
    @SerializedName("mfx-es")
    @Expose
    private Integer mfxEs;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTmr() {
        return tmr;
    }

    public void setTmr(Integer tmr) {
        this.tmr = tmr;
    }

    public Integer getMfxNl() {
        return mfxNl;
    }

    public void setMfxNl(Integer mfxNl) {
        this.mfxNl = mfxNl;
    }

    public Integer getMfxDe() {
        return mfxDe;
    }

    public void setMfxDe(Integer mfxDe) {
        this.mfxDe = mfxDe;
    }

    public Integer getTicketmaster() {
        return ticketmaster;
    }

    public void setTicketmaster(Integer ticketmaster) {
        this.ticketmaster = ticketmaster;
    }

    public Integer getMfxDk() {
        return mfxDk;
    }

    public void setMfxDk(Integer mfxDk) {
        this.mfxDk = mfxDk;
    }

    public Integer getMfxEs() {
        return mfxEs;
    }

    public void setMfxEs(Integer mfxEs) {
        this.mfxEs = mfxEs;
    }

}
