package com.example.studentsinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationActivity extends AppCompatActivity {
    public static final String FULLNAME = "FULLNAME";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "PASSWORD";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText edtName;
    private EditText edtRegistrationPassword;
    private EditText edtRegistrationEmail;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setViews();
        setupPreferences();
    }

    private void setViews() {
        edtName = findViewById(R.id.editTextText3);
        edtRegistrationPassword = findViewById(R.id.editTextTextpassword);
        edtRegistrationEmail = findViewById(R.id.editTextTextemail);
        btnSignUp = findViewById(R.id.button4);
    }

    private void setupPreferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }

    public void btnSignUpOnClick(View view) {
        String employeeName = edtName.getText().toString();
        String employeePassword = edtRegistrationPassword.getText().toString();
        String employeeEmail = edtRegistrationEmail.getText().toString();
        if (!employeeName.isEmpty() && !employeePassword.isEmpty() && !employeeEmail.isEmpty()) {
            editor.putString(FULLNAME, employeeName);
            editor.putString(PASSWORD, employeePassword);
            editor.putString(EMAIL, employeeEmail);
            editor.apply();

            addEmployeeToDb(employeeName, employeePassword, employeeEmail);

            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void addEmployeeToDb(String employeeName, String employeePassword, String employeeEmail) {
        String url = "http://10.0.2.2:5000/create/employee";
        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject registrationData = new JSONObject();
        try {
            registrationData.put("name", employeeName);
            registrationData.put("password", employeePassword);
            registrationData.put("email", employeeEmail);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, registrationData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error during registration: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
    }

}