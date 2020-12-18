package com.example.login.Costume;
//服装类,抽象类也就是代指某一个事物，并没有具体
public abstract class Clothes {
    private String ClothesID;
    private  int ClothesNum;
    public abstract void Use();//待定

    public String getClothesID() {
        return ClothesID;
    }

    public void setClothesID(String clothesID) {
        ClothesID = clothesID;
    }

    public int getClothesNum() {
        return ClothesNum;
    }

    public void setClothesNum(int clothesNum) {
        ClothesNum = clothesNum;
    }
}

