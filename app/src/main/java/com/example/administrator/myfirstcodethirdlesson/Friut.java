package com.example.administrator.myfirstcodethirdlesson;

/**
 * Created by Administrator on 2017/12/15.
 */

public class Friut {
    public String name;
    public int imageId;
    public Friut(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
