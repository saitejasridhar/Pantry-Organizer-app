package com.example.fridgepal;

public class Blog {
    private String title;
    private String desc;
    private String image;
    private int qunatity;

    public Blog(String title, String desc, String image,int qunatity) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.qunatity=qunatity;

    }

    public int getQunatity() {
        return qunatity;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
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