package org.uab.dedam.todoman.presenter;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TaskTimeBroadcastReceiver extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "NOTIFICATION_ID";
    public static String NOTIFICATION = "NOTIFICATION";
    public static String TASK_ID = "TASK_ID";

    @Override
    public void onReceive(Context context, Intent intent) {
        TaskPresenter presenter = new TaskPresenter(context);

        long idTask = intent.getLongExtra(TASK_ID, 0);
        if(!presenter.isCompleted(idTask))
        {
            NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = intent.getParcelableExtra(NOTIFICATION);
            int idNotification = intent.getIntExtra(NOTIFICATION_ID, 0);
            notificationManager.notify(idNotification, notification);
        }
    }
}
