package org.uab.dedam.todoman;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

public class DataBaseInteractorImpl implements DataBaseInteractor {
    @Override
    public void findTasks(final Context context, final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                listener.onFinished(createTaskList(context));
            }
        }, 2000);
    }

    @Override
    public long taskSave(final Context context, final task myTask) {
                DataBaseRepository repository= new DataBaseRepository(context);
                repository.openDatabaseForWrite();
                return repository.taskSave(myTask.getName(),myTask.getDescription(),myTask.getDate(),myTask.getTime(),myTask.getCompleted());
            }

    @Override
    public task findTaskById(Context context, long taskId) {
        DataBaseRepository repository;
        repository = new DataBaseRepository(context);
        repository.openDatabaseForReadOnly();
        Cursor allTasks = repository.fetchTaskById(taskId);
        task myTask = new task();
                if (allTasks.moveToFirst()) {
            do {
                                myTask.setId(Long.parseLong(allTasks.getString(0), 10));
                myTask.setName(allTasks.getString(1));
                myTask.setDescription(allTasks.getString(2));
                myTask.setDate(allTasks.getString(3));
                myTask.setTime(allTasks.getString(4));
                myTask.setCompleted(Boolean.getBoolean(allTasks.getString(5)));
                            } while (allTasks.moveToNext());
        }
        return myTask;
    }

    @Override
    public void taskUpdate(Context context, task myTask) {
// TODO Implementar método para actualización de datos de tareas existentes.
    }

    @Override
    public void taskDelete(Context context, task myTask) {
        // TODO Implementar método para eliminación de tareas.
    }

    private List<task> createTaskList(Context context) {
                DataBaseRepository repository;
repository = new DataBaseRepository(context);
repository.openDatabaseForReadOnly();
Cursor allTasks = repository.fetchAllTasks();
            List<task> taskList = new ArrayList<task>();
            if (allTasks.moveToFirst()) {
                do {
                    task myTask = new task();
                                        myTask.setId(Long.parseLong(allTasks.getString(0), 10));
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
