package org.uab.dedam.todoman.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.uab.dedam.todoman.R;

public class UITaskCursorAdapter extends CursorAdapter {
    Context context;

    public UITaskCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView taskTitle = (TextView) view.findViewById(R.id.itemTaskTitle);
        TextView taskDescription = (TextView) view.findViewById(R.id.itemTaskDescription);
        CheckBox checkDone = (CheckBox) view.findViewById(R.id.itemTaskDone) ;
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
        int done = cursor.getInt(cursor.getColumnIndexOrThrow("done"));
        taskTitle.setText(title);
        taskDescription.setText(String.valueOf(description));
        checkDone.setChecked(done == 1);
    }
}
