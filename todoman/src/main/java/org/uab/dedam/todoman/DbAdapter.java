package org.uab.dedam.todoman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mon on 10/11/16.
 */
public class DbAdapter {
    private DataBaseToDoMan dataBaseToDoMan;
    private SQLiteDatabase sqLiteDatabase;

    //constructor
    public DbAdapter(Context context){
        this.dataBaseToDoMan=new DataBaseToDoMan(context);
    }

    //read-only
    public void openDatabaseForReadOnly(){
        this.sqLiteDatabase = this.dataBaseToDoMan.getReadableDatabase();
    }
    //write
    public void openDatabaseForWrite(){
        this.sqLiteDatabase= this.dataBaseToDoMan.getWritableDatabase();
    }

    //save task
    public void saveTask(String taskTitle, String taskDescription, String taskEndDate, String taskEndTime, Boolean taskDone){
        ContentValues values =new ContentValues();
        values.put(DataBaseToDoMan.TASK_TITLE,taskTitle);
        values.put(DataBaseToDoMan.TASK_DESCRIPTION,taskDescription);
        values.put(DataBaseToDoMan.TASK_END_DATE,taskEndDate);
        values.put(DataBaseToDoMan.TASK_END_TIME,taskEndTime);
        values.put(DataBaseToDoMan.TASK_DONE,taskDone);

        this.sqLiteDatabase.insert(
                DataBaseToDoMan.DB_TABLE_TASKS,
                null,
                values
        );
    }

    //return tasks
    public Cursor returnTasks(){
        Cursor result = this.sqLiteDatabase.query(
                DataBaseToDoMan.DB_TABLE_TASKS,
                DataBaseToDoMan.COLUMNS,
                null,
                null,
                null,
                null,
                null);

        return result;
    }


}
