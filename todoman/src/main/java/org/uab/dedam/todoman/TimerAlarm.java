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

public class TimerAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Timer Alarm lanzado", Toast.LENGTH_SHORT).show();
        //El vibrador del dispositivo
        Vibrator vibrator =(Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
    }
}
