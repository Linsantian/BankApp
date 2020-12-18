package com.example.login.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.Inventory;
import com.example.login.Order.OneOrder;
import com.example.login.R;
import com.example.login.ReportTable.OneReport;
import com.example.login.ReportTable.ReportData;

import java.util.ArrayList;
import java.util.List;

public class HistoryReport extends AppCompatActivity {
    ListView list_report;
    ListView list_table;
    ArrayList<OneReport> AllReport=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_report);
        Bundle bundle = getIntent().getExtras();
        AllReport = (ArrayList<OneReport>) bundle.getSerializable("cc");



        list_report=findViewById(R.id.listview_history);
         ReportAdapter reportAdapter = new ReportAdapter(this,R.layout.report_item,AllReport);
        list_report.setAdapter(reportAdapter);
        list_report.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dia;
                OneReport oneReport=new OneReport();
                oneReport=AllReport.get(position);
                LayoutInflater lay=LayoutInflater.from(HistoryReport.this);
                View view1 = lay.inflate(R.layout.table_report,null);
                AlertDialog.Builder bui=new AlertDialog.Builder(HistoryReport.this);
                bui.setView(view1);
                dia= bui.create();
                dia.show();
                list_table=view1.findViewById(R.id.listview_table_report);
                TableAdapter tableAdapter = new TableAdapter(HistoryReport.this,R.layout.table_model,oneReport.getOneData());
                list_table.setAdapter(tableAdapter);
                tableAdapter.notifyDataSetChanged();

            }
        });
        reportAdapter.notifyDataSetChanged();
    }

}
//适配器
class ReportAdapter extends ArrayAdapter<OneReport> {
    private  int resourceId;

    public ReportAdapter(@NonNull Context context, int resource, @NonNull List<OneReport> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(this.getContext());
        View view = mInflater.inflate(this.resourceId, null);
       // View view = LayoutInflater.from(this.getContext()).inflate(resourceId,null);
        OneReport oneReport = this.getItem(position);
        TextView title = view.findViewById(R.id.textView_reportItem);
        title.setText("报表："+oneReport.getTimeName());
        return view;
    }
}
//表格适配器
class TableAdapter extends ArrayAdapter<ReportData>{
    private  int resourceId;

    public TableAdapter(@NonNull Context context, int resource, @NonNull List<ReportData> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(this.getContext());
        View view = mInflater.inflate(this.resourceId, null);

        ReportData reportData =  this.getItem(position);
        TextView location = view.findViewById(R.id.textView_L);
        TextView id = view.findViewById(R.id.textView_C);
        TextView num = view.findViewById(R.id.textView_N);
        TextView loos = view.findViewById(R.id.textView_Lo);

        location.setText(reportData.getLocation());
        id.setText(reportData.getID());
        num.setText(reportData.getNum()+"");
        loos.setText(reportData.getLoos()+"");
        return view;

    }
}





