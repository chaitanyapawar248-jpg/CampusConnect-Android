package com.example.campusconnect;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AttendanceActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    private int mathClasses, mathPresent;
    private int cnClasses, cnPresent;

    private TextView txtSummary;
    private TextView txtMath;
    private TextView txtCN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        preferences = getSharedPreferences(
                "CampusAttendance", MODE_PRIVATE);

        txtSummary = findViewById(R.id.txtAttendanceSummary);
        txtMath = findViewById(R.id.txtMathAttendance);
        txtCN = findViewById(R.id.txtCNAttendance);

        loadAttendance();

        findViewById(R.id.btnMathPresent).setOnClickListener(v ->
                markAttendance("math", true));

        findViewById(R.id.btnMathAbsent).setOnClickListener(v ->
                markAttendance("math", false));

        findViewById(R.id.btnCNPresent).setOnClickListener(v ->
                markAttendance("cn", true));

        findViewById(R.id.btnCNAbsent).setOnClickListener(v ->
                markAttendance("cn", false));

        updateDisplay();
    }

    private void loadAttendance() {
        mathClasses = preferences.getInt("mathClasses", 0);
        mathPresent = preferences.getInt("mathPresent", 0);

        cnClasses = preferences.getInt("cnClasses", 0);
        cnPresent = preferences.getInt("cnPresent", 0);
    }

    private void markAttendance(String subject, boolean present) {
        if (subject.equals("math")) {
            mathClasses++;
            if (present) {
                mathPresent++;
            }
        } else {
            cnClasses++;
            if (present) {
                cnPresent++;
            }
        }

        preferences.edit()
                .putInt("mathClasses", mathClasses)
                .putInt("mathPresent", mathPresent)
                .putInt("cnClasses", cnClasses)
                .putInt("cnPresent", cnPresent)
                .apply();

        updateDisplay();

        Toast.makeText(this,
                present ? "Marked Present" : "Marked Absent",
                Toast.LENGTH_SHORT).show();
    }

    private int percentage(int present, int classes) {
        if (classes == 0) {
            return 0;
        }

        return Math.round(present * 100f / classes);
    }

    private void updateDisplay() {
        txtMath.setText(String.format(Locale.getDefault(),
                "Classes: %d | Present: %d | Attendance: %d%%",
                mathClasses, mathPresent,
                percentage(mathPresent, mathClasses)));

        txtCN.setText(String.format(Locale.getDefault(),
                "Classes: %d | Present: %d | Attendance: %d%%",
                cnClasses, cnPresent,
                percentage(cnPresent, cnClasses)));

        int totalClasses = mathClasses + cnClasses;
        int totalPresent = mathPresent + cnPresent;

        txtSummary.setText(String.format(Locale.getDefault(),
                "Overall Attendance: %d%%",
                percentage(totalPresent, totalClasses)));
    }
}