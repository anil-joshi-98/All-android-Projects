package com.example.cardview;

import android.provider.ContactsContract;

public class DataModel {
    int imageRes;
    String imageView;
    public DataModel(int imageRes,String imageView){
        this.imageRes=imageRes;
        this.imageView=imageView;
    }

    public int getImageRes() {
        return imageRes;
    }
    public String getImageView(){
        return imageView;
    }
}
