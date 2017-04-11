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
    public static final String VALUE = "value";
    ListView lvTasksList;
    TaskAdapter tAdapter;
    Button bAddTask;
    Button bAddCategory;
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
        this.bAddCategory = (Button) findViewById(R.id.bAddCategory);

        bAddTask.setOnClickListener(this);
        bAddCategory.setOnClickListener(this);
        this.lvTasksList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {    Log.d(TAG,"klik");
                Task task = tAdapter.getItem(position);
                TaskDBHelper.getInstance(getApplicationContext()).deleteTask(task);
                tAdapter.deleteAt(position);
                return true;
            }
        });

    }
    private ArrayList<Task> loadTasks() {
        return TaskDBHelper.getInstance(this).getAllTasks();
    }

    @Override
    public void onClick(View v) {
        Intent explicitIntent = new Intent(getApplicationContext(),NewTaskActivity.class);
        switch(v.getId()){
            case R.id.bAddTask:
            explicitIntent.putExtra(VALUE, "task");
                break;
            case R.id.bAddCategory:
                explicitIntent.putExtra(VALUE, "category");
                break;
        }
        this.startActivity(explicitIntent);
    }
}
