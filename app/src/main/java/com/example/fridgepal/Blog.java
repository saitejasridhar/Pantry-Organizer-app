package com.example.fridgepal;

public class Blog {
    private String title;
    private String desc;
    private String image;
    private int quantity;
    private String time;

    public Blog(String title, String desc, String image,int qunatity,String time) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.quantity =qunatity;
        this.time=time;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public Blog()
    {

    }

}