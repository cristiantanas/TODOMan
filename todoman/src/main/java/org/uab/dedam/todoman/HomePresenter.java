package org.uab.dedam.todoman;

import android.content.Context;

public interface HomePresenter {

    void onResume();

void onNewTaskClicked();

    void onItemClicked(int position);

void onTaskSaveClicked(task myTask);

    void onDestroy();

}