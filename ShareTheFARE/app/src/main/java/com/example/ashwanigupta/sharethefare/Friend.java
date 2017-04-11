package com.example.ashwanigupta.sharethefare;

import android.net.Uri;

/**
 * Created by ashwani gupta on 13-02-2017.
 */

public class Friend {

    int id;
    String name;
    float total;
   // Uri image;
    String image;
    public int clicked;
    public int flag;

    public Friend(int id, String name,float total) {
        this.id=id;
        this.name = name;
        this.total = total;
        this.clicked = 0;
        this.flag = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getTotal() {
        return total;
    }

    public int getClicked() {
        return clicked;
    }

    public int getFlag() {
        return flag;
    }

    //    public Uri getImage() {
//        return image;
//    }

    public void setTotal(float t) {
        total = total + t;
    }

    public void updateTotal(Float t) {
        total = t;
    }

    public String getImage() {
        return image;
    }
}
