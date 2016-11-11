package org.uab.dedam.todoman;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class TaskCursorAdapter extends CursorAdapter {


    public TaskCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.list_task_item, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewTaskTitle = (TextView) view.findViewById(R.id.textViewItemTaskTitle);
        textViewTaskTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COLUMN_TITLE)));

        TextView textViewTaskDescription = (TextView) view.findViewById(R.id.textViewItemTaskDescription);
        textViewTaskDescription.setText(cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COLUMN_DESCRIPTION)));

        CheckBox checkboxTaskCompleted = (CheckBox) view.findViewById(R.id.checkBoxItemTaskCompleted);
        checkboxTaskCompleted.setChecked(cursor.getInt(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COLUMN_COMPLETED)) == 1 ? true : false);

        TextView textViewTaskEndDate = (TextView) view.findViewById(R.id.textViewItemTaskSelectedDate);
        textViewTaskEndDate.setText(cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COLUMN_END_DATE)));

        TextView textViewEndTime = (TextView) view.findViewById(R.id.textViewItemTaskSelectedTime);
        textViewEndTime.setText(cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COLUMN_END_TIME)));
    }
}