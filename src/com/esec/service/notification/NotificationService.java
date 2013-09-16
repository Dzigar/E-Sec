package com.esec.service.notification;

import java.sql.SQLException;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.connection.HelperFactory;
import com.esec.dao.ToDoDAO;
import com.esec.model.Todo;

public class NotificationService extends Service {

	private final static String SERVICE_IS_RUNNING = "RUNNING";

	private static Context context;

	private ToDoDAO toDoDAO;

	private List<Todo> todayEvent;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		try {
			setContext(getApplicationContext());
			HelperFactory.setHelper(getApplicationContext());
			toDoDAO = HelperFactory.getHelper().getToDoDAO();
			if (!toDoDAO.getTodayTodos().isEmpty()) {
				todayEvent = toDoDAO.getTodayTodos();
				createNewNotification();
			}
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 
	 * @param todo
	 * @return
	 * @throws SQLException
	 */

	public void createNewNotification() throws SQLException {

		Intent notificationIntent = new Intent(getApplicationContext(),
				MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, notificationIntent,
				Intent.FLAG_ACTIVITY_NEW_TASK);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				getApplicationContext())
				.setContentTitle(getString(R.string.app_name))
				.setContentText(
						"You have " + todayEvent.size() + " event for today!")
				.setContentIntent(pendingIntent)
				.setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_launcher);
		((NotificationManager) getApplicationContext().getSystemService(
				Context.NOTIFICATION_SERVICE)).notify(0, builder.build());
	}

	/**
	 * after running service save "flag" true
	 */
	@SuppressWarnings("unused")
	private void setRunning(boolean running) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = pref.edit();

		editor.putBoolean(SERVICE_IS_RUNNING, running);
		editor.apply();
	}

	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean isRunning(Context ctx) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(ctx.getApplicationContext());
		return pref.getBoolean(SERVICE_IS_RUNNING, false);
	}

	/**
	 * @return the context
	 */
	public static Context getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public static void setContext(Context context) {
		NotificationService.context = context;
	}

}
