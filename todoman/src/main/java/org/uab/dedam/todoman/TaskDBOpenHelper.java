package org.uab.dedam.todoman;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by balazs.ujlaki on 28/10/2016.
 */

public class TaskDBOpenHelper extends SQLiteOpenHelper {

    public TaskDBOpenHelper(Context context) {
        super(context, TaskDBContract.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TaskDBContract.TABLE_TASKS + " (" +
                        TaskDBContract.COLUMN_ID + " integer primary key, " +
                        TaskDBContract.COLUMN_TITLE + " text, " +
                        TaskDBContract.COLUMN_DESCRIPTION + " text, " +
                        TaskDBContract.COLUMN_DUEDATE + " text, " +
                        TaskDBContract.COLUMN_DONE + " integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
