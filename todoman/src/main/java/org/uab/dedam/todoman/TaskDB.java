package org.uab.dedam.todoman;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Regulo on 11/11/2016.
 */

public class TaskDB extends SQLiteOpenHelper {


    public TaskDB(Context context) {
        super(context, TaskDBcontract.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table task (" +
                        "_ID integer primary key, " +
                        "Title text, " +
                        "Description text, " +
                        "Completed integer not null default 0, " +
                        "EndDate text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {
        db.execSQL(
                "drop table if exist task"
        );
        onCreate(db);
    }
}
