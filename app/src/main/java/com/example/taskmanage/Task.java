package com.example.taskmanage;

import java.util.Calendar;
import java.util.Date;

public class Task {

    private String title;
    private String description;
    private Calendar date;
    private boolean completed;
    private int iconTask;

    public Task(String title, String description, Calendar date) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDate() {
        return date;
    }


    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
