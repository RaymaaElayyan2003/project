package com.example.studentsinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    RecyclerView rcv;
    List<Students> list = new ArrayList<>();
    RecyclerViewAdapter adapter;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = findViewById(R.id.recyclerView);
        /*Button LoginButton = findViewById(R.id.button3);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Loginactivity.class));
            }
        });*/
        rcv.setLayoutManager(new LinearLayoutManager(this));
//        setContentView(R.layout.activity_view);
//        ratingBar = (RatingBar) findViewById(R.id.OurRatingBar);
        // Call the method to fetch data from the database
        loadStudentRecyclerData();
//        String username[] = getResources().getStringArray(R.array.stuname_ID);
//        int ProfImages[] = {R.drawable.eddie, R.drawable.ff, R.drawable.mm, R.drawable.oo, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.vg, R.drawable.helo, R.drawable.poi, R.drawable.i};
//        String descForAll = getResources().getString(R.string.stuDesc);
//        for (int i = 0; i < username.length; i++) {
//            list.add(new Students(ProfImages[i % ProfImages.length], username[i], descForAll));
//        }
        adapter = new RecyclerViewAdapter(this, list);
        rcv.setAdapter(adapter);

    }

    // Method to fetch data from the database
    private void loadStudentRecyclerData() {
        String url = "http://10.0.2.2:5000/orders";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Students> studentsList = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONArray studentArray = response.getJSONArray(i);

                                // Extract data from JSON array and create Students object
                                String studentId = studentArray.getString(1);
                                String name = studentArray.getString(2);

                                // Add Students object to list
                                studentsList.add(new Students(studentId, name));
                            }
                            adapter.updateList(studentsList);

                        } catch (JSONException e) {
                            Log.e("JSON Parsing Error", "Error parsing JSON: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", "Error fetching students", error);
            }
        });

        queue.add(jsonArrayRequest);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        // Set up search query listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }

        });

        return true;


    }



}