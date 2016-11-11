package org.uab.dedam.todoman;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindItemsInteractorImpl implements FindItemsInteractor {
    @Override
    public void findItems(final Context context, final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                listener.onFinished(createTaskList(context));
            }
        }, 2000);
    }

    @Override
    public void taskSave(final Context context, final task myTask) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                SQLiteDataRepository repository= new SQLiteDataRepository(context);
                repository.openDatabaseForWrite();
                repository.taskSave(myTask.getName(),myTask.getDescription(),myTask.getDate(),myTask.getTime(),myTask.getCompleted());
            }
        }, 2000);
        }

    private List<task> createTaskList(Context context) {
                SQLiteDataRepository repository;
repository = new SQLiteDataRepository(context);
repository.openDatabaseForReadOnly();
Cursor allTasks = repository.fetchAllTasks();
            List<task> taskList = new ArrayList<task>();
            if (allTasks.moveToFirst()) {
                do {
                    task myTask = new task();
                                        myTask.setId(Integer.parseInt(allTasks.getString(0)));
                                        myTask.setName(allTasks.getString(1));
myTask.setDescription(allTasks.getString(2));
myTask.setDate(allTasks.getString(3));
myTask.setTime(allTasks.getString(4));
myTask.setCompleted(Boolean.getBoolean(allTasks.getString(5)));
                    taskList.add(myTask);
                } while (allTasks.moveToNext());
            }
        return taskList;
    }
}
