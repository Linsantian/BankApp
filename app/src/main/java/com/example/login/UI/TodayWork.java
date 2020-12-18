package com.example.login.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.login.Order.OneOrder;
import com.example.login.Order.Order;
import com.example.login.R;
import com.example.login.ReportTable.OneReport;
import com.example.login.ReportTable.ReportData;

import java.util.ArrayList;
import java.util.List;

public class TodayWork extends AppCompatActivity {
    ArrayList<OneOrder> AllOrder=new ArrayList<>();
    ListView list_work;
    ListView list_orderdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_work);
        Bundle bundle = getIntent().getExtras();
        AllOrder = (ArrayList<OneOrder>) bundle.getSerializable("ee");


        list_work=findViewById(R.id.listview_work);
        WorkAdapter workAdapter = new WorkAdapter(this,R.layout.work_item,AllOrder);
        list_work.setAdapter(workAdapter);
        list_work.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dia;
                LayoutInflater lay=LayoutInflater.from(TodayWork.this);
                View view1 = lay.inflate(R.layout.orderdetail,null);
                AlertDialog.Builder bui=new AlertDialog.Builder(TodayWork.this);
                bui.setView(view1);
                dia= bui.create();
                dia.show();
                Log.d("a",Integer.toString(AllOrder.get(position).getOneOrder().size()));
                list_orderdetail=view1.findViewById(R.id.listview_orderdetail);
                OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(TodayWork.this,R.layout.orderdetail_item,AllOrder.get(position).getOneOrder());

                list_orderdetail.setAdapter(orderDetailAdapter);


                orderDetailAdapter.notifyDataSetChanged();

            }
        });
        workAdapter.notifyDataSetChanged();
    }
}


//适配器
class WorkAdapter extends ArrayAdapter<OneOrder> {
    private  int resourceId;
    public WorkAdapter(@NonNull Context context, int resource, @NonNull List<OneOrder> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(this.getContext());
        View view = mInflater.inflate(this.resourceId, null);
        OneOrder oneOrder = this.getItem(position);
        TextView title = view.findViewById(R.id.textView_work_item);
        title.setText("报表： "+oneOrder.getOrderId());
        return view;
    }
}

//拣货单详情适配器
class OrderDetailAdapter extends ArrayAdapter<Order>{
    private  int resourceId;

    public OrderDetailAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(this.getContext());
        View view = mInflater.inflate(this.resourceId, null);

        Order order =  this.getItem(position);
        TextView textView_orderDetail_item = view.findViewById(R.id.textView_orderdetail_item);
        textView_orderDetail_item.setText("     服装ID:"+order.getClothesID()+"           "+"数目："+order.getClotherNum());
        return view;

    }
}