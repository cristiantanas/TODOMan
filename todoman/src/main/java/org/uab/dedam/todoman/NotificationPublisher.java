package org.uab.dedam.todoman;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationPublisher extends BroadcastReceiver {



    private static final int NOTIFICATIONID = 1;

    public void onReceive(Context context, Intent intent) {

        Intent intentnot =  new Intent(context,HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,1,intentnot,0);


        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentText("Task To Complete")
                        .setContentTitle("Urgent")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

        // Send the notification
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(
                NOTIFICATIONID,
                notificationBuilder.build()
        );



    }
}