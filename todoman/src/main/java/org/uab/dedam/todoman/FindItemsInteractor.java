package org.uab.dedam.todoman;

import android.content.Context;

import java.util.List;

public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<task> tasks);
    }

    void findItems(Context context, OnFinishedListener listener);

void taskSave(Context context, task myTask);

}
