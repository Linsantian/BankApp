package com.example.login.UI;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;

import java.io.IOException;
import java.io.InputStream;

//import jxl.Workbook;
import jxl.*;
import jxl.read.biff.BiffException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void login_bt(View view) {
        Intent i = new Intent(MainActivity.this, MainFormActivity.class);
       // Toast.makeText(getApplicationContext(), "默认Toast样式", Toast.LENGTH_SHORT).show();
        if(Login_authentication()){
            startActivity(i);
        }
        else
            Toast.makeText(getApplicationContext(), "帐号或密码错误", Toast.LENGTH_SHORT).show();
    }


//用于登录时判断帐号是否正确
    public Boolean Login_authentication() {
        EditText editText = findViewById(R.id.id_editText);
        EditText password_editText = findViewById(R.id.password_editText);

        //获取AssetManager对象
        AssetManager assetManager = this.getAssets();
        //打开Excel文件，返回输入流对象
        try {
            InputStream inputStream = assetManager.open("personInfo.xls");
            Workbook book = Workbook.getWorkbook(inputStream);
            Sheet sheet = book.getSheet(0);                //获取工作簿
            int i, j;
            int Rows = sheet.getRows();               //得到当前工作表的行数
            int Cols = sheet.getColumns();            //得到当前工作表的列数
            for (i = 1; i < Rows; i++) {
                Cell c = sheet.getCell(0, i);                //获取单元格
                String s=c.getContents();
                if (s.equals(editText.getText().toString())) {
                    Cell c1 = sheet.getCell(1, i);                //获取单元格
                    String s1=c1.getContents();

                    if (s1.equals(password_editText.getText().toString())) {
                        book.close();
                        return TRUE;
                    }
                }
            }
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
return FALSE;
    }
}
