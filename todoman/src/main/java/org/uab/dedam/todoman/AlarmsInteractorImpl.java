package org.uab.dedam.todoman;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.SynchronousQueue;

public class AlarmsInteractorImpl implements AlarmsInteractor {
		@Override
	public void setNewAlarm(Context activityContext, task myTask) {
		String[] Date=myTask.getDate().split("/");
		String[] Time=myTask.getTime().split(":");
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.set(Calendar.DAY_OF_MONTH,(Integer.parseInt(Date[0])));
		c.set(Calendar.MONTH, (Integer.parseInt(Date[1]))-1);
		c.set(Calendar.YEAR,(Integer.parseInt(Date[2])));
		c.set(Calendar.HOUR_OF_DAY,(Integer.parseInt(Time[0])));
		c.set(Calendar.MINUTE, (Integer.parseInt(Time[1])));
		AlarmManager manager = (AlarmManager) activityContext.getSystemService(activityContext.ALARM_SERVICE);
		Intent intent  = new Intent("org.uab.dedam.todoman.alarmmanager");
			intent.putExtra("taskId", myTask.getId());
			intent.putExtra("taskName", myTask.getName());
		PendingIntent pIntent = PendingIntent.getBroadcast(activityContext, (int)myTask.getId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
								manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pIntent);
		Toast.makeText(activityContext, "Alarma configurada para la tarea con ID "+(String.valueOf(myTask.getId())), Toast.LENGTH_LONG).show();
	}

	@Override
	public void unsetOldAlarm(Context activityContext, task myTask) {
// TODO Implementar un método para refrescar las alarmas de tareas completadas, y de tareas con cambios de fecha.
	}
}
