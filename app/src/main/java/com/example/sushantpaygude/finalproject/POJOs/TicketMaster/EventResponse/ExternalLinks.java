
package com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalLinks {

    @SerializedName("youtube")
    @Expose
    private List<Youtube> youtube = null;
    @SerializedName("twitter")
    @Expose
    private List<Twitter_> twitter = null;
    @SerializedName("facebook")
    @Expose
    private List<Facebook> facebook = null;
    @SerializedName("instagram")
    @Expose
    private List<Instagram> instagram = null;
    @SerializedName("musicbrainz")
    @Expose
    private List<Musicbrainz> musicbrainz = null;

    public List<Youtube> getYoutube() {
        return youtube;
    }

    public void setYoutube(List<Youtube> youtube) {
        this.youtube = youtube;
    }

    public List<Twitter_> getTwitter() {
        return twitter;
    }

    public void setTwitter(List<Twitter_> twitter) {
        this.twitter = twitter;
    }

    public List<Facebook> getFacebook() {
        return facebook;
    }

    public void setFacebook(List<Facebook> facebook) {
        this.facebook = facebook;
    }

    public List<Instagram> getInstagram() {
        return instagram;
    }

    public void setInstagram(List<Instagram> instagram) {
        this.instagram = instagram;
    }

    public List<Musicbrainz> getMusicbrainz() {
        return musicbrainz;
    }

    public void setMusicbrainz(List<Musicbrainz> musicbrainz) {
        this.musicbrainz = musicbrainz;
    }

}
