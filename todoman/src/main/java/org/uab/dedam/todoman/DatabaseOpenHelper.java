package org.uab.dedam.todoman;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {


    private static final String		DATABASE_NAME = "todoman";
    private static final int		DATABASE_VERSION = 1;


    static final String		TASKS_TABLE_NAME = "tasks";
    static final String		_ID = "_id";
    static final String		TITLE = "Title";
    static final String		DESCRIPTION = "Description";
    static final String		COMPLETED = "Completed";
    static final String		ENDDATE = "EndDate";


    static final String[] COLUMNS = {
            _ID,
            TITLE,
            DESCRIPTION,
            COMPLETED,
            ENDDATE
    };


    private static final String CREATE_TASKS_TABLE =
            "CREATE TABLE " + TASKS_TABLE_NAME + "( " +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TITLE + " TEXT NOT NULL, " +
                    DESCRIPTION + " TEXT, " +
                     COMPLETED+ " TEXT, " +
                    ENDDATE + " TEXT)";


    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TASKS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}
