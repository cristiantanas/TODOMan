package org.uab.dedam.todoman;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.view.View.*;

/**
 * Created by mon on 10/11/16.
 */
public class CustomList extends CursorAdapter {

    public CustomList(Context context, Cursor cursor)  {
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitle = (TextView) view.findViewById(R.id.itemTaskTitle);
        TextView tvDescription=(TextView) view.findViewById(R.id.itemTaskDescription);
        TextView tvDate=(TextView)view.findViewById(R.id.itemTaskDate);
        ImageView ivDone=(ImageView) view.findViewById(R.id.doneIcon);

        String txtTitle=cursor.getString(cursor.getColumnIndexOrThrow(DataBaseToDoMan.TASK_TITLE));
        String txtDescription=cursor.getString(cursor.getColumnIndexOrThrow(DataBaseToDoMan.TASK_DESCRIPTION));
        String txtDate=cursor.getString(cursor.getColumnIndexOrThrow(DataBaseToDoMan.TASK_END_DATE));
        String txtTime=cursor.getString(cursor.getColumnIndexOrThrow(DataBaseToDoMan.TASK_END_TIME));
        Boolean txtDone = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseToDoMan.TASK_DONE)) != 0;

        tvTitle.setText(txtTitle);
        if(txtDone) {
            ivDone.setVisibility(VISIBLE);
        }
        if(txtDescription.isEmpty())tvDescription.setVisibility(GONE);
        else tvDescription.setText(txtDescription);
        tvDate.setText(txtTime+" ("+txtDate+")");

    }


}