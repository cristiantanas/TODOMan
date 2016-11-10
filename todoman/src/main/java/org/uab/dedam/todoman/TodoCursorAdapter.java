package org.uab.dedam.todoman;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by kofax on 06/11/16.
 */

public class TodoCursorAdapter extends CursorAdapter {
    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView title = (TextView) view.findViewById(R.id.titleitem);


        TextView description = (TextView) view.findViewById(R.id.descriptionitem);

        CheckBox completed = (CheckBox) view.findViewById(R.id.completeditem);



        String titletoshow = cursor.getString(cursor.getColumnIndexOrThrow("Title"));
        String descriptiontoshow=cursor.getString(cursor.getColumnIndexOrThrow("Description"));
        String completedshow = cursor.getString(cursor.getColumnIndexOrThrow("Completed"));

        title.setText(titletoshow);
        description.setText(descriptiontoshow);
        if (completedshow.contentEquals("true")) {
            completed.setChecked(true);
        }
        else
        {
            completed.setChecked(false);

        }

    }
}
