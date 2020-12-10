package com.example.bankraft.MainPage;

import android.graphics.drawable.Drawable;

public class HomePannelRecyclerItem {
    Drawable img;
    String home_context;

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getHome_context() {
        return home_context;
    }

    public void setHome_context(String home_context) {
        this.home_context = home_context;
    }

    public HomePannelRecyclerItem(Drawable img, String content) {
        this.img = img;
        this.home_context = content;
    }
}
