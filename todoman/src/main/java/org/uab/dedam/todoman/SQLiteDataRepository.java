package org.uab.dedam.todoman;

/**
 * Created by kofax on 06/11/16.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteDataRepository {


    private DatabaseOpenHelper databaseOpenHelper;


    private SQLiteDatabase				sqliteDatabase;



    public SQLiteDataRepository(Context context) {

        this.databaseOpenHelper = new DatabaseOpenHelper(context);
    }


    public void openDatabaseForReadOnly() {

        this.sqliteDatabase = this.databaseOpenHelper.getReadableDatabase();
    }


    public void openDatabaseForWrite() {

        this.sqliteDatabase = this.databaseOpenHelper.getWritableDatabase();
    }


    public void saveTask(String title, String description,
                           String completed, String enddate) {

        ContentValues values = new ContentValues();
        values.put(DatabaseOpenHelper.TITLE, title);
        values.put(DatabaseOpenHelper.DESCRIPTION, description);
        values.put(DatabaseOpenHelper.COMPLETED, completed);
        values.put(DatabaseOpenHelper.ENDDATE, enddate);

        this.sqliteDatabase.insert(
                DatabaseOpenHelper.TASKS_TABLE_NAME,
                null,
                values);
    }



    public Cursor retrieveAllTasks() {

        Cursor result = this.sqliteDatabase.query(
                DatabaseOpenHelper.TASKS_TABLE_NAME,
                DatabaseOpenHelper.COLUMNS,
                null,
                null,
                null,
                null,
                null);
        return result;
    }

    public void release() {

        if ( sqliteDatabase != null ) {
            sqliteDatabase.close();
            sqliteDatabase = null;
        }
    }
}