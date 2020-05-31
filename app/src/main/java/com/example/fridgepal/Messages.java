package com.example.fridgepal;

public class Messages {

    String name;
    String imageUrl;
    int quantity;

    public Messages() {
    }

    public Messages(String name, String imageUrl, int quantity) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.quantity=quantity;
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
