package com.example.taskmanage;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class TaskList {
    private static final String SHARED_PREFERENCES_FILE_NAME = "tasks";
    private static final String TASKS_KEY = "tasks";

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }
    public int size() {
        return this.tasks.size();
    }
    public void getTasks(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        String jsonTasks = sharedPreferences.getString(TASKS_KEY, "");

        if (!jsonTasks.isEmpty()) {
            try {
                tasks = new Gson().fromJson(jsonTasks, new TypeToken<ArrayList<Task>>() {}.getType());
            } catch (Exception e) {
                Log.e("TaskList", "Error loading tasks: " + e.getMessage());
            }
        }
    }

    public void saveTasks(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String storedTasks = new Gson().toJson(tasks);
        editor.putString(TASKS_KEY, storedTasks);
        editor.apply();
    }



}

