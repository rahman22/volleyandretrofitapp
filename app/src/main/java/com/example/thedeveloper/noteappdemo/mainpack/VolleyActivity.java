package com.example.thedeveloper.noteappdemo.mainpack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.thedeveloper.noteappdemo.R;
import com.example.thedeveloper.noteappdemo.apiconfig.HelperVolley;
import com.example.thedeveloper.noteappdemo.apiconfig.ShairedPreManager;
import com.example.thedeveloper.noteappdemo.noteadapterpack.VolleyListAdapter;
import com.example.thedeveloper.noteappdemo.notepackages.ModelHr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ListView listView;
    private VolleyListAdapter adapter;
    private ArrayList<ModelHr> list;
    public ProgressDialog dialog;
    public static final String BASE_URL = "https://0301swe28.000webhostapp.com/slimrestapi/public/volley.php/volleylist";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Volley 1.0.19");
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        TextView profilename = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profilenameText);
        TextView profileemail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profileemailText);
        profilename.setText(ShairedPreManager.getmInstance(VolleyActivity.this).getUserName());
        profileemail.setText(ShairedPreManager.getmInstance(VolleyActivity.this).getUserEmail());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(VolleyActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        listView = (ListView) findViewById(R.id.volley_list);
        dialog = new ProgressDialog(this);
        showHrListData();

    }


    public void showHrListData() {

        dialog.setMessage("Loading wait .....");
        dialog.show();


        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        list = new ArrayList<>();

                        try {

                            JSONArray array = response.getJSONArray("volley_list");

                            for (int i = 0; i < array.length(); i++) {

                                ModelHr modelHr = new ModelHr();
                                JSONObject jsonObject = array.getJSONObject(i);
                                modelHr.setHrId(jsonObject.getInt("hr_id"));
                                modelHr.setCompanyName(jsonObject.getString("hr_name"));
                                modelHr.setCompanyNumber(jsonObject.getString("hr_num"));
                                modelHr.setCompanyEmail(jsonObject.getString("hr_mail"));
                                list.add(modelHr);

                            }
                            dialog.dismiss();
                            adapter = new VolleyListAdapter(VolleyActivity.this, list);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    ModelHr modelHr = list.get(position);
                                    Toast.makeText(VolleyActivity.this, "if and else " + modelHr.getHrId(), Toast.LENGTH_SHORT).show();

                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        Toast.makeText(VolleyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        HelperVolley.getmInstance(this).addToRequestQueue(request);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        switch(item.getItemId()){

            case R.id.volley_back_menu:
            startActivity(new Intent(VolleyActivity.this , HomeActivity.class));
            break;

            case R.id.add_menu_volley:
            startActivity(new Intent(VolleyActivity.this , AddVolleyActivity.class));
            break;
        }



        return true;
    }
}
