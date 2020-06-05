package com.example.fridgepal;

public class Recipe {
    String name;
    String ing;
    String pro;
    String image;
    String link;

    public Recipe(String name, String ing, String pro,String image,String link) {
        this.name = name;
        this.ing = ing;
        this.pro = pro;
        this.link=link;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public  Recipe() {

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
