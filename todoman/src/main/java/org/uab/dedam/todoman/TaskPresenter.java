package org.uab.dedam.todoman;

import android.content.Context;
import android.database.Cursor;
import android.widget.ListView;

import org.uab.dedam.todoman.Adapter.UITaskCursorAdapter;
import org.uab.dedam.todoman.DB.SQLiteDataRepository;
import org.uab.dedam.todoman.Model.TaskModel;

public class TaskPresenter implements ITaskPresenter {

    Context context;
    SQLiteDataRepository sqLiteDataRepository;

    public TaskPresenter(Context context) {
        this.context = context;
        sqLiteDataRepository = new SQLiteDataRepository(context);
    }

    @Override
    public long addTask(String title, String description, Boolean done, String dueDate, String dueTime) {
        sqLiteDataRepository.openDatabaseForWrite();
        long taskId = sqLiteDataRepository.saveTask(
                title,
                description,
                done,
                dueDate,
                dueTime);
        sqLiteDataRepository.closeDatabase();
        return taskId;
    }

    @Override
    public void listAllTasks(ListView listTasks) {
        sqLiteDataRepository.openDatabaseForReadOnly();
        Cursor cursor = sqLiteDataRepository.loadAllTasks();
        UITaskCursorAdapter cursorAdapter = new UITaskCursorAdapter(context, cursor);
        listTasks.setAdapter(cursorAdapter);
        sqLiteDataRepository.closeDatabase();
    }

    @Override
    public TaskModel getTask(long alarmId) {
        sqLiteDataRepository.openDatabaseForReadOnly();
        TaskModel taskModel = sqLiteDataRepository.loadTask(alarmId);
        sqLiteDataRepository.closeDatabase();
        return taskModel;
    }
}
