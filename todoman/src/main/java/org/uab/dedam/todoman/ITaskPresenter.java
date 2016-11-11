package org.uab.dedam.todoman;

import android.database.Cursor;
import android.widget.ListView;

import org.uab.dedam.todoman.Model.TaskModel;

import java.util.List;

public interface ITaskPresenter {
    void addTask(
            String title,
            String description,
            Boolean done,
            String dueDate,
            String dueTime);

    void listAllTasks(ListView listView);
}
