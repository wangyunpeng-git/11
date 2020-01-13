package com.example.zhasanguo.person;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.zhasanguo.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private int imageId;

    //------------------------------
    //2019/12/20 Tough Add:
    int nID;
    String imageName;
    String introduction;
    //-------------------------------

    public Person(int imageId) {
        this.imageId = imageId;
    }

    public Person(int nID, String imageName, String introduction) {
        this.nID = nID;
        this.imageName = imageName;
        this.introduction = introduction;
    }

    public int getImageId() {
        return imageId;
    }

    public String getImageName(){
        return this.imageName;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;

    }




}
