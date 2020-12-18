package com.example.login.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.Inventory;
import com.example.login.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Enquiry extends AppCompatActivity {
    ArrayList<Inventory> AllInventory=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        Bundle bundle = getIntent().getExtras();
        AllInventory = (ArrayList<Inventory>) bundle.getSerializable("aa");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    public void enquiry_sure_bt(View view){
        EditText editText=findViewById(R.id.editText_ID);
        editText.setTextIsSelectable(true);//主要就是这句话起到复制粘贴的作用
        String s=editText.getText().toString();
        int Rr=getposition(AllInventory,s);
        if(Rr!=AllInventory.size()+1){
        final Dialog dia;
        LayoutInflater lay=LayoutInflater.from(this);
        View v1=lay.inflate(R.layout.enquiry_result,null);
        AlertDialog.Builder bui=new AlertDialog.Builder(this);
        bui.setView(v1);
        dia= bui.create();
        dia.show();
            TextView textView_location=v1.findViewById(R.id.textView_Location);
            TextView textView_Num=v1.findViewById(R.id.textView_Num);
            TextView textView_ID=v1.findViewById(R.id.textView_ID);
            textView_location.setText("服装位置："+AllInventory.get(Rr).getShelves()+"-"+AllInventory.get(Rr).getLocation());
            textView_Num.setText("数量："+AllInventory.get(Rr).getClothingNumber());
            textView_ID.setText("服装ID："+AllInventory.get(Rr).getClothingId());
            Button button=v1.findViewById(R.id.button_enquiry_sure);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dia.dismiss();
                }
            });
        }
        else if(s.equals("")){
            Toast.makeText(getApplicationContext(), "请输入服装ID", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(), "无此服装id", Toast.LENGTH_SHORT).show();


    }
    //参数：所有库存数据，返回值为position
    public int getposition(ArrayList<Inventory> AllInvevtory,String Str){
        for(int i=0;i<AllInvevtory.size();i++){
            if(AllInvevtory.get(i).getClothingId().equals(Str)){
                return i;
            }
        }
        return AllInvevtory.size()+1;
    }
    //参数:S为查找的对象，fileName为查找的文件,Col为查找的列（属性），返回值是excel单元格的行数
    public int getRows(String S,String fileName,int Col){
        //获取AssetManager对象
        AssetManager assetManager = this.getAssets();
        //打开Excel文件，返回输入流对象
        try {
            InputStream inputStream = assetManager.open(fileName);
            Workbook book = Workbook.getWorkbook(inputStream);
            Sheet sheet = book.getSheet(0);                //获取工作簿
            int Rows=sheet.getRows();
            for(int i=1;i<Rows;i++){
                Cell c=sheet.getCell(Col,i);
                String s=c.getContents();
                if(S.equals(s)){
                    book.close();
                    return i;
                }
            }
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //参数：fileName为文件名，i为列，j为行，返回值是excel单元格的内容String
    public String getExcelString(String fileName,int i,int j){

        //获取AssetManager对象
        AssetManager assetManager = this.getAssets();
        //打开Excel文件，返回输入流对象
        try {
            InputStream inputStream = assetManager.open(fileName);
            Workbook book = Workbook.getWorkbook(inputStream);
            Sheet sheet = book.getSheet(0);                //获取工作簿
            Cell c=sheet.getCell(i,j);
            String s=c.getContents();
            book.close();
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return "";
    }
    //参数：r:通过行数获得其在哪个货架，返回货架
    public String getShelves(int r){
        int r1=(r-2)/12;
        switch (r1){
            case 0:
                return "A";

            case 1:
                return "B";

            case 2:
                return "C";

            case 3:
                return "D";

            default:
                break;
        }
        return "";
    }
}
