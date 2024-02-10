package com.example.studentsinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AllOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Array of student records
        String[][] studentRecords = {
                {"Ahmad Ali", "12345", "67890", "54321", "555-555-5555", "Delivered"},
                {"Fatima Ahmed", "10101", "20202", "30303", "056-345-6789", "Pending"},
                {"Youssef Mansour", "24680", "13579", "98765", "123-456-7890", "In Progress"},
                {"Layla Hassan", "77777", "88888", "99999", "987-654-3210", "Delivered"},
                {"Omar Mahmoud", "23456", "34567", "45678", "789-012-3456", "Pending"}
        };

        // Add data rows dynamically
        for (int i = 0; i < studentRecords.length; i++) {
            final String orderId = studentRecords[i][2]; // Get the Order ID for each record

            TableRow row = new TableRow(this);

            // Add data for each column in the record
            for (int j = 0; j < studentRecords[i].length; j++) {
                TextView textView = createTextView(studentRecords[i][j], 1);

                // Add OnClickListener to Order ID column
                if (j == 2) {
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Navigate to OrderDetailsActivity passing the order ID
                            Intent intent = new Intent(AllOrder.this, OrderDetailsActivity.class);
                            intent.putExtra("order_id", orderId);
                            startActivity(intent);
                        }
                    });
                }

                row.addView(textView);
            }

            // Add the row to the table layout
            tableLayout.addView(row);
        }
    }

    // Helper method to create TextView with specified text and weight
    private TextView createTextView(String text, float weight) {
        TextView textView = new TextView(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, weight);
        textView.setLayoutParams(layoutParams);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        return textView;
}
}
