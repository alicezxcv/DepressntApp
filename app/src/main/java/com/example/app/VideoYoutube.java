package com.example.app;

public class VideoYoutube {
    private String Title, Thumbnail, IdVideo;

    public VideoYoutube(String title, String thumbnail, String idVideo) {
        Title = title;
        Thumbnail = thumbnail;
        IdVideo = idVideo;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getIdVideo() {
        return IdVideo;
    }

    public void setIdVideo(String idVideo) {
        IdVideo = idVideo;
    }
}
