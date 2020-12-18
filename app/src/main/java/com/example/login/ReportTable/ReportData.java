package com.example.login.ReportTable;

import java.io.Serializable;

public class ReportData implements Serializable {
    private int Index;//
    private String Location;
    private String ID;
    private int Num;
    private int Loos;

    public ReportData(){}
    public ReportData(int index, String location, String ID, int num, int loos) {
        Index = index;
        Location = location;
        this.ID = ID;
        Num = num;
        Loos = loos;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public int getLoos() {
        return Loos;
    }

    public void setLoos(int loos) {
        Loos = loos;
    }
}
