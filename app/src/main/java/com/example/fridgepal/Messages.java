package com.example.fridgepal;

public class Messages {

    String name;
    String imageUrl;
    int quantity;
    String unit;

    public Messages() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Messages(String name, String imageUrl, int quantity, String unit)
    {
        this.name=name;
        this.imageUrl=imageUrl;
        this.quantity=quantity;
        this.unit=unit;
    }


    public int getQuantityy() {
        return quantity;
    }

    public void setQuantityy(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
