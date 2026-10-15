package com.example.campusconnect;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Button btnMath = findViewById(R.id.btnMathNotes);
        Button btnCN = findViewById(R.id.btnCNNotes);
        Button btnDBMS = findViewById(R.id.btnDBMSNotes);

        btnMath.setOnClickListener(v ->
                showResources("Mathematics",
                        "Formulas, class notes and practice problems"));

        btnCN.setOnClickListener(v ->
                showResources("Computer Networks",
                        "Networking concepts, protocols and revision notes"));

        btnDBMS.setOnClickListener(v ->
                showResources("Database Management Systems",
                        "SQL queries, database design and normalization"));
    }

    private void showResources(String subject, String resources) {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(subject)
                .setMessage(resources)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}