package com.example.thedeveloper.noteappdemo.notepackages;

import com.google.gson.annotations.SerializedName;

/**
 * Created by The Developer on 6/3/17.
 */

public class User {


    @SerializedName("hr_name")
    private int hr_id;
    @SerializedName("company_name")
    private String company_name;
    @SerializedName("hr_number")
    private String company_number;
    @SerializedName("hr_email")
    private String company_email;

    public User(String company_name, String company_number, String company_email) {
        this.company_name = company_name;
        this.company_number = company_number;
        this.company_email = company_email;
    }

    public int getHr_id() {
        return hr_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCompany_number() {
        return company_number;
    }

    public String getCompany_email() {
        return company_email;
    }
}
