package com.example.studentsinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginactivity extends AppCompatActivity {
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "PASSWORD";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText editTextText2;
    private EditText editTextText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextText2 = findViewById(R.id.editTextText2);
        editTextText = findViewById(R.id.editTextText);
        setupPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();

        String username = preferences.getString(EMAIL, "");
        String password = preferences.getString(PASSWORD, "");
        editTextText2.setText(username);
        editTextText.setText(password);
    }

    private void setupPreferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        String username = preferences.getString(EMAIL, "");
        String password = preferences.getString(PASSWORD, "");
        editTextText2.setText(username);
        editTextText.setText(password);
    }

    private boolean authenticateUser(String username, String password) {
        String storedUsername = preferences.getString(EMAIL, "");
        String storedPassword = preferences.getString(PASSWORD, "");

        if (username.equals("") || password.equals("") || storedUsername.equals("") || storedPassword.equals("")) {
            return false;
        }

        if (storedUsername.equals(username) && storedPassword.equals(password)) {
            return true;
        }
        return false;
    }

    public void btnSignInOnClick(View view) {
        String username = editTextText2.getText().toString();
        String password = editTextText.getText().toString();

        if (authenticateUser(username, password)) {
            Intent intent2 = new Intent(this, MainActivity.class);
            startActivity(intent2);
        } else {
            Toast.makeText(getApplicationContext(), "Please make sure your information is correct", Toast.LENGTH_SHORT).show();
        }
    }


    public void btnRegisterOnClick(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}