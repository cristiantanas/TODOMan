package org.uab.dedam.todoman;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

private static final String DATABASE_NAME = "ToDoMan";
private static final int DATABASE_VERSION = 1;
static final String TASKS_TABLE_NAME = "tasks";
static final String _ID = "_id";
static final String TASK_NAME = "taskName";
static final String TASK_DESCRIPTION = "taskDescription";
static final String TASK_DATE = "taskDate";
static final String TASK_TIME = "taskTime";
static final String TASK_COMPLETED = "taskCompleted";
static final String[] COLUMNS = {
_ID,
TASK_NAME,
TASK_DESCRIPTION,
TASK_DATE,
TASK_TIME,
TASK_COMPLETED
};
private static final String CREATE_TASKS_TABLE = 
"CREATE TABLE " + TASKS_TABLE_NAME + "( " +
_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
TASK_NAME + " TEXT NOT NULL, " +
TASK_DESCRIPTION + " TEXT NOT NULL, " +
TASK_DATE + " TEXT NOT NULL, " +
TASK_TIME + " TEXT NOT NULL, " +
TASK_COMPLETED + " BOOLEAN NOT NULL )";

public DatabaseOpenHelper(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) {
db.execSQL(CREATE_TASKS_TABLE);
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Implementar método para actualización de base de datos por versiones.
}

}