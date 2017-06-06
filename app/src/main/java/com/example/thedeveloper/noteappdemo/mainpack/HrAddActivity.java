package com.example.thedeveloper.noteappdemo.mainpack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thedeveloper.noteappdemo.R;
import com.example.thedeveloper.noteappdemo.apiconfig.ApiRetrofitLib;
import com.example.thedeveloper.noteappdemo.notepackages.Result;
import com.example.thedeveloper.noteappdemo.notepackages.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.thedeveloper.noteappdemo.R.id.company_name;

public class HrAddActivity extends AppCompatActivity implements View.OnClickListener{

    //public static final String ENDURL = "http://10.0.3.2/slimrestapi/public/retrofit.php/";

    public static final String ENDURL = "https://0301swe28.000webhostapp.com/slimrestapi/public/retrofit.php/";

    private Toolbar toolbar;
    EditText name , number , mail;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_add);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Insert Use Retrofit");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = (EditText) findViewById(company_name);
        number = (EditText) findViewById(R.id.company_number);
        mail = (EditText) findViewById(R.id.company_mail);

        addBtn = (Button) findViewById(R.id.add_retrofit_Button);
        addBtn.setOnClickListener(this);


   }

    @Override
    public void onClick(View v) {


        final ProgressDialog dialog = new ProgressDialog(HrAddActivity.this);

        String company_name  = name.getText().toString().trim();
        String company_number  = number.getText().toString().trim();
        String company_mail  = mail.getText().toString().trim();

        if(company_name.equals("")){
            Toast.makeText(HrAddActivity.this, "company name is require", Toast.LENGTH_SHORT).show();
            return;
        }

        if(company_number.equals("")){
            Toast.makeText(HrAddActivity.this, "company number is require", Toast.LENGTH_SHORT).show();
            return;
        }

        if(company_mail.equals("")){
            Toast.makeText(HrAddActivity.this, "company email is require", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.setMessage("Wait Add Hr Loading .....");
        dialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ENDURL).addConverterFactory(GsonConverterFactory.create()).build();

        ApiRetrofitLib lib = retrofit.create(ApiRetrofitLib.class);


        User user = new User(company_name,company_number,company_mail);

        Call<Result> call = lib.createList(
                user.getCompany_name(),
                user.getCompany_number(),
                user.getCompany_email()
        );


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                dialog.dismiss();
                Toast.makeText(HrAddActivity.this, response.body().getMessage() , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HrAddActivity.this,RetrofitLib2.class));

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                dialog.hide();
                Toast.makeText(HrAddActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });



    }
}
