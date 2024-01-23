package com.kidslearning.kidsplay.smartkids.derdroid.preschool.model;

import java.io.Serializable;

/**
 * Created by Naynesh Patel on 12-Feb-19.
 */
public class ModelVideo implements Serializable {

    String videoId,videoTitle,videoThumb;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoThumb() {
        return videoThumb;
    }

    public void setVideoThumb(String videoThumb) {
        this.videoThumb = videoThumb;
    }
}
