package org.uab.dedam.todoman.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.uab.dedam.todoman.Contract.TaskReaderContract.TaskEntry;

public class SQLiteDataRepository {

    private DataBaseOpenHelper dataBaseOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public SQLiteDataRepository(Context context) {
        this.dataBaseOpenHelper = new DataBaseOpenHelper(context);
    }

    public void openDatabaseForReadOnly() {

        this.sqLiteDatabase = this.dataBaseOpenHelper.getReadableDatabase();
    }

    public void openDatabaseForWrite() {

        this.sqLiteDatabase = this.dataBaseOpenHelper.getWritableDatabase();
    }

    public void saveTask(String title, String description, Boolean done, String dueDate, String dueTime) {
        ContentValues values = new ContentValues();
        values.put(TaskEntry.TITLE, title);
        values.put(TaskEntry.DESCRIPTION, description);
        values.put(TaskEntry.DONE, done);
        values.put(TaskEntry.DUEDATE, dueDate);
        values.put(TaskEntry.DUETIME, dueTime);
        this.sqLiteDatabase.insert(
                TaskEntry.TABLE_NAME,
                null,
                values);
    }

    public Cursor loadAllTasks() {
        return this.sqLiteDatabase.rawQuery(TaskEntry.SELECT_ALL_TASKS, null);
    }

}
