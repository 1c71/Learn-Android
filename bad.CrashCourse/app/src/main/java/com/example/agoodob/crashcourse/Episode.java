package com.example.agoodob.crashcourse;

/**
 * 每一集的属性
 */
public class Episode {

    private String course;
    private String episode;
    private String title;
    private String imgUrl;

    public Episode(String course, String episode, String title, String imgUrl) {
        this.course = course;
        this.episode = episode;
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
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
