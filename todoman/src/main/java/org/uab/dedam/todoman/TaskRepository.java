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

    public Cursor getTaskByID(int todoId){
        String[] args = {String.valueOf(todoId)};
        Cursor task = this.dbObj.query(TaskDBContract.TABLE_TASKS,null,"_id=?",args,null,null,null);
        task.moveToFirst();
        return task;
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

    public void updateTask(int todoId, String title, String description, String duedate, boolean done){
        ContentValues values = new ContentValues();
        values.put(TaskDBContract.COLUMN_TITLE, title);
        values.put(TaskDBContract.COLUMN_DESCRIPTION, description);
        values.put(TaskDBContract.COLUMN_DUEDATE, duedate);
        values.put(TaskDBContract.COLUMN_DONE, done);

        String[] args = {String.valueOf(todoId)};

        dbObj = this.taskDBOpenHelper.getWritableDatabase();
        this.dbObj.update(TaskDBContract.TABLE_TASKS, values, "_id=?", args);
        dbObj.close();
    }

    public void setTaskDone(Integer id){
        ContentValues values = new ContentValues();
        values.put(TaskDBContract.COLUMN_DONE, 1);
        this.dbObj.update(TaskDBContract.TABLE_TASKS, values, "_id=?", new String[]{id.toString()});
    }
}
