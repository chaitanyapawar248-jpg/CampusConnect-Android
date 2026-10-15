package com.example.campusconnect;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Button btnTech = findViewById(R.id.btnRegisterTech);
        Button btnCultural = findViewById(R.id.btnRegisterCultural);
        Button btnSports = findViewById(R.id.btnRegisterSports);

        btnTech.setOnClickListener(v ->
                registerEvent("Tech Fest 2026"));

        btnCultural.setOnClickListener(v ->
                registerEvent("Cultural Fest"));

        btnSports.setOnClickListener(v ->
                registerEvent("Sports Day"));
    }

    private void registerEvent(String eventName) {
        Toast.makeText(
                this,
                "Registration successful for " + eventName,
                Toast.LENGTH_LONG
        ).show();
    }
}