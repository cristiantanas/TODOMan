package org.uab.dedam.todoman.model;

import android.database.Cursor;

public interface ITaskRepository {
    Cursor getTasks();
    long saveTask(String title, String description, boolean completed, String endDate, String endTime);
    boolean isCompleted(long idTask);
}
