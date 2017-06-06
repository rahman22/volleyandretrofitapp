package com.example.thedeveloper.noteappdemo.notepackages;

import com.google.gson.annotations.SerializedName;

/**
 * Created by The Developer on 5/13/2017.
 */

public class HrData {

    @SerializedName("hr_name")
    private String name;
    @SerializedName("hr_num")
    private String contact;
    @SerializedName("hr_mail")
    private String emails;


    public HrData(String name, String contact, String emails) {
        this.name = name;
        this.contact = contact;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmails() {
        return emails;
    }
}
