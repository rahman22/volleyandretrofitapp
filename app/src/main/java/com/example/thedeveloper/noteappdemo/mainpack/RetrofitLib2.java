package com.example.thedeveloper.noteappdemo.mainpack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thedeveloper.noteappdemo.R;
import com.example.thedeveloper.noteappdemo.apiconfig.ApiRetrofitLib;
import com.example.thedeveloper.noteappdemo.apiconfig.ShairedPreManager;
import com.example.thedeveloper.noteappdemo.noteadapterpack.NoteListAdapter;
import com.example.thedeveloper.noteappdemo.notepackages.ListHr;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitLib2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    //public static final String ENDURL = "http://10.0.3.2/slimrestapi/public/retrofit.php/";
    public static final String ENDURL = "https://0301swe28.000webhostapp.com/slimrestapi/public/retrofit.php/";
    Toolbar toolbar;
    ListView details_list;
    NoteListAdapter mylistadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_activity);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Retrofit 2.2.0");

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        TextView profilename = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profilenameText);
        TextView profileemail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profileemailText);
        profilename.setText(ShairedPreManager.getmInstance(RetrofitLib2.this).getUserName());
        profileemail.setText(ShairedPreManager.getmInstance(RetrofitLib2.this).getUserEmail());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(RetrofitLib2.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        details_list = (ListView) findViewById(R.id.retrofit_list);
        displayNewData();
    }

    public void displayNewData() {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(ENDURL)
                .addConverterFactory(GsonConverterFactory.create()).build();


        ApiRetrofitLib lib = retrofit.create(ApiRetrofitLib.class);

        Call<ListHr> call = lib.getHrList();

        call.enqueue(new Callback<ListHr>() {

            @Override
            public void onResponse(Call<ListHr> call, Response<ListHr> response) {

                mylistadapter = new NoteListAdapter(RetrofitLib2.this, response.body().getGetList());
                details_list.setAdapter(mylistadapter);
            }

            @Override
            public void onFailure(Call<ListHr> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.retrofit_back_menu:
                startActivity(new Intent(RetrofitLib2.this, HomeActivity.class));
                break;

            case R.id.add_menu_retrofit:
                startActivity(new Intent(RetrofitLib2.this, HrAddActivity.class));
                break;
        }


        return true;
    }
}
