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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.thedeveloper.noteappdemo.R;
import com.example.thedeveloper.noteappdemo.apiconfig.HelperVolley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddVolleyActivity extends AppCompatActivity implements View.OnClickListener {


    private Toolbar toolbar;
    private Button addButton;
    private EditText name, number, mail;
    private ProgressDialog progressDialog;
    public static final String BASE_URL = "https://0301swe28.000webhostapp.com/slimrestapi/public/volley.php/insertvolley";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_volley);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Insert Use Volley");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = (EditText) findViewById(R.id.vcompany_name);
        number = (EditText) findViewById(R.id.vcompany_number);
        mail = (EditText) findViewById(R.id.vcompany_mail);
        addButton = (Button) findViewById(R.id.add_volley_Button);
        addButton.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);

    }


    @Override
    public void onClick(View v) {

        final String company_name = name.getText().toString().trim();
        final String company_number = number.getText().toString().trim();
        final String company_email = mail.getText().toString().trim();


        if (company_name.equals("")) {

            Toast.makeText(AddVolleyActivity.this, "company name is require", Toast.LENGTH_SHORT).show();

            return;
        }


        if (company_number.equals("")) {

            Toast.makeText(AddVolleyActivity.this, "company number is require", Toast.LENGTH_SHORT).show();

            return;
        }


        if (company_email.equals("")) {

            Toast.makeText(AddVolleyActivity.this, "company email is require", Toast.LENGTH_SHORT).show();

            return;
        }

        progressDialog.setMessage("Wait Loading.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(AddVolleyActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddVolleyActivity.this,VolleyActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(AddVolleyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("company_name", company_name);
                map.put("hr_number", company_number);
                map.put("hr_email", company_email);
                return map;
            }
        };

        HelperVolley.getmInstance(this).addToRequestQueue(stringRequest);

    }


}
