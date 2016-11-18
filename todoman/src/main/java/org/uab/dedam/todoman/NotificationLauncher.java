package org.uab.dedam.todoman;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationLauncher extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean notNotify = intent.getBooleanExtra("completedTask", false);

        if( ! notNotify ) {
            Intent intentOpenHome = new Intent(context, HomeActivity.class);
            PendingIntent pendingIntentOpenHome = PendingIntent.getActivity(context, 0, intentOpenHome, PendingIntent.FLAG_UPDATE_CURRENT);
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

            builder.setAutoCancel(false)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(intent.getStringExtra("titleTask"))
                    .setContentText(intent.getStringExtra("descriptionTask"))
                    .setTicker("I'm a ticker")
                    .setColor(context.getResources().getColor(R.color.colorOrangeDark))
                    .setContentIntent(pendingIntentOpenHome)
                    .setSound(alarmSound);
            ;

            Notification notification = builder.build();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(123456, notification);
        }
    }
}
