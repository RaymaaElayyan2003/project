package com.example.studentsinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.table_details);
        Button kitchenButton = findViewById(R.id.kitchenButton);
        kitchenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, ToKitchen.class));
            }
        });
        Button readyButton = findViewById(R.id.readyButton);
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, ReadyForPickup.class));
            }
        });
        Button receiveButton = findViewById(R.id.receiveButton);
        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, ReceiveOrder.class));
            }
        });
        /*public void Go(View view) {
            String orderId = (String) view.getTag();

            boolean success = DatabaseHelper.updateOrderStatus(orderId, "Order directed to kitchen");
            if (success) {
                Toast.makeText(this, "Order directed to kitchen", Toast.LENGTH_SHORT).show();

                 } else {
               Toast.makeText(this, "Failed to update order status", Toast.LENGTH_SHORT).show();
            }
        }

        public void onReadyButtonClick(View view) {
            String orderId = (String) view.getTag();

           boolean success = DatabaseHelper.updateOrderStatus(orderId, "Waiting to be picked");
            if (success) {
               Toast.makeText(this, "Order status updated to Waiting to be picked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to update order status", Toast.LENGTH_SHORT).show();
            }
        }


        public void onReceiveButtonClick(View view) {

            String orderId = (String) view.getTag();


            boolean success = DatabaseHelper.updateOrderStatus(orderId, "Order done delivered");
            if (success) {

                Toast.makeText(this, "Order received successfully", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Failed to update order status", Toast.LENGTH_SHORT).show();
}
        }

    */
    }
}