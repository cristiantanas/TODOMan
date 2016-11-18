package org.uab.dedam.todoman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseRepository {
private DatabaseOpenHelper dataBaseOpenHelper;
private SQLiteDatabase sqliteDatabase;

public DataBaseRepository(Context context) {
this.dataBaseOpenHelper = new DatabaseOpenHelper(context);
}

public void openDatabaseForReadOnly() {
this.sqliteDatabase = this.dataBaseOpenHelper.getReadableDatabase();
}

public void openDatabaseForWrite() {
this.sqliteDatabase = this.dataBaseOpenHelper.getWritableDatabase();
}

public long taskSave(String taskName, String taskDescription,
String taskDate, String taskTime, Boolean taskCompleted) {
ContentValues values = new ContentValues();
values.put(DatabaseOpenHelper.TASK_NAME, taskName);
values.put(DatabaseOpenHelper.TASK_DESCRIPTION, taskDescription);
values.put(DatabaseOpenHelper.TASK_DATE, taskDate);
values.put(DatabaseOpenHelper.TASK_TIME, taskTime);
values.put(DatabaseOpenHelper.TASK_COMPLETED, taskCompleted);
long taskId=this.sqliteDatabase.insert(
dataBaseOpenHelper.TASKS_TABLE_NAME,
null, 
values);
	return taskId;
}

public Cursor fetchTaskById(long taskId) {
String selection = dataBaseOpenHelper._ID + " = ?";
String[] selectionArgs = new String[] { String.valueOf(taskId) };
Cursor result = this.sqliteDatabase.query(
dataBaseOpenHelper.TASKS_TABLE_NAME,
dataBaseOpenHelper.COLUMNS,
selection, 
selectionArgs, 
null, 
null, 
null);
return result;
}

public Cursor fetchAllTasks() {
Cursor result = this.sqliteDatabase.query(
dataBaseOpenHelper.TASKS_TABLE_NAME,
dataBaseOpenHelper.COLUMNS,
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