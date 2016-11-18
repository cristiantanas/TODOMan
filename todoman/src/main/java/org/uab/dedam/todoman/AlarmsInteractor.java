package org.uab.dedam.todoman;

import android.content.Context;

public interface AlarmsInteractor {
	void setNewAlarm(Context activityContext, task myTask);
	void unsetOldAlarm(Context activityContext, task myTask);
}
