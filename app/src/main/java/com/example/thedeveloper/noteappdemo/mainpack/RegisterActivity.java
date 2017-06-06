package com.example.thedeveloper.noteappdemo.mainpack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.thedeveloper.noteappdemo.R;
import com.example.thedeveloper.noteappdemo.apiconfig.HelperVolley;
import com.example.thedeveloper.noteappdemo.apiconfig.ShairedPreManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by The Developer on 6/6/17.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BASE_URL = "https://0301swe28.000webhostapp.com/slimrestapi/public/volley.php/register";

    TextView register;
    EditText fullname, emailid, passcode;
    Button registerButton;
    ProgressDialog progressDialog;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        if(ShairedPreManager.getmInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this , HomeActivity.class));
            return;
        }

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Register Activity");
        setSupportActionBar(toolbar);
        register = (TextView) findViewById(R.id.text_register);
        fullname = (EditText) findViewById(R.id.registerNameText);
        emailid = (EditText) findViewById(R.id.registerEmailText);
        passcode = (EditText) findViewById(R.id.registerPasswordText);
        registerButton = (Button) findViewById(R.id.register_Button);
        registerButton.setOnClickListener(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }


    @Override
    public void onClick(View v) {

        final String full_name = fullname.getText().toString().trim();
        final String email_id = emailid.getText().toString().trim();
        final String password = passcode.getText().toString().trim();


        progressDialog = new ProgressDialog(RegisterActivity.this);

        if (full_name.equals("")) {

            Toast.makeText(RegisterActivity.this, "name is required please", Toast.LENGTH_SHORT).show();
            return;
        }


        if (email_id.equals("")) {

            Toast.makeText(RegisterActivity.this, "email is required please", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals("")) {

            Toast.makeText(RegisterActivity.this, "password is required please", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Wait Register Loading ......");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "error is code " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("name", full_name);
                params.put("email", email_id);
                params.put("password", password);

                return params;
            }
        };

        HelperVolley.getmInstance(this).addToRequestQueue(stringRequest);
    }


}
