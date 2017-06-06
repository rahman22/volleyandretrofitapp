package com.example.thedeveloper.noteappdemo.mainpack;

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

import com.example.thedeveloper.noteappdemo.R;
import com.example.thedeveloper.noteappdemo.apiconfig.ShairedPreManager;
import com.example.thedeveloper.noteappdemo.noteadapterpack.ListOfDemoAdapter;
import com.example.thedeveloper.noteappdemo.notepackages.DemoList;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView lv;
    String libList[] = {"Retrofit 2.2.0", "Volley 1.0.19"};
    private DrawerLayout drawerLayout;

    private Toolbar toolbar;
    ArrayList<DemoList> demoLists;
    ListOfDemoAdapter demoAdapter;
    TextView profilename, profileemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.app_name);

        NavigationView navigationView = (NavigationView) findViewById(R.id.main_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        profilename = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profilenameText);
        profileemail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profileemailText);
        profilename.setText(ShairedPreManager.getmInstance(HomeActivity.this).getUserName());
        profileemail.setText(ShairedPreManager.getmInstance(HomeActivity.this).getUserEmail());


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //profilename.setText("hello i am rafique");


        demoLists = getInfo();
        lv = (ListView) findViewById(R.id.lib_list);
        demoAdapter = new ListOfDemoAdapter(HomeActivity.this, demoLists);
        lv.setAdapter(demoAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DemoList demoList = demoLists.get(position);

                if (demoList.getLibName().equals("Retrofit 2.2.0")) {

                    startActivity(new Intent(HomeActivity.this, RetrofitLib2.class));
                } else if (demoList.getLibName().equals("Volley 1.0.19")) {

                    startActivity(new Intent(HomeActivity.this, VolleyActivity.class));
                }


            }
        });

    }

    public ArrayList<DemoList> getInfo() {

        ArrayList<DemoList> demoLists = new ArrayList<>();
        demoLists.add(new DemoList("Retrofit 2.2.0", "Retrofit is a type-safe REST client for Android developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp"));
        demoLists.add(new DemoList("Volley 1.0.19", "Volley is a networking library for Android that manages network requests. It bundles the most important features you’ll need, such as accessing JSON APIs, loading images and String requests in an easier-to-use package."));
        return demoLists;

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logout_menu:
                ShairedPreManager.getmInstance(HomeActivity.this).logout();
                finish();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                break;

            case R.id.setting:

                Toast.makeText(HomeActivity.this, "setting menu ", Toast.LENGTH_SHORT).show();
                break;

        }

        return true;
    }
}
