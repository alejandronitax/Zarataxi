package com.example.zarataxi;

public class DirectionItem {
    private int image;
    private String text;

    public DirectionItem(int image, String text) {
        this.image = image;
        this.text = text;
    }


   /* private  int order;
    private  Boolean show;

    private  String title;
    private  String description;
    private  Uri link;
*/

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
