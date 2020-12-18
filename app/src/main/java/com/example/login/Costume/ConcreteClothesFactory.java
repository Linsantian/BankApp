package com.example.login.Costume;

public class ConcreteClothesFactory extends ClothesFactory {

    @Override
    public Clothes getCostume() {
        return new ConcreteClothes();
    }

}
