package com.example.login.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.login.Costume.Clothes;
import com.example.login.Costume.ClothesFactory;
import com.example.login.Costume.ConcreteClothes;
import com.example.login.Costume.ConcreteClothesFactory;
import com.example.login.Inventory;
import com.example.login.Order.ConcreteBuilder;
import com.example.login.Order.Management;
import com.example.login.Order.OneOrder;
import com.example.login.Order.Order;
import com.example.login.Order.WorkBuilder;
import com.example.login.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainFormActivity extends AppCompatActivity {

    public ArrayList<Inventory> AllInventory=new ArrayList<>();//库存的所有数据
    public ArrayList<OneOrder> AllOrder=new ArrayList<>();//所有的订单
    public static ArrayList<Clothes> AllClothes=new ArrayList<>();//所有的商品，衣服

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);
        Init();
        InitClothes();

    //Toast.makeText(getApplicationContext(), "报表生成成功！请在历史报表查收！"+AllOrder.get(0).getOneOrder().get(0).getClothesID(), Toast.LENGTH_SHORT).show();


}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if(resultCode == RESULT_OK) {      //新添

            switch (requestCode) {
                case REQUEST_CODE:


                    Bundle bundle = data.getExtras();
                    AllData date = (AllData) bundle.getSerializable("aa");

                    ADD(date);
                    break;
            }
            SelfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long ID) {
                    onCreatDetail(position);
                }
            });


    }

         */
    }

    //查询功能的点击事件
    public void enquiry_bt(View view)
    {
        Intent i = new Intent(MainFormActivity.this, Enquiry.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("aa",(Serializable) AllInventory);

        i.putExtras(bundle);
        //setResult(RESULT_OK,i);
        //startActivityForResult(i,RESULT_OK);
        startActivity(i);
    }
    //盘点功能的点击事件
    public void Stocktaking_bt(View view)
    {
        Intent i = new Intent(MainFormActivity.this, Stocktaking.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bb",(Serializable) AllInventory);
        i.putExtras(bundle);
        startActivity(i);
    }
    //随机生成订单的点击事件
    public void order_bt(View view){
        RandomOrder(5);
    return;
    }
    //今日工作点击事件
    public void work_bt(View view){
        Intent i = new Intent(MainFormActivity.this, TodayWork.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ee",(Serializable) AllOrder);
        i.putExtras(bundle);
        startActivity(i);
    }
    //初始化，将excel数据获得到AllInventory
    public void Init(){
        //获取AssetManager对象
        AssetManager assetManager = this.getAssets();
        //打开Excel文件，返回输入流对象
        try {
            InputStream inputStream = assetManager.open("Info.xls");
            Workbook book = Workbook.getWorkbook(inputStream);
            Sheet sheet = book.getSheet(0);                //获取工作簿
            int Rows = sheet.getRows();               //得到当前工作表的行数
            for (int i = 1; i < Rows-2; i++) {
                Inventory inventory=new Inventory();
                int r=sheet.getCell(0, i).getRow();
                inventory.setId(r);
                r=(r-2)/12;
                inventory.setShelves(""+(char)('A'+r));
                inventory.setLocation(Integer.parseInt(sheet.getCell(1,i).getContents()));
                inventory.setClothingId(sheet.getCell(2,i).getContents());
               if(sheet.getCell(3,i).getContents().equals(""))
                {inventory.setClothingNumber(0);}
                else{
                inventory.setClothingNumber(Integer.parseInt(sheet.getCell(3,i).getContents()));}

                AllInventory.add(inventory);
            }
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
    //初始化，生成货物类,使用工厂方法模式调用,需要什么产品则通过该产品对应的工厂类来获取，不需要知道具体的创建过程
    public void InitClothes(){
        for (int i=0;i<AllInventory.size();i++)
        {
            if(!AllInventory.get(i).getClothingId().equals("")){
                ClothesFactory clothesFactory = new ConcreteClothesFactory();
                ConcreteClothes clothes = (ConcreteClothes) clothesFactory.getCostume();
                clothes.setClothesID(AllInventory.get(i).getClothingId());
                clothes.setClothesNum(AllInventory.get(i).getClothingNumber());
                AllClothes.add(clothes);

            }
        }
       // Toast.makeText(getApplicationContext(), "报表生成成功！请在历史报表查收！"+AllClothes.size(), Toast.LENGTH_SHORT).show();
    }
    //随机生成订单，参数RON为限制每次生成的单数，
    public void RandomOrder(int RON){
        Random random=new Random();
int Num= random.nextInt(RON)+1;
        Toast.makeText(getApplicationContext(), "已经新增"+Num+"条订单，请尽快处理！", Toast.LENGTH_SHORT).show();
        for(int i=0;i<Num;i++) {
            Management director = new Management();
            WorkBuilder builder = new ConcreteBuilder();
            director.construct(builder);
            OneOrder product = builder.getResult();
            product.setOrderId(Integer.toString(AllOrder.size()+1));
            AllOrder.add(product);
        }

             }



}
