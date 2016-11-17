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
    public boolean isCompleted(long idTask)
    {
        String whereClause = TaskDatabaseContract.COLUMN_ID + " = ?";
        String[] whereArgs = new String[] { String.valueOf(idTask) };

        Cursor cursor = this.dataBaseObject.query(TaskDatabaseContract.TABLE_TASK,
                null,
                whereClause, whereArgs,
                null, null, null);
        cursor.moveToNext();
        int completed = cursor.getInt(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COlUMN_COMPLETED));
        return  completed == 1;
    }

    @Override
    public long saveTask(String title, String description, boolean completed, String endDate, String endTime)
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
            return id;
        }
        catch (Exception ex)
        {
            return 0;
        }
    }
}
