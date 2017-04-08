package com.example.kslovic.tasky;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends Activity {
    ListView lvTasksList;
    TaskAdapter tAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setUpUI();
    }
    private void setUpUI() {
        this.lvTasksList = (ListView) this.findViewById(R.id.lvTaskList);
        this.tAdapter = new TaskAdapter(this.loadTasks());
        this.lvTasksList.setAdapter(this.tAdapter);
    }
    private ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("The Hobbit","J.R.R. Tolkien", Color.BLACK));
        tasks.add(new Task("The fellowship of the ring","J.R.R. Tolkien", Color.BLACK));
        return tasks;
    }
}
