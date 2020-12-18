package com.example.login.Order;

import java.io.Serializable;
import java.util.ArrayList;

public class OneOrder implements Serializable {
    private String orderId;
    private ArrayList<Order> oneOrder=new ArrayList<>();

    public OneOrder(){};

    public void AddOrder(Order order) {
        oneOrder.add(order);
    }

    public OneOrder(String orderId, ArrayList<Order> oneOrder) {
        this.orderId = orderId;
        this.oneOrder = oneOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ArrayList<Order> getOneOrder() {
        return oneOrder;
    }

    public void setOneOrder(ArrayList<Order> oneOrder) {
        this.oneOrder = oneOrder;
    }
}
