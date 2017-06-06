package com.example.thedeveloper.noteappdemo.apiconfig;

import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;


public class RestApi {


    public interface insert {
        @FormUrlEncoded
        @POST("/restapi/insertData.php")
        void insertHr(
                @Field("company_name") String name,
                @Field("contact_no") String mobile,
                @Field("hr_emailid") String email,
                Callback<Response> callback);
    }

    public interface read {
        @GET("/restapi/displayAll.php")
        void readData(Callback<JsonElement> callback);
    }


}
