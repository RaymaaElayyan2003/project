package com.example.studentsinfo;
import android.content.Context;

public class DatabaseHelper {

    public boolean updateOrderStatus(String orderId, String status) {
        System.out.println("Updating status of order " + orderId + " to: " + status);


        return true;
    }
}