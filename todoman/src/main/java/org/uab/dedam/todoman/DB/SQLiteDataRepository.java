package org.uab.dedam.todoman.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.uab.dedam.todoman.Contract.TaskReaderContract.TaskEntry;
import org.uab.dedam.todoman.Model.TaskModel;

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

    public void closeDatabase() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
            this.sqLiteDatabase.close();
    }

    public long saveTask(String title, String description, Boolean done, String dueDate, String dueTime) {
        ContentValues values = new ContentValues();
        values.put(TaskEntry.TITLE, title);
        values.put(TaskEntry.DESCRIPTION, description);
        values.put(TaskEntry.DONE, done);
        values.put(TaskEntry.DUEDATE, dueDate);
        values.put(TaskEntry.DUETIME, dueTime);
        return this.sqLiteDatabase.insert(
                TaskEntry.TABLE_NAME,
                null,
                values);
    }

    public Cursor loadAllTasks() {
        return this.sqLiteDatabase.rawQuery(TaskEntry.SELECT_ALL_TASKS, null);
    }

    public TaskModel loadTask(long id) {
        TaskModel taskModel = new TaskModel();
        Cursor taskCursor = this.sqLiteDatabase.rawQuery(
                TaskEntry.SELECT_ALL_TASKS +
                " WHERE _id = " + id,
                null);
        if (taskCursor.moveToFirst()) {
            taskModel.setId(taskCursor.getInt(0));
            taskModel.setTitle(taskCursor.getString(1));
            taskModel.setDescription(taskCursor.getString(2));
            taskModel.setDone(taskCursor.getInt(3) == 1);
            taskModel.setDueDate(taskCursor.getString(4));
            taskModel.setDueTime(taskCursor.getString(5));
        }

        return taskModel;
    }

}
