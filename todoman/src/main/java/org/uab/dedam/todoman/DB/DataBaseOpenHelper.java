package org.uab.dedam.todoman.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.uab.dedam.todoman.Contract.TaskReaderContract.TaskEntry;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "taskDB";
    private static final int DB_VERSION = 14;


    public DataBaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TaskEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TaskEntry.SQL_DROP_TABLE);
        onCreate(db);
    }
}
