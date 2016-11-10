package org.uab.dedam.todoman.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskDatabase extends SQLiteOpenHelper {

    public TaskDatabase(Context context) {
        super(context, TaskDatabaseContract.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE task (_id integer primary key, " +
                                      " Title text, " +
                                      " Description text, " +
                                      " Completed integer not null default 0, " +
                                      " EndDate text, " +
                                      " EndTime text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
