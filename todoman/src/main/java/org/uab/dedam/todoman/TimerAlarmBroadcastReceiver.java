package org.uab.dedam.todoman;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Vibrator;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by mon on 18/11/16.
 */

public class TimerAlarmBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String titleNotification=intent.getExtras().getString("title");
        String descriptionNotification=intent.getExtras().getString("description");
        String tickerNotification="New notification";
        Boolean doneNotification=intent.getExtras().getBoolean("done");

        if(!doneNotification) {
            Intent intentReturnHome = new Intent(context, HomeActivity.class);
            //Notification
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentReturnHome, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(titleNotification)
                    .setContentText(descriptionNotification)
                    .setTicker(tickerNotification)
                    .setContentIntent(pendingIntent);

            Notification notification = builder.build();
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(NOTIFICATION_SERVICE);


            notificationManager.notify(1, notification);

            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
        }
    }
}
