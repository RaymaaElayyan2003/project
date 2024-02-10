package com.example.studentsinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewActivity extends AppCompatActivity {
CircleImageView ImageView;
TextView UserNameAndID,UserDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ImageView =findViewById(R.id.profImage);
        UserNameAndID=findViewById(R.id.username_ID);
        UserDesc=findViewById(R.id.userDesc);


    int image=getIntent().getIntExtra("imageProf",1);
    String username=getIntent().getStringExtra("stuname_ID");
    String desc=getIntent().getStringExtra("stuDesc");
        ImageView.setImageResource(image);
        UserNameAndID.setText(username);
        UserDesc.setText(desc);
        Button acceptButton = findViewById(R.id.custom_button3);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, OrderDetailsActivity.class));
            }
        });
        Button RejectButton = findViewById(R.id.custom_button);
        RejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, RejectAct.class));
            }
        });
        Button AllOrder = findViewById(R.id.button2);
        AllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, AllOrder.class));
            }
        });



    }}
