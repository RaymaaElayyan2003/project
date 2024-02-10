package com.example.studentsinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SearchView;

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

        String username[] = getResources().getStringArray(R.array.stuname_ID);
        int ProfImages[] = {R.drawable.eddie, R.drawable.ff, R.drawable.mm, R.drawable.oo, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.vg, R.drawable.helo, R.drawable.poi, R.drawable.i};

        String descForAll = getResources().getString(R.string.stuDesc);

        for (int i = 0; i < username.length; i++) {
            list.add(new Students(ProfImages[i % ProfImages.length], username[i], descForAll));
        }


        adapter = new RecyclerViewAdapter(this, list);
        rcv.setAdapter(adapter);

    }public void submitStars(View view) {
//        RatingBar ratingBar = findViewById(R.id.RatingBar); // Get the RatingBar instance from the layout
        System.out.println("Stars Amount:" + ratingBar.getRating());
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
                // any logic?
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter adapter's data based on the new text//هون مشعارفه
                adapter.getFilter().filter(newText);
                return true;
            }

        });

        return true;


    }



}