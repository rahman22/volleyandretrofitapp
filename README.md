## Volley

Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster
### Sending a Simple Request
At a high level, you use Volley by creating a RequestQueue and passing it Request objects. The RequestQueue manages worker threads for running the network operations, reading from and writing to the cache, and parsing responses. Requests do the parsing of raw responses and Volley takes care of dispatching the parsed response back to the main thread for delivery

## Retrofit

Retrofit is a REST Client for Android and Java by Square. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. In Retrofit you configure which converter is used for the data serialization
## Volley Dependencies

```xml

compile 'com.mcxiaoke.volley:library:1.0.19'

```

## Volley Singleton Pattern

```java
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

```

### Volley StringRequest GET Method Display Data

```java

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

```

### Volley StringRequest POST Method Insert Data

```java

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

```

## Retrofit Dependencies

```xml
compile 'com.squareup.retrofit2:retrofit:2.2.0'
compile 'com.google.code.gson:gson:2.8.0'
compile 'com.squareup.retrofit2:converter-gson:2.2.0'

```

### Retrofit Api Use

```java

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

```

### Retrofit GET Request Display Data

```java

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

```


### Retrofit POST Request Insert Data

```java
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

```
