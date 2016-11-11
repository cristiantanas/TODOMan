package org.uab.dedam.todoman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteDataRepository {
private DatabaseOpenHelper databaseOpenHelper;
private SQLiteDatabase sqliteDatabase;

public SQLiteDataRepository(Context context) {
this.databaseOpenHelper = new DatabaseOpenHelper(context);
}

public void openDatabaseForReadOnly() {
this.sqliteDatabase = this.databaseOpenHelper.getReadableDatabase();
}

public void openDatabaseForWrite() {
this.sqliteDatabase = this.databaseOpenHelper.getWritableDatabase();
}

public void taskSave(String taskName, String taskDescription, 
String taskDate, String taskTime, Boolean taskCompleted) {
ContentValues values = new ContentValues();
values.put(DatabaseOpenHelper.TASK_NAME, taskName);
values.put(DatabaseOpenHelper.TASK_DESCRIPTION, taskDescription);
values.put(DatabaseOpenHelper.TASK_DATE, taskDate);
values.put(DatabaseOpenHelper.TASK_TIME, taskTime);
values.put(DatabaseOpenHelper.TASK_COMPLETED, taskCompleted);
this.sqliteDatabase.insert(
DatabaseOpenHelper.TASKS_TABLE_NAME, 
null, 
values);
}

public Cursor fetchTaskById(int taskId) {
String selection = DatabaseOpenHelper._ID + " = ?";
String[] selectionArgs = new String[] { String.valueOf(taskId) };
Cursor result = this.sqliteDatabase.query(
DatabaseOpenHelper.TASKS_TABLE_NAME, 
DatabaseOpenHelper.COLUMNS, 
selection, 
selectionArgs, 
null, 
null, 
null);
return result;
}

public Cursor fetchAllTasks() {
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
sqliteDatabase = null;}
}

}