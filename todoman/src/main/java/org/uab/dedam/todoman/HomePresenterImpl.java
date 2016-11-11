package org.uab.dedam.todoman;

import android.content.Context;
import android.content.Intent;
import java.util.List;

public class HomePresenterImpl implements HomePresenter, FindItemsInteractor.OnFinishedListener {

    private  Context activityContext;
    private HomeView homeView;
    private NewTaskView newTaskView;
private FindItemsInteractor findItemsInteractor;

    public HomePresenterImpl(Context activityContext, HomeView homeView, NewTaskView newTaskView, FindItemsInteractor findItemsInteractor) {
        this.activityContext=activityContext;
        this.homeView = homeView;
        this.newTaskView=newTaskView;
        this.findItemsInteractor = findItemsInteractor;
    }

    @Override
    public void onResume() {
                        findItemsInteractor.findItems(activityContext,this);
    }

    @Override
    public void onNewTaskClicked() {
        Intent intent = new Intent(activityContext,NewTaskActivity.class);
        intent.putExtra("action", "taskNew");
         activityContext.startActivity(intent);
    }

    @Override
    public void onItemClicked(int position) {
        if (homeView != null) {
            
        }
    }

    @Override
    public void onTaskSaveClicked(task myTask) {
        if (newTaskView != null) {
findItemsInteractor.taskSave(activityContext,myTask);
        }
    }

    @Override public void onDestroy() {
        homeView = null;
        newTaskView=null;
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

    public NewTaskView getNewTaskView() {
        return newTaskView;
    }

}

