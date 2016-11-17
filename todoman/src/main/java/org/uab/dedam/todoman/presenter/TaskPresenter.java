package org.uab.dedam.todoman.presenter;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.SystemClock;

import org.uab.dedam.todoman.model.ITaskRepository;
import org.uab.dedam.todoman.model.TaskRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TaskPresenter  {
    ITaskRepository repositorio;
    Context context;

    public TaskPresenter(Context context){
        this.context = context;
        this.repositorio = new TaskRepository(context);
    }

    public Cursor getTasks(){
        return repositorio.getTasks();
    }

    public long saveTasks(String title, String description, boolean completed, String endDate, String endTime)
    {
        return repositorio.saveTask(title, description, completed, endDate, endTime);
    }

    public boolean isCompleted(long idTask)
    {
        return repositorio.isCompleted(idTask);
    }

    public void saveTaskTimeNotification(Notification notification, long idTask, String date, String time){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            Date dateTime = format.parse(date + " " + time);
            Calendar alarmDateTime = new GregorianCalendar(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());
            alarmDateTime.setTime(dateTime);

            Intent notificationIntent = new Intent(context, TaskTimeBroadcastReceiver.class);
            notificationIntent.putExtra(TaskTimeBroadcastReceiver.NOTIFICATION_ID, idTask);
            notificationIntent.putExtra(TaskTimeBroadcastReceiver.NOTIFICATION, notification);
            notificationIntent.putExtra(TaskTimeBroadcastReceiver.TASK_ID, idTask);
            PendingIntent pendingIntentAlarm = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmDateTime.getTimeInMillis(), pendingIntentAlarm);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}
