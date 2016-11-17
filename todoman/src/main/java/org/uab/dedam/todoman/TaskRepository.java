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
        this.dbObj = this.taskDBOpenHelper.getWritableDatabase();
    }

    public Cursor getTasks(){
        Cursor tasks = this.dbObj.query(TaskDBContract.TABLE_TASKS,null,null,null,null,null,null);
        return tasks;
    }

    public Cursor getTasksSorted(){
        Cursor tasks = this.dbObj.query(TaskDBContract.TABLE_TASKS,null,null,null,null,null,TaskDBContract.COLUMN_DUEDATE);
        return tasks;
    }

    public Cursor getTaskByID(int todoId){
        String[] args = {String.valueOf(todoId)};
        Cursor task = this.dbObj.query(TaskDBContract.TABLE_TASKS,null,"_id=?",args,null,null,null);
        task.moveToFirst();
        return task;
    }

    public String getTaskTitle(int todoId){
        String[] args = {String.valueOf(todoId)};
        Cursor task = this.dbObj.query(TaskDBContract.TABLE_TASKS,null,"_id=?",args,null,null,null);
        task.moveToFirst();
        return task.getString(task.getColumnIndexOrThrow(TaskDBContract.COLUMN_TITLE));
    }

    public int saveTask(String title, String description, String duedate){
        int recordID;
        ContentValues values = new ContentValues();
        values.put(TaskDBContract.COLUMN_TITLE, title);
        values.put(TaskDBContract.COLUMN_DESCRIPTION, description);
        values.put(TaskDBContract.COLUMN_DUEDATE, duedate);
        values.put(TaskDBContract.COLUMN_DONE, 0);

        recordID = (int) this.dbObj.insert(TaskDBContract.TABLE_TASKS, null, values);

        return recordID;
    }

    public void updateTask(int todoId, String title, String description, String duedate, boolean done){
        ContentValues values = new ContentValues();
        values.put(TaskDBContract.COLUMN_TITLE, title);
        values.put(TaskDBContract.COLUMN_DESCRIPTION, description);
        values.put(TaskDBContract.COLUMN_DUEDATE, duedate);
        values.put(TaskDBContract.COLUMN_DONE, done);

        String[] args = {String.valueOf(todoId)};

        this.dbObj.update(TaskDBContract.TABLE_TASKS, values, "_id=?", args);
    }

    public void deleteTask(int todoId){
        this.dbObj.delete(TaskDBContract.TABLE_TASKS, "_id=?", new String[]{String.valueOf(todoId)});
    }

    public void setTaskDone(int todoId){
        ContentValues values = new ContentValues();
        values.put(TaskDBContract.COLUMN_DONE, 1);
        values.put(TaskDBContract.COLUMN_HASALARM, 0);
        this.dbObj.update(TaskDBContract.TABLE_TASKS, values, "_id=?", new String[]{String.valueOf(todoId)});
    }

    public void setHasAlarm(int todoId){
        ContentValues values = new ContentValues();
        values.put(TaskDBContract.COLUMN_HASALARM, 1);
        this.dbObj.update(TaskDBContract.TABLE_TASKS, values, "_id=?", new String[]{String.valueOf(todoId)});
    }

    public void unsetHasAlarm(int todoId){
        ContentValues values = new ContentValues();
        values.put(TaskDBContract.COLUMN_HASALARM, 0);
        this.dbObj.update(TaskDBContract.TABLE_TASKS, values, "_id=?", new String[]{String.valueOf(todoId)});
    }
}
