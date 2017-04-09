package com.example.kslovic.tasky;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class ListActivity extends Activity implements View.OnClickListener {
    ListView lvTasksList;
    TaskAdapter tAdapter;
    Button bAddTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.setUpUI();
    }
    private void setUpUI() {
        this.lvTasksList = (ListView) this.findViewById(R.id.lvTaskList);
        this.tAdapter = new TaskAdapter(this.loadTasks());
        this.lvTasksList.setAdapter(this.tAdapter);
        this.bAddTask=(Button) findViewById(R.id.bAddTask);

        bAddTask.setOnClickListener(this);
        this.lvTasksList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {    Log.d(TAG,"klik");
                tAdapter.deleteAt(position);
                return true;
            }
        });
        Intent startingIntent = this.getIntent();
        if(startingIntent.hasExtra(NewTaskActivity.TITLE)&&startingIntent.hasExtra(NewTaskActivity.CATEGORY)&&startingIntent.hasExtra(NewTaskActivity.PRIORITY)){
            String title = startingIntent.getStringExtra(NewTaskActivity.TITLE);
            String category = startingIntent.getStringExtra(NewTaskActivity.CATEGORY);
            String priority = startingIntent.getStringExtra(NewTaskActivity.PRIORITY);
            int tColor= Color.GREEN;
            Log.d(TAG,priority);
            switch(priority){
                case "Medium":
                    tColor = Color.YELLOW;
                    break;
                case "High":
                    tColor = Color.RED;
                    break;
            }
            tAdapter.add(new Task(title,category, tColor));
        }
    }
    private ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("The Hobbit","J.R.R. Tolkien", Color.BLACK));
        tasks.add(new Task("The fellowship of the ring","J.R.R. Tolkien", Color.BLACK));
        return tasks;
    }

    @Override
    public void onClick(View v) {
        Intent explicitIntent = new Intent(getApplicationContext(),NewTaskActivity.class);
        this.startActivity(explicitIntent);

    }
}
