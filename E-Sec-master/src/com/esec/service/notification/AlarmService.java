package com.esec.service.notification;

import java.sql.SQLException;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.esec.activity.MainActivity;

public class AlarmService {

	private static AlarmService alarmService;
	private PendingIntent pendingIntent;
	private Context context;

	private AlarmService() {
		context = MainActivity.getActivity();
	}

	public static AlarmService getAlarmService() {
		if (alarmService == null) {
			alarmService = new AlarmService();
		}
		return alarmService;
	}

	public void disturb() throws SQLException {
		Intent intent = new Intent(context, NotificationReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC,
				System.currentTimeMillis() + 100000000, 60000, pendingIntent);
	}
}
