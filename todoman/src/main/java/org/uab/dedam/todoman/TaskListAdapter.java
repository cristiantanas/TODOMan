package org.uab.dedam.todoman;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Balazs on 10/29/2016.
 */

public class TaskListAdapter extends CursorAdapter {

    public TaskListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.task_item,parent,false);
        //return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitle = (TextView) view.findViewById(R.id.taskTitle);
        TextView tvDueDate = (TextView) view.findViewById(R.id.taskDueDate);
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String duedate = cursor.getString(cursor.getColumnIndexOrThrow("duedate"));
        tvTitle.setText(title);
        tvDueDate.setText(duedate);
    }
}
