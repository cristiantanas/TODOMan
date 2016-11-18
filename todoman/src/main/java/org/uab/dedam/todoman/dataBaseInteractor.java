package org.uab.dedam.todoman;

import android.content.Context;
import java.util.List;

public interface DataBaseInteractor {

    interface OnFinishedListener {
        void onFinished(List<task> tasks);
    }

    void findTasks(Context context, OnFinishedListener listener);

long taskSave(Context context, task myTask);

    task findTaskById(Context context, long taskId);

    void taskUpdate(Context context, task myTask);

    void taskDelete(Context context, task myTask);

}
