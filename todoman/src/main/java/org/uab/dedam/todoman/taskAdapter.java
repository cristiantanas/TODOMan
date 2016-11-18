package org.uab.dedam.todoman;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class taskAdapter extends BaseAdapter {
    protected Activity activity;
    protected List<task> tasks;

    public taskAdapter (Activity activity,List<task> tasks) {
        this.activity = activity;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    public void clear() {
        tasks.clear();
    }

    public void addAll(List<task> task) {
        for (int i = 0; i < task.size(); i++) {
            tasks.add(task.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return tasks.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        task myTask = tasks.get(position);
        return myTask.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.taskitem, null);
        }

        task myTask = tasks.get(position);

        CheckBox name=(CheckBox)v.findViewById(R.id.itm_taskName);
        name.setChecked(myTask.getCompleted());
        name.setText(myTask.getName());

        TextView dateandtime = (TextView) v.findViewById(R.id.itm_taskDateAndTime);
        dateandtime.setText(myTask.getDate() + " " + myTask.getTime());

        return v;
    }
}