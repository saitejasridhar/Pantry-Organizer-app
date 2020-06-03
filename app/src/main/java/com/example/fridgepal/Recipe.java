package com.example.fridgepal;

public class Recipe {
    String name;
    String ing;
    String pro;
    String image;

    public Recipe(String name, String ing, String pro,String image) {
        this.name = name;
        this.ing = ing;
        this.pro = pro;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public  Recipe() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }
}
