package com.example.taskmanage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private TaskList taskList;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private CalendarView calendarView;
    private Calendar taskDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        taskList = new TaskList();
        // Load tasks from SharedPreferences
        taskList.getTasks(this);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDesc);
        calendarView = findViewById(R.id.calendar);

        taskDate = Calendar.getInstance();
        calendarView.setDate(taskDate.getTimeInMillis());

        // Set the calendar view listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                taskDate.set(Calendar.YEAR, year);
                taskDate.set(Calendar.MONTH, month);
                taskDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        });

        Button saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                Task task = new Task(title, description, taskDate);
                taskList.addTask(task);
                taskList.saveTasks(AddActivity.this);

                setResult(RESULT_OK);
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        taskList.saveTasks(this);
        super.onDestroy();
    }

}
