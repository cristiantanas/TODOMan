package org.uab.dedam.todoman.Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import org.uab.dedam.todoman.HomeActivity;
import org.uab.dedam.todoman.ITaskPresenter;
import org.uab.dedam.todoman.Model.TaskModel;
import org.uab.dedam.todoman.NewTaskActivity;
import org.uab.dedam.todoman.TaskPresenter;

public class TODOManService extends IntentService {

    private static final String tickerText = "TO DO Man";
    private String notificationTitle = "Notification Title";
    private String notificationContent = "You have received a new notification!";
    private static final long[] VIBRATE_PATTERN = { 200 };
    private NotificationManager notificationManager;
    private static final int RANDOM_NOTIFICATION_ID = 1;
    ITaskPresenter taskPresenter;

    public TODOManService() {
        super("TODOManService");
    }

    public TODOManService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        taskPresenter = new TaskPresenter(this);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        final PendingIntent notificationPendingIntent = PendingIntent
                .getActivity(
                        this, 							// Context
                        122457799, 					    // Request code
                        notificationIntent, 			// Intent to be launched
                        Intent.FLAG_ACTIVITY_NEW_TASK	// Flags
                );
        long alarmId = intent.getExtras().getLong("alarmId");
        TaskModel task = taskPresenter.getTask(alarmId);

        if (!task.getDone()) {

            notificationTitle = task.getTitle();
            notificationContent = task.getDescription();

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this)
                            .setAutoCancel(true)
                            .setTicker(tickerText)
                            .setContentTitle(notificationTitle)
                            .setContentText(notificationContent)
                            .setSmallIcon(android.R.drawable.ic_menu_agenda)
                            .setContentIntent(notificationPendingIntent)
                            .setVibrate(VIBRATE_PATTERN);

            notificationManager.notify(
                    RANDOM_NOTIFICATION_ID,
                    notificationBuilder.build()        // Notification build previously
            );
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
}
