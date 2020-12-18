package com.example.login.Order;

import java.io.Serializable;

//记录订单
public class Order implements Serializable {
    private String ClothesID;
    private int ClotherNum;

    public Order(){};
    public Order(String clothesID, int clotherNum) {
        ClothesID = clothesID;
        ClotherNum = clotherNum;
    }

    public String getClothesID() {
        return ClothesID;
    }

    public void setClothesID(String clothesID) {
        ClothesID = clothesID;
    }

    public int getClotherNum() {
        return ClotherNum;
    }

    public void setClotherNum(int clotherNum) {
        ClotherNum = clotherNum;
    }
}
