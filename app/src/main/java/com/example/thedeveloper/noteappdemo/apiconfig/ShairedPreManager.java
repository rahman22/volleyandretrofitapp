package com.example.thedeveloper.noteappdemo.apiconfig;

import android.content.Context;
import android.content.SharedPreferences;

public class ShairedPreManager {

    private static ShairedPreManager mInstance;
    private static Context context;

    private static final String SHAIRED_PRE = "myshairedpress";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "name";
    private static final String KEY_EMAIL = "email";


    public ShairedPreManager(Context context) {
        this.context = context;

    }

    public static synchronized ShairedPreManager getmInstance(Context context) {

        if (mInstance == null) {

            mInstance = new ShairedPreManager(context);

        }

        return mInstance;

    }


    public boolean userLogin(int id, String name, String email) {

        SharedPreferences preferences = context.getSharedPreferences(SHAIRED_PRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(KEY_ID, id);
        editor.putString(KEY_USERNAME, name);
        editor.putString(KEY_EMAIL, email);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn() {

        SharedPreferences preferences = context.getSharedPreferences(SHAIRED_PRE, Context.MODE_PRIVATE);

        if (preferences.getString(KEY_USERNAME, null) != null) {

            return true;
        }
        return false;
    }

    public boolean logout() {

        SharedPreferences preferences = context.getSharedPreferences(SHAIRED_PRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


    public String getUserName() {
        SharedPreferences preferences = context.getSharedPreferences(SHAIRED_PRE, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USERNAME, null);
    }

    public String getUserEmail() {
        SharedPreferences preferences = context.getSharedPreferences(SHAIRED_PRE, Context.MODE_PRIVATE);
        return preferences.getString(KEY_EMAIL, null);
    }

}
