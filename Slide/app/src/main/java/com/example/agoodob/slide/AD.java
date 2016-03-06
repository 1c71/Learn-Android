package com.example.agoodob.slide;

/**
 * Created by agoodob on 2016/3/4.
 */
public class AD {

    private int  iconResID;
    private String intro;

    public AD(int iconResID, String intro) {
        this.iconResID = iconResID;
        this.intro = intro;
    }

    public int getIconResID() {
        return iconResID;
    }

    public void setIconResID(int iconResID) {
        this.iconResID = iconResID;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }



}
