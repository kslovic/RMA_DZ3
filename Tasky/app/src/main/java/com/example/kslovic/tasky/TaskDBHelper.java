package com.example.kslovic.tasky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TaskDBHelper extends SQLiteOpenHelper {
    // Singleton
    private static TaskDBHelper mTaskDBHelper = null;
    private TaskDBHelper (Context context){
        super(context.getApplicationContext(),Schema.DATABASE_NAME,null,Schema.SCHEMA_VERSION);
    }
    public static synchronized TaskDBHelper getInstance(Context context){
        if(mTaskDBHelper == null){
            mTaskDBHelper = new TaskDBHelper(context);
        }
        return mTaskDBHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MY_TASKS);}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_MY_TASKS);
        this.onCreate(db);
    }
    //SQL statements
    static final String CREATE_TABLE_MY_TASKS = "CREATE TABLE " + Schema.TABLE_MY_TASKS + " (" + Schema.TITLE + " TEXT," + Schema.CATEGORY + " TEXT," + Schema.PRIORITY + " INTEGER);";
    static final String DROP_TABLE_MY_TASKS = "DROP TABLE IF EXISTS " + Schema.TABLE_MY_TASKS;
    static final String SELECT_ALL_TASKS = "SELECT " + Schema.TITLE + "," + Schema.CATEGORY + "," + Schema.PRIORITY + " FROM " + Schema.TABLE_MY_TASKS;
    // CRUD should be performed on another thread
    public void insertTask(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.TITLE, task.gettTitle());
        contentValues.put(Schema.CATEGORY, task.gettCategory());
        contentValues.put(Schema.PRIORITY, task.gettPriority());
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        writeableDatabase.insert(Schema.TABLE_MY_TASKS, Schema.TITLE,contentValues);
        writeableDatabase.close();
    }
    public void deleteTask(Task task){
        String title = task.gettTitle();
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        writeableDatabase.delete(Schema.TABLE_MY_TASKS, Schema.TITLE + "='" + title+ "'",null);
        writeableDatabase.close();
    }
    public ArrayList<Task> getAllTasks(){
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        Cursor taskCursor = writeableDatabase.rawQuery(SELECT_ALL_TASKS,null);
        ArrayList<Task> tasks = new ArrayList<>();
        if(taskCursor.moveToFirst()){
            do{
                String title = taskCursor.getString(0);
                String category = taskCursor.getString(1);
                int priority = taskCursor.getInt(2);
                tasks.add(new Task(title, category, priority));
            }while(taskCursor.moveToNext());
        }
        taskCursor.close();
        writeableDatabase.close();
        return tasks;
    }
    public static class Schema{
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "tasks.db";

        static final String TABLE_MY_TASKS = "my_tasks";
        static final String TITLE = "title";
        static final String CATEGORY = "category";
        static final String PRIORITY = "priority";

    }
}
