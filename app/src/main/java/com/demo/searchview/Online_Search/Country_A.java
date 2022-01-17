package com.demo.searchview.Online_Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.demo.searchview.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Country_A extends AppCompatActivity {
    ArrayList<Country_Model> country_modelArrayList = new ArrayList<>();
    RecyclerView recyclerView_event;
    SharedPreferences sharedPreferences;
    EditText Searchtext;
    private Country_Adapter adapter;
    ImageView img_add;
    String str_url = "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/index.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ((TextView) findViewById(R.id.toolbr_lbl)).setText("Country");
        findViewById(R.id.imgbck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        country_modelArrayList = new ArrayList<>();
        img_add = findViewById(R.id.img_add);
        recyclerView_event = findViewById(R.id.recyclerView_data);
        recyclerView_event.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        getBloodList();
        this.Searchtext = (EditText) findViewById(R.id.search_input);
        this.Searchtext.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                filterQuery(editable.toString());
            }
        });

    }

    private void getBloodList() {
        final ProgressDialog progressDialog = new ProgressDialog(Country_A.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, str_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("quick_1", "onResponse: " + response);
                try {
                    country_modelArrayList = new ArrayList<>();

                    JSONArray jsonArrayvideo = new JSONArray(response);
                    for (int i = 0; i < jsonArrayvideo.length(); i++) {
                        Country_Model video = new Country_Model();

                        JSONObject jsonObject1 = jsonArrayvideo.getJSONObject(i);
                        video.name = jsonObject1.getString("name");
                        video.code = jsonObject1.getString("code");
                        video.image = jsonObject1.getString("image");
                        country_modelArrayList.add(video);
                    }
                    adapter = (new Country_Adapter(country_modelArrayList, Country_A.this));
                    recyclerView_event.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap();
                // params.put("user_id", str_userid);
                Log.d("parameter_tab", "getParams: " + params);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                return header;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        RetryPolicy retryPolicy = new DefaultRetryPolicy(3000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        requestQueue.add(stringRequest);

    }

    public void filterQuery(String text) {
        ArrayList<Country_Model> filterdNames = new ArrayList<>();
        for (Country_Model s : this.country_modelArrayList) {
            if (s.name.toLowerCase().contains(text) || s.name.toUpperCase().contains(text)) {
                filterdNames.add(s);
            }
        }
        this.adapter.setFilter(filterdNames);
    }
}