package org.uab.dedam.todoman;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

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
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int pos = cursor.getPosition();

        //set background color
        if((pos & 0x01) == 0) {
            view.setBackgroundColor(Color.rgb(255, 255, 255));
        }else{
            view.setBackgroundColor(Color.rgb(240, 240, 240));
        }

        TextView tvTitle = (TextView) view.findViewById(R.id.taskTitle);
        TextView tvDueDate = (TextView) view.findViewById(R.id.taskDueDate);
        ImageView imgDone = (ImageView) view.findViewById(R.id.imgDone);

        //store ID in the view TAG
        view.setTag(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));

        //populate fields
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String duedate = cursor.getString(cursor.getColumnIndexOrThrow("duedate"));
        Boolean done = (cursor.getInt(cursor.getColumnIndexOrThrow("done")) == 1);
        Boolean hasalarm = (cursor.getInt(cursor.getColumnIndexOrThrow("hasalarm")) == 1);

        //convert UTC date to LOCAL date
        duedate = Tools.convertFromUTC(duedate);

        if (done) {
            imgDone.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.checkmark4));
            imgDone.setVisibility(View.VISIBLE);
        } else {
            if (hasalarm){
                imgDone.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.alarm));
                imgDone.setVisibility(View.VISIBLE);
            }else {
                imgDone.setVisibility(View.INVISIBLE);
            }
        }
        tvTitle.setText(title);
        tvDueDate.setText(duedate);
    }

    @Override
    public void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
    }

    @Override
    public Cursor swapCursor(Cursor newCursor) {
        return super.swapCursor(newCursor);
    }
}
