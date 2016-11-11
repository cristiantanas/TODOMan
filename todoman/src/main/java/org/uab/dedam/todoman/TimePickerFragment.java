package org.uab.dedam.todoman;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TimePicker;
import android.widget.EditText;
import android.support.v4.app.DialogFragment;


public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
private  int hour;
private int min;
private  Boolean is24h;
private Handler t;

public  TimePickerFragment(int hour, int min, Handler t) {
this.hour=hour;
this.min=min;
this.is24h=true;
this.t=t;
}

@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
return new TimePickerDialog(getActivity(), this, hour, min, is24h);
}


@Override
public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
Message msg = new Message();
Bundle data = new Bundle();
data.putString("selected_time", hourOfDay + ":" + minute);
msg.setData(data);
t.sendMessage(msg);
}
}
