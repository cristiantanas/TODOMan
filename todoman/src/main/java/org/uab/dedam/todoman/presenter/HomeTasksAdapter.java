package org.uab.dedam.todoman.presenter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.uab.dedam.todoman.R;
import org.uab.dedam.todoman.model.TaskDatabaseContract;

public class HomeTasksAdapter extends android.widget.CursorAdapter {

    Cursor cursor;
    Context context;

    public HomeTasksAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.task_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView texTaskTitle = (TextView) view.findViewById(R.id.texTaskTitle);
        TextView texTaskDescription = (TextView) view.findViewById(R.id.texTaskDescription);
        TextView texEndDate = (TextView) view.findViewById(R.id.texTaskDate);
        TextView texEndTime = (TextView) view.findViewById(R.id.texTaskTime);
        View vieCompleted = view.findViewById(R.id.vieCompleted);

        String title = cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COLUMN_TITLE));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COLUMN_DESCRIPTION));
        String endDate = cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COLUMN_END_DATE));
        String endTime = cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COLUMN_END_TIME));
        int completed = cursor.getInt(cursor.getColumnIndexOrThrow(TaskDatabaseContract.COlUMN_COMPLETED));
        int visibilityCompleted = completed == 0 ? View.GONE : View.VISIBLE;

        texTaskTitle.setText(title);
        texTaskDescription.setText(description);
        texEndDate.setText(endDate);
        texEndTime.setText(endTime);
        vieCompleted.setVisibility(visibilityCompleted);
    }
}