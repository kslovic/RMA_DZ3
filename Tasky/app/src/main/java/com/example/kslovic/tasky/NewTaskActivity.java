package com.example.kslovic.tasky;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class NewTaskActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    public static final String TAG = "Kristina";
    private PreferenceManagement mPrefs;
    LinearLayout llNewTask;
    TextView tvNewTask;
    EditText etNewTask;
    Button bAddNewTask;
    Spinner sCategory;
    Spinner sPriority;
    LinearLayout llNewCategory;
    TextView tvNewCategory;
    EditText etNewCategory;
    Button bAddNewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new);
        this.setUI();
    }

    private void setUI() {
        this.mPrefs = new PreferenceManagement();
        this.llNewTask = (LinearLayout) findViewById(R.id.llNewTask);
        this.tvNewTask = (TextView) findViewById(R.id.tvNewTask);
        this.etNewTask = (EditText) findViewById(R.id.etNewTask);
        this.bAddNewTask = (Button) findViewById(R.id.bAddNewTask);
        sCategory = (Spinner) findViewById(R.id.sCategory);
        sPriority = (Spinner) findViewById(R.id.sPriority);
        this.llNewCategory = (LinearLayout) findViewById(R.id.llNewCategory);
        this.tvNewCategory = (TextView) findViewById(R.id.tvNewCategory);
        this.etNewCategory = (EditText) findViewById(R.id.etNewCategory);
        this.bAddNewCategory = (Button) findViewById(R.id.bAddNewCategory);

        Intent startingIntent = this.getIntent();
        if (startingIntent.hasExtra(ListActivity.VALUE)) {
            String value = startingIntent.getStringExtra(ListActivity.VALUE);
            Log.d(TAG, value);
            switch (value) {
                case "task":
                    Log.d(TAG, "uslo");
                    llNewTask.setVisibility(View.VISIBLE);
                    break;
                case "category":
                    llNewCategory.setVisibility(View.VISIBLE);
                    break;
            }
        }
        this.bAddNewTask.setOnClickListener(this);
        this.bAddNewCategory.setOnClickListener(this);

        ArrayList<String> listOldCategories = new ArrayList<String>(
                Arrays.asList("Faculty", "Shopping", "Work", "Other"));
        if (null != this.mPrefs) {
            Set<String> set;
            set = this.mPrefs.retrieveCategory(this);
            List<String> listNew = new ArrayList<String>(set);
            Set<String> newSet = new HashSet<String>(listOldCategories);
            newSet.addAll(listNew);
            listOldCategories = new ArrayList<String>(newSet);
        }
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOldCategories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategory.setAdapter(adapterCategory);
        ArrayAdapter<CharSequence> adapterPriority = ArrayAdapter.createFromResource(this, R.array.priority_array, android.R.layout.simple_spinner_item);
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sPriority.setAdapter(adapterPriority);
        sCategory.setOnItemSelectedListener(this);
        sPriority.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        String tTitle, tCategory, tPriority;
        switch(v.getId()){
            case R.id.bAddNewTask:
                tTitle = etNewTask.getText().toString();
                tCategory = sCategory.getSelectedItem().toString();
                tPriority = sPriority.getSelectedItem().toString();
                if(tTitle.isEmpty()){
                    Toast.makeText(this, "You must enter task name!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int tColor = Color.GREEN;
                    switch (tPriority) {
                        case "Medium":
                            tColor = Color.YELLOW;
                            break;
                        case "High":
                            tColor = Color.RED;
                            break;
                    }
                    Task task = new Task(tTitle, tCategory, tColor);
                    TaskDBHelper.getInstance(getApplicationContext()).insertTask(task);
                    Intent explicitIntent = new Intent(getApplicationContext(), ListActivity.class);
                    this.startActivity(explicitIntent);
                }
                break;
            case R.id.bAddNewCategory:
                tCategory = etNewCategory.getText().toString();
                if(tCategory.isEmpty()){
                    Toast.makeText(this, "You must enter category name!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (null != this.mPrefs) {
                        Set<String> setCategory;
                        setCategory = this.mPrefs.retrieveCategory(this);
                        List<String> listSet = new ArrayList<String>(setCategory);
                        listSet.add(tCategory);
                        setCategory = new HashSet<String>(listSet);
                        mPrefs.saveCategory(getApplicationContext(), setCategory);
                    }
                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                    this.startActivity(intent);
                }
                break;
        }
        }
}
