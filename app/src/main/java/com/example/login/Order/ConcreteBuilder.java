package com.example.login.Order;

import android.util.Log;

import com.example.login.UI.MainFormActivity;

import java.util.Random;

import static java.lang.Boolean.TRUE;

public class ConcreteBuilder extends WorkBuilder {
    private OneOrder oneOrder = new OneOrder();
    @Override
    public void RandomBuild() {
        Random random = new Random();

       int OneOrderNum = random.nextInt(3)+1;//每单的衣服量数,几种衣服
        for(int j=0;j<OneOrderNum;j++){
            Order order=new Order();
            int CountNum=random.nextInt(2)+1;//每种衣服量数,几个衣服
            int id=random.nextInt(MainFormActivity.AllClothes.size());
            Log.d("测试1","HHH");
            /*for(int i=0;i<oneOrder.getOneOrder().size();i++) {

                while (MainFormActivity.AllClothes.get(id).getCostume().getClothesID().equals(oneOrder.getOneOrder().get(i).getClothesID())) {//如果已经存在,重新选择
                    id = random.nextInt(MainFormActivity.AllClothes.size());
                }
             while (MainFormActivity.AllClothes.get(id).getCostume().getClothesNum()<CountNum){
                if(MainFormActivity.AllClothes.get(id).getCostume().getClothesNum()==0){
                    id=random.nextInt(MainFormActivity.AllClothes.size());
                }
                else
                    CountNum=random.nextInt(2)+1;
            }

             */
            order.setClotherNum(CountNum);
            order.setClothesID(MainFormActivity.AllClothes.get(id).getClothesID());
               // Log.d("测试",order.getClotherNum()+"  "+id+"  "+MainFormActivity.AllClothes.get(1).getClothesID()+"123");
            oneOrder.getOneOrder().add(order);
            Log.d("测试",oneOrder.getOneOrder().size()+"123");
            order=new Order();

    }

    }
    @Override
    public OneOrder getResult() {
        return oneOrder;
    }
}
