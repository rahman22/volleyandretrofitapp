package com.example.thedeveloper.noteappdemo.apiconfig;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by The Developer on 6/3/17.
 */

public class HelperVolley {

    private static HelperVolley mInstance;
    private RequestQueue mrequestQueue;
    private Context context;

    public HelperVolley(Context context) {
        this.context = context;
        mrequestQueue = getRequestQueue();
    }


    public static synchronized HelperVolley getmInstance(Context context) {

        if (mInstance == null) {

            mInstance = new HelperVolley(context);

        }

        return mInstance;

    }


    public RequestQueue getRequestQueue() {

        if (mrequestQueue == null) {

            mrequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mrequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> request) {

        getRequestQueue().add(request);
    }


}
