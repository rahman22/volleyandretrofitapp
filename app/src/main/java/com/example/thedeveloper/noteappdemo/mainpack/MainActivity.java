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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BASE_URL = "https://0301swe28.000webhostapp.com/slimrestapi/public/volley.php/login";

    TextView not_register;
    EditText email, passcode;
    Button loginButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ShairedPreManager.getmInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Login Activity");
        setSupportActionBar(toolbar);
        email = (EditText) findViewById(R.id.loginEmailText);
        passcode = (EditText) findViewById(R.id.loginpasswordText);
        loginButton = (Button) findViewById(R.id.login_Button);
        loginButton.setOnClickListener(this);
        not_register = (TextView) findViewById(R.id.not_text_register);
        not_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

    }


    @Override
    public void onClick(View v) {

        progressDialog = new ProgressDialog(MainActivity.this);
        final String email_id = email.getText().toString().trim();
        final String password = passcode.getText().toString().trim();


        if (email_id.equals("")) {
            Toast.makeText(MainActivity.this, "please enter email id", Toast.LENGTH_SHORT).show();
            return;
        }


        if (password.equals("")) {
            Toast.makeText(MainActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Wait Login Progress .....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (!jsonObject.getBoolean("error")) {

                        ShairedPreManager.getmInstance(MainActivity.this).userLogin(
                                jsonObject.getInt("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("email")

                    );

                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        finish();

                    } else {
                        //progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "error login " + error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email", email_id);
                params.put("password", password);

                return params;
            }
        };


        HelperVolley.getmInstance(this).addToRequestQueue(stringRequest);

    }

}
