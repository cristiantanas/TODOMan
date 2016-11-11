package org.uab.dedam.todoman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Regulo on 11/11/2016.
 */

public class TaskRepository {
    private TaskDB taskDB;
    private SQLiteDatabase databaseObject;

    public TaskRepository(Context context) {
        this.taskDB = new TaskDB(context);
        this.databaseObject = this.taskDB.getWritableDatabase();
    }

    public Cursor getTasks() {
        String colums [] = {TaskDBcontract.COLUMN_ID,
                            TaskDBcontract.COLUMN_TITLE,
                            TaskDBcontract.COLUMN_DESCRIPTION,
                            TaskDBcontract.COLUMN_COMPLETED};
        Cursor cursor = this.databaseObject.query(TaskDBcontract.TABLE_TASK, colums,
                null,
                null,
                null,
                null,
                null);
        return cursor;
    }

    public void save(String title, String description, String completed ) {
        ContentValues values = new ContentValues();
        values.put(TaskDBcontract.COLUMN_TITLE, title);
        values.put(TaskDBcontract.COLUMN_DESCRIPTION, description);
        values.put(TaskDBcontract.COLUMN_COMPLETED, completed);

        this.databaseObject.insert(
                TaskDBcontract.TABLE_TASK,
                null,
                values);
    }
}



