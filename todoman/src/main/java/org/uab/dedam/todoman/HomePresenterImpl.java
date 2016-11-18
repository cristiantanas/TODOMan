package org.uab.dedam.todoman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, DataBaseInteractor.OnFinishedListener {

    private  Context activityContext;
    private HomeView homeView;
    private TaskView taskView;
private DataBaseInteractor dataBaseInteractor;
    private  AlarmsInteractor alarmsInteractor;

    public HomePresenterImpl(Context activityContext, HomeView homeView, TaskView taskView, DataBaseInteractor dataBaseInteractor, AlarmsInteractor alarmsInteractor) {
        this.activityContext=activityContext;
        this.homeView = homeView;
        this.taskView = taskView;
        this.dataBaseInteractor = dataBaseInteractor;
        this.alarmsInteractor=alarmsInteractor;
    }

    @Override
    public void onResume() {
                        dataBaseInteractor.findTasks(activityContext,this);
    }

    @Override
    public void onNewTaskClicked() {
        Intent intent = new Intent(activityContext,TaskActivity.class);
        intent.putExtra("taskId", 0);
         activityContext.startActivity(intent);
    }

    @Override
    public void onTaskClicked(long taskId) {
        // TODO Revisar por qué no funciona al pulsar en un elemento de la lista.
        if (homeView != null) {
            Intent intent = new Intent(activityContext,TaskActivity.class);
            intent.putExtra("taskId", taskId);
            activityContext.startActivity(intent);
        }
    }

    @Override
    public void onTaskSaveClicked(task myTask) {
        if (taskView != null) {
            if (myTask.getId() == 0) {
                myTask.setId(dataBaseInteractor.taskSave(activityContext, myTask));
                alarmsInteractor.setNewAlarm(activityContext, myTask);
            }else {
                dataBaseInteractor.taskUpdate(activityContext,myTask);
                alarmsInteractor.unsetOldAlarm(activityContext, myTask);
                alarmsInteractor.setNewAlarm(activityContext, myTask);
            }
        }
    }

    @Override
    public void onDestroy() {
        homeView = null;
        taskView =null;
    }

    @Override
    public task getTaskFromIntent(Bundle taskBundle) {
        task myTask=new task();
long taskId=taskBundle.getLong("taskId");
        if(taskId==0){
            myTask.setId(taskId);
            return myTask;
        }else{
            myTask= dataBaseInteractor.findTaskById(activityContext, taskId);
                    return  myTask;
        }
    }

    @Override
    public void onFinished(List<task> tasks) {
        if (homeView != null) {
            homeView.setTasks(tasks);
                    }
    }

    public HomeView getHomeView() {
        return homeView;
    }

    public TaskView getTaskView() {
        return taskView;
    }

}

