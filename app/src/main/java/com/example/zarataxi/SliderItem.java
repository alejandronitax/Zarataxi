package com.example.zarataxi;

import android.net.Uri;

public class SliderItem {

    private int image;
    private  String text;
   /* private  int order;
    private  Boolean show;

    private  String title;
    private  String description;
    private  Uri link;
*/

    public SliderItem(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

}
