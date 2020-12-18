package com.example.login.ReportTable;

import java.io.Serializable;
import java.util.ArrayList;

public class OneReport implements Serializable {
    private ArrayList<ReportData> OneData=new ArrayList<>();
    private String TimeName;

    public  OneReport(){}
    public OneReport(ArrayList<ReportData> oneData, String timeName) {
        OneData = oneData;
        TimeName = timeName;
    }

    public ArrayList<ReportData> getOneData() {
        return OneData;
    }

    public void setOneData(ArrayList<ReportData> oneData) {
        OneData = oneData;
    }

    public String getTimeName() {
        return TimeName;
    }

    public void setTimeName(String timeName) {
        TimeName = timeName;
    }
}
