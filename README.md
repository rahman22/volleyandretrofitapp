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

### Volley StringRequest 

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



