package com.example.campusconnect;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtName, edtRoll, edtBranch, edtEmail;
    private TextView txtName, txtBranch;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtName = findViewById(R.id.edtProfileName);
        edtRoll = findViewById(R.id.edtRollNumber);
        edtBranch = findViewById(R.id.edtProfileBranch);
        edtEmail = findViewById(R.id.edtProfileEmail);

        txtName = findViewById(R.id.txtProfileName);
        txtBranch = findViewById(R.id.txtProfileBranch);

        Button btnSave = findViewById(R.id.btnSaveProfile);

        preferences = getSharedPreferences(
                "StudentProfile",
                MODE_PRIVATE
        );

        loadProfile();

        btnSave.setOnClickListener(v -> saveProfile());
    }

    private void saveProfile() {
        String name = edtName.getText().toString().trim();
        String roll = edtRoll.getText().toString().trim();
        String branch = edtBranch.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (name.isEmpty() || roll.isEmpty()
                || branch.isEmpty() || email.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        preferences.edit()
                .putString("name", name)
                .putString("roll", roll)
                .putString("branch", branch)
                .putString("email", email)
                .apply();

        updateHeader(name, branch);

        Toast.makeText(
                this,
                "Profile saved successfully",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void loadProfile() {
        String name = preferences.getString("name", "");
        String roll = preferences.getString("roll", "");
        String branch = preferences.getString("branch", "");
        String email = preferences.getString("email", "");

        edtName.setText(name);
        edtRoll.setText(roll);
        edtBranch.setText(branch);
        edtEmail.setText(email);

        updateHeader(name, branch);
    }

    private void updateHeader(String name, String branch) {
        txtName.setText(
                name.isEmpty() ? "Student Name" : name
        );

        txtBranch.setText(
                branch.isEmpty() ? "Branch and Year" : branch
        );
    }
}