package org.uab.dedam.todoman.presenter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import org.uab.dedam.todoman.model.ITaskRepository;
import org.uab.dedam.todoman.model.TaskRepository;

public class TaskPresenter  {
    ITaskRepository repositorio;

    public TaskPresenter(Context context){
        this.repositorio = new TaskRepository(context);
    }

    public Cursor getTasks(){
        return repositorio.getTasks();
    }

    public boolean saveTasks(String title, String description, boolean completed, String endDate, String endTime)
    {
        return repositorio.saveTask(title, description, completed, endDate, endTime);
    }
}
