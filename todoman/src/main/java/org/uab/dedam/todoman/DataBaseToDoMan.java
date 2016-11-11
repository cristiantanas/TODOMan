package org.uab.dedam.todoman;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mon on 10/11/16.
 */
public class DataBaseToDoMan extends SQLiteOpenHelper{
    public static final String DB_NAME="agenda";
    public static final int DB_VERSION=1;

    public static final String DB_TABLE_TASKS="tasks";
    public static final String _ID="_id";
    public static final String TASK_TITLE="title";
    public static final String TASK_DESCRIPTION="description";
    public static final String TASK_END_DATE="end_date";
    public static final String TASK_DONE="done";

    static final String[] COLUMNS = {
            _ID,
            TASK_TITLE,
            TASK_DESCRIPTION,
            TASK_END_DATE,
            TASK_DONE
    };

    private static final String DB_CREATE_TASKS_TABLE=
            "create table "+DB_TABLE_TASKS+"("
            +_ID+" integer primary key autoincrement, "
            +TASK_TITLE+" text not null, "
            +TASK_DESCRIPTION+" text, "
            +TASK_END_DATE+" text, "
            +TASK_DONE+" boolean not null)";

    public DataBaseToDoMan(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
