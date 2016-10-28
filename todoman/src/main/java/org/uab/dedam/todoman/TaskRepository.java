package org.uab.dedam.todoman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by balazs.ujlaki on 28/10/2016.
 */

public class TaskRepository {
    private TaskDBOpenHelper taskDBOpenHelper;
    private SQLiteDatabase dbObj;

    public TaskRepository(Context context){
        this.taskDBOpenHelper = new TaskDBOpenHelper(context);
        //this.dbObj = this.taskDBOpenHelper.getWritableDatabase();
    }

    public Cursor getTasks(){
        this.dbObj = this.taskDBOpenHelper.getReadableDatabase();
        Cursor tasks = this.dbObj.query(TaskDBContract.TABLE_TASKS,null,null,null,null,null,null);
        dbObj.close();
        return tasks;
    }

    public void saveTask(String title, String description, String duedate){
        ContentValues values = new ContentValues();
        values.put(TaskDBContract.COLUMN_TITLE, title);
        values.put(TaskDBContract.COLUMN_DESCRIPTION, description);
        values.put(TaskDBContract.COLUMN_DUEDATE, duedate);
        values.put(TaskDBContract.COLUMN_DONE, 0);

        dbObj = this.taskDBOpenHelper.getWritableDatabase();
        this.dbObj.insert(TaskDBContract.TABLE_TASKS, null, values);
        dbObj.close();
    }

    public void setTaskDone(Integer id){
        ContentValues values = new ContentValues();
        values.put(TaskDBContract.COLUMN_DONE, 1);
        this.dbObj.update(TaskDBContract.TABLE_TASKS, values, "_ID=?", new String[]{id.toString()});
    }
}
