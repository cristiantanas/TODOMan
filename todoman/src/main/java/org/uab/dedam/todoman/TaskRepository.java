package org.uab.dedam.todoman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskRepository {
    private TaskDatabase taskDatabase;
    private SQLiteDatabase databaseObject;

    public TaskRepository(Context context) {
        this.taskDatabase = new TaskDatabase(context);
        this.databaseObject = this.taskDatabase.getWritableDatabase();
    }

    public Cursor getTasks() {
        String buildSQL = "SELECT * FROM " + TaskDatabaseContract.TABLE_TASK;
        return databaseObject.rawQuery(buildSQL, null);
    }

    public void save(String title, String description, boolean isCompleted, String endDate, String endTime) {
        int completed = isCompleted ? 1 : 0;
        ContentValues values = new ContentValues();

        values.put(TaskDatabaseContract.COLUMN_TITLE, title);
        values.put(TaskDatabaseContract.COLUMN_DESCRIPTION, description);
        values.put(TaskDatabaseContract.COLUMN_COMPLETED, completed);
        values.put(TaskDatabaseContract.COLUMN_END_DATE, endDate);
        values.put(TaskDatabaseContract.COLUMN_END_TIME, endTime);

        long id = this.databaseObject.insert(
                TaskDatabaseContract.TABLE_TASK,
                null,
                values);
    }
}
