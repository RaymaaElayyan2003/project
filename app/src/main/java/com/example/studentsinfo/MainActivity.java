package com.example.studentsinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcv;
    List<Students> list = new ArrayList<>();
    RecyclerViewAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        rcv.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the string array resource
        String username[] = getResources().getStringArray(R.array.stuname_ID);

        // Generate image resources if needed
        int ProfImages[] = {R.drawable.eddie, R.drawable.ff, R.drawable.mm, R.drawable.oo, R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.vg, R.drawable.helo, R.drawable.poi, R.drawable.i};

        // Get the common description
        String descForAll = getResources().getString(R.string.stuDesc);

        // Populate the list with Students objects
        for (int i = 0; i < username.length; i++) {
            list.add(new Students(ProfImages[i % ProfImages.length], username[i], descForAll));
        }

        // Set up the adapter with the list
        adapter = new RecyclerViewAdapter(this, list);
        rcv.setAdapter(adapter);

        // Set up search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private void filter(String newText) {
        List<Students> filteredList = new ArrayList<>();

        for (Students student : list) {
            if (student.getUserName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(student);
            }
        }

        adapter.updateList(filteredList);
    }
}