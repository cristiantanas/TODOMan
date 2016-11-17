package org.uab.dedam.todoman;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by balazs.ujlaki on 16/11/2016.
 */

public class TodomanBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_TASKDUE = "org.uab.dedam.todoman.TASKDUE";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equalsIgnoreCase(ACTION_TASKDUE)){
            //create new INTENT to open NewTodoActivity

            TaskRepository repo = new TaskRepository(context);

            int todoId = intent.getIntExtra("todoId", -1);
            String todoTitle = repo.getTaskTitle(todoId);

            //unset HASALARM flag
            repo.unsetHasAlarm(todoId);

            Intent taskIntent = new Intent(context, NewTodoActivity.class);
            taskIntent.putExtra("todoId", todoId);
            taskIntent.putExtra("action", NewTodoActivity.ACTION_EDITTASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, taskIntent, 0);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setAutoCancel(true)
                    .setContentTitle("TASK DUE!")
                    .setContentText(todoTitle)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentIntent(pendingIntent);

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1234, builder.build());
        }

    }
}
