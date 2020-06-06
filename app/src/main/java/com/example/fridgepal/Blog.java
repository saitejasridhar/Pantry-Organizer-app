package com.example.fridgepal;

public class Blog {
    private String title;
    private String desc;
    private String image;
    private int quantity;
    private String time;
    private String unit;

    public Blog(String title, String desc, String image,int quantity,String time,String unit) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.quantity =quantity;
        this.time=time;
        this.unit=unit;

    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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