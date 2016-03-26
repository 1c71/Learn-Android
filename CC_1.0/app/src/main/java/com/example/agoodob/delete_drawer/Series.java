package com.example.agoodob.delete_drawer;

/**
 * 一个系列
 */
public class Series {

    private String title;
    private String imgUrl;
    private String seriesId;

    public Series(String seriesId, String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.seriesId = seriesId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
