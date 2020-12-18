package com.example.login;

import java.io.Serializable;

public class Inventory implements Serializable {
    private int id;         //第几行
    private  String Shelves;//货架
    private  int Location;//货位
    private  String ClothingId;//服装ID
    private  int ClothingNumber;//服装数量


    public Inventory(){}
    public Inventory(int id, String shelves, int location, String clothingId, int clothingNumber) {
        this.id = id;
        Shelves = shelves;
        Location = location;
        ClothingId = clothingId;
        ClothingNumber = clothingNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShelves() {
        return Shelves;
    }

    public void setShelves(String shelves) {
        Shelves = shelves;
    }

    public int getLocation() {
        return Location;
    }

    public void setLocation(int location) {
        Location = location;
    }

    public String getClothingId() {
        return ClothingId;
    }

    public void setClothingId(String clothingId) {
        ClothingId = clothingId;
    }

    public int getClothingNumber() {
        return ClothingNumber;
    }

    public void setClothingNumber(int clothingNumber) {
        ClothingNumber = clothingNumber;
    }
}
