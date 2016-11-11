package org.uab.dedam.todoman.Contract;


import android.provider.BaseColumns;

public class TaskReaderContract {
    private TaskReaderContract() {}

    public static class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "TASKS";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String DONE = "done";
        public static final String DUEDATE = "duedate";
        public static final String DUETIME = "duetime";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        TITLE + " TEXT, " +
                        DESCRIPTION + " TEXT, " +
                        DONE + " INTEGER, " +
                        DUEDATE + " TEXT, " +
                        DUETIME + " TEXT)";
        public static final String SQL_DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static final String SELECT_ALL_TASKS =
                "SELECT " +
                        _ID + "," +
                        TITLE + "," +
                        DESCRIPTION + "," +
                        DONE +
                        " FROM " + TABLE_NAME;

    }
}
