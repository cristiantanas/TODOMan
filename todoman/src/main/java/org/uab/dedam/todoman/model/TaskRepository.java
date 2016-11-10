package org.uab.dedam.todoman.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskRepository implements ITaskRepository{
    private TaskDatabase database;
    private SQLiteDatabase dataBaseObject;

    public TaskRepository(Context context){
        this.database = new TaskDatabase(context);
        dataBaseObject = this.database.getWritableDatabase();
    }

    @Override
    public Cursor getTasks(){
        return this.dataBaseObject.query(TaskDatabaseContract.TABLE_TASK,
                null,
                null, null,
                null, null, null);
    }

    @Override
    public boolean saveTask(String title, String description, boolean completed, String endDate, String endTime)
    {
        try
        {
            ContentValues values = new ContentValues();
            values.put(TaskDatabaseContract.COLUMN_TITLE, title);
            values.put(TaskDatabaseContract.COLUMN_DESCRIPTION, description);
            values.put(TaskDatabaseContract.COlUMN_COMPLETED, completed);
            values.put(TaskDatabaseContract.COLUMN_END_DATE, endDate);
            values.put(TaskDatabaseContract.COLUMN_END_TIME, endTime);
            long id = this.dataBaseObject.insert(TaskDatabaseContract.TABLE_TASK, null, values);
            return id != 0;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
