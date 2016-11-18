package org.uab.dedam.todoman;

import android.content.Context;
import android.os.Bundle;

public interface HomePresenter {

    void onResume();

    void onDestroy();

void onNewTaskClicked();

    void onTaskClicked(long taskId);

void onTaskSaveClicked(task myTask);

        task getTaskFromIntent(Bundle taskBundle);

}

