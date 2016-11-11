package org.uab.dedam.todoman;

import android.app.Dialog;
//import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.DatePicker;
import android.widget.EditText;
import android.support.v4.app.DialogFragment;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
private  int year;
private int month;
private int day;
private Handler h;

public  DatePickerFragment(int year, int month, int day, Handler h) {
this.year=year;
this.month=month;
this.day=day;
this.h=h;
}

@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
return new DatePickerDialog(getActivity(), this, year, month, day);
}

@Override
public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
Message msg = new Message();
Bundle data = new Bundle();
monthOfYear=monthOfYear+1;
data.putString("selected_date", day + "/" + monthOfYear + "/" + year);
msg.setData(data);
h.sendMessage(msg);
}
}
