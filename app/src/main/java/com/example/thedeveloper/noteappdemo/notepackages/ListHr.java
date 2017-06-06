package com.example.thedeveloper.noteappdemo.notepackages;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ListHr {

    @SerializedName("retrofit_list")
    private ArrayList<HrData> getList;

    public ListHr() {
    }

    public ArrayList<HrData> getGetList() {
        return getList;
    }
}
