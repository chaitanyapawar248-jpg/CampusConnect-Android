
package com.example.campusconnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom);
                    return insets;
                });

        Button btnTimetable = findViewById(R.id.btnTimetable);
        Button btnAttendance = findViewById(R.id.btnAttendance);
        Button btnEvents = findViewById(R.id.btnEvents);
        Button btnNotes = findViewById(R.id.btnNotes);
        Button btnAnnouncements = findViewById(R.id.btnAnnouncements);


        btnTimetable.setOnClickListener(v -> {
            android.content.Intent intent =
                    new android.content.Intent(
                            MainActivity.this,
                            TimetableActivity.class
                    );
            startActivity(intent);
        });


        btnAttendance.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AttendanceActivity.class
            );
            startActivity(intent);
        });

        btnEvents.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    EventsActivity.class
            );
            startActivity(intent);
        });

        btnNotes.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    NotesActivity.class
            );
            startActivity(intent);
        });

        btnAnnouncements.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AnnouncementsActivity.class
            );
            startActivity(intent);
        });
    }

    private void showMessage(String feature) {
        Toast.makeText(this,
                feature + " - Coming Soon!",
                Toast.LENGTH_SHORT).show();
    }
}
