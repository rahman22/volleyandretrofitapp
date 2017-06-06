package com.example.thedeveloper.noteappdemo.apiconfig;

import com.example.thedeveloper.noteappdemo.notepackages.ListHr;
import com.example.thedeveloper.noteappdemo.notepackages.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRetrofitLib {

    @GET("retrofitlist")
    Call<ListHr> getHrList();


    @FormUrlEncoded
    @POST("insertretrofit")
    Call<Result> createList(
            @Field("company_name") String name,
            @Field("hr_number") String number,
            @Field("hr_email") String email);
}
