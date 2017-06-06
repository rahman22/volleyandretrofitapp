package com.example.thedeveloper.noteappdemo.notepackages;

/**
 * Created by The Developer on 6/4/17.
 */

public class DemoList {


    private String libName;
    private String libDescription;

    public DemoList() {
    }

    public DemoList(String libName, String libDescription) {
        this.libName = libName;
        this.libDescription = libDescription;
    }

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getLibDescription() {
        return libDescription;
    }

    public void setLibDescription(String libDescription) {
        this.libDescription = libDescription;
    }
}
