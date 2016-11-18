package org.uab.dedam.todoman;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
	public NotificationReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		long taskId=intent.getLongExtra("taskId", 10);
		String taskName=intent.getStringExtra("taskName");
		String contentDescription=context.getResources().getString(R.string.taskNotification);
		Intent notificationIntent=new Intent(context, TaskActivity.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		notificationIntent.putExtra("taskId", taskId);
		PendingIntent notificationPendingIntent = PendingIntent.getActivity(context, (int)taskId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
		.setSmallIcon(android.R.drawable.ic_dialog_info)
						.setContentTitle(context.getResources().getString(R.string.app_name))
						.setContentText(contentDescription+":"+taskName)
						.setContentIntent(notificationPendingIntent)
						.setAutoCancel(true);
				notificationManager.notify(
						(int)taskId,
				notificationBuilder.build()
		);
						// Toast.makeText(context, "La tarea "+(String.valueOf(taskId))+" ha vencido. "+taskName+": "+contentDescription, Toast.LENGTH_LONG).show();
					}
}

