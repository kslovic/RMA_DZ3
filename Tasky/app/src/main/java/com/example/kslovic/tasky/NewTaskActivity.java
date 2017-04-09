package com.example.kslovic.tasky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NewTaskActivity extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    public static final String TITLE = "Task title";
    public static final String CATEGORY = "Task category";
    public static final String PRIORITY = "Task priority";
    TextView tvNewTask;
    EditText etNewTask;
    Button bAddNewTask;
    Spinner sCategory;
    Spinner sPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        this.setUI();
    }

    private void setUI() {
        this.tvNewTask = (TextView) findViewById(R.id.tvNewTask);
        this.etNewTask = (EditText) findViewById(R.id.etNewTask);
        this.bAddNewTask = (Button) findViewById(R.id.bAddNewTask);
        sCategory = (Spinner) findViewById(R.id.sCategory);
        sPriority = (Spinner) findViewById(R.id.sPriority);

        this.bAddNewTask.setOnClickListener(this);
        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
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
        tTitle = etNewTask.getText().toString();
        tCategory = sCategory.getSelectedItem().toString();
        tPriority = sPriority.getSelectedItem().toString();
        Intent explicitIntent = new Intent(getApplicationContext(),ListActivity.class);
        explicitIntent.putExtra(TITLE, tTitle);
        explicitIntent.putExtra(CATEGORY, tCategory);
        explicitIntent.putExtra(PRIORITY, tPriority);
        this.startActivity(explicitIntent);
    }
}
