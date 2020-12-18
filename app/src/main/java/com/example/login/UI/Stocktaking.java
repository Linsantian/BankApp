package com.example.login.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.Inventory;
import com.example.login.R;
import com.example.login.ReportTable.OneReport;
import com.example.login.ReportTable.ReportData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Stocktaking extends AppCompatActivity {
    private OneReport oneReport=new OneReport();
    private ArrayList<OneReport> AllReport=new ArrayList<>();
     ArrayList<Inventory> AllInventory=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocktaking);


        Bundle bundle = getIntent().getExtras();
        AllInventory = (ArrayList<Inventory>) bundle.getSerializable("bb");
        Read_inventory();//默认显示第一个数据
        Spinner_SelectedChance();//选项改变事件

    }
    //当下拉列表改变时的监听事件
    public void Spinner_SelectedChance(){
    Spinner spinner_shelves=findViewById(R.id.spinner_Shelves);
    spinner_shelves.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           Read_inventory();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
    Spinner spinner_location=findViewById(R.id.spinner_Location);
    spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Read_inventory();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
}
//读取对应位置的ID和数量，并显示出来
    public void Read_inventory(){
        TextView textView_Id=findViewById(R.id.textView_Id);
        EditText editText_Num=findViewById(R.id.editText_Num);

                textView_Id.setText("服装ID:  "+AllInventory.get(getRow()-1).getClothingId());
                editText_Num.setText(AllInventory.get(getRow()-1).getClothingNumber()+"");

    }
    //核查确认的点击事件
    public  void stocktaking_bt(View view){
if(AllInventory.get(getRow()-1).getClothingId().equals("")){
    location_Next();//自动进入下一个状态
}

else{
    setData();//给数据赋值
    EditText editText_Num=findViewById(R.id.editText_Num);

    location_Next();//自动进入下一个状态
}

    }
    //生成报表的点击事件
    public  void table_bt(View view){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss//获取当前时间
         Date date = new Date(System.currentTimeMillis());
        oneReport.setTimeName(simpleDateFormat.format(date));
        AllReport.add(oneReport);
        initOneReport();//init盘点数据
        Toast.makeText(getApplicationContext(), "报表生成成功！请在历史报表查收！"+simpleDateFormat.format(date), Toast.LENGTH_SHORT).show();
    }
    //历史报表的点击事件
    public void History_bt(View view){
        Intent i = new Intent(Stocktaking.this, HistoryReport.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cc",(Serializable) AllReport);
        i.putExtras(bundle);
        startActivity(i);
    }
    //返回按钮的点击事件
    public void back_bt(View view){
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("dd",(Serializable) AllInventory);

        i.putExtras(bundle);
        setResult(RESULT_OK,i);
        finish();

    }
    //点击一下自动进入下一个状态
    public  void location_Next(){
        Spinner spinner_Location=findViewById(R.id.spinner_Location);
        do{
            if(spinner_Location.getSelectedItemPosition()!=11)
                spinner_Location.setSelection(spinner_Location.getSelectedItemPosition()+1);

            else
            {
                spinner_Location.setSelection(0);
            }
        }while(AllInventory.get(getRow()-1).getClothingId().isEmpty());

    }
    //获得此时的货位的行数，返回值为行数
    public int getRow(){
        Spinner spinner_Shelves=findViewById(R.id.spinner_Shelves);
        Spinner spinner_Location=findViewById(R.id.spinner_Location);
        int i=0,j=0;
        switch (spinner_Shelves.getItemAtPosition(spinner_Shelves.getSelectedItemPosition()).toString()){

            case "A":
                i=0;
                break;
            case "B":
                i=12;
                break;
            case "C":
                i=24;
                break;
            case "D":
                i=36;
                break;
            default:
                break;
        }
        String s1=spinner_Location.getItemAtPosition(spinner_Location.getSelectedItemPosition()).toString();
        j=Integer.parseInt(s1);//获得货架位置,数字形式
        return i+j;
    }
    //点击赋值
    public void setData(){
        EditText editText_Num=findViewById(R.id.editText_Num);

    for (int i = 0; i < oneReport.getOneData().size(); i++) {
        if (oneReport.getOneData().get(i).getIndex() == getRow()) {
            oneReport.getOneData().get(i).setNum(Integer.parseInt(editText_Num.getText().toString()));
            oneReport.getOneData().get(i).setLoos(Integer.parseInt(editText_Num.getText().toString()) - AllInventory.get(getRow()-1).getClothingNumber());
            AllInventory.get(getRow()-1).setClothingNumber(Integer.parseInt(editText_Num.getText().toString()));
            return;
        }
    }

        ReportData reportData=new ReportData();
        reportData.setIndex(getRow());
        reportData.setID(AllInventory.get(getRow()-1).getClothingId());
        reportData.setLocation(AllInventory.get(getRow()-1).getShelves()+"-"+AllInventory.get(getRow()-1).getLocation());
        reportData.setNum(Integer.parseInt(editText_Num.getText().toString()));
        reportData.setLoos(Integer.parseInt(editText_Num.getText().toString())-AllInventory.get(getRow()-1).getClothingNumber());
        AllInventory.get(getRow()-1).setClothingNumber(Integer.parseInt(editText_Num.getText().toString()));
        oneReport.getOneData().add(reportData);
    }
    //参数：i为列，j为行，返回值是excel单元格的内容String
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
    //初始化盘点oneReport
    public void initOneReport(){
        for(int i=0;i<oneReport.getOneData().size();i++){
           oneReport.getOneData().get(i).setLoos(0);
        }

        oneReport=new OneReport();
    }
}
