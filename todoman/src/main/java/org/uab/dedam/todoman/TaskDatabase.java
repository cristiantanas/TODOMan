package org.uab.dedam.todoman;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDatabase extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            TaskDatabaseContract.TABLE_TASK +
            "(_id integer primary key, " +
            "Title text, " +
            "Description text, " +
            "Completed integer not null default 0, " +
            "EndDate text, " +
            "EndTime text)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskDatabaseContract.TABLE_TASK;

    public TaskDatabase(Context context) {
        super(context, TaskDatabaseContract.DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
