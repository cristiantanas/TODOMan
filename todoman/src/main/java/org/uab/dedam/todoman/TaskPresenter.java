package org.uab.dedam.todoman;

import android.content.Context;
import android.database.Cursor;
import android.widget.ListView;

import org.uab.dedam.todoman.Adapter.UITaskCursorAdapter;
import org.uab.dedam.todoman.DB.SQLiteDataRepository;

public class TaskPresenter implements ITaskPresenter {

    Context context;
    SQLiteDataRepository sqLiteDataRepository;
    ListView listTasks;

    public TaskPresenter(Context context) {
        this.context = context;
        sqLiteDataRepository = new SQLiteDataRepository(context);
    }

    @Override
    public void addTask(String title, String description, Boolean done, String dueDate, String dueTime) {
        sqLiteDataRepository.openDatabaseForWrite();
        sqLiteDataRepository.saveTask(
                title,
                description,
                done,
                dueDate,
                dueTime);
    }

    @Override
    public void listAllTasks(ListView listTasks) {
        sqLiteDataRepository.openDatabaseForReadOnly();
        Cursor cursor = sqLiteDataRepository.loadAllTasks();
        UITaskCursorAdapter cursorAdapter = new UITaskCursorAdapter(context, cursor);
        listTasks.setAdapter(cursorAdapter);
    }
}
