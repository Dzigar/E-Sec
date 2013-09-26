package com.esec.userinterface.dialog;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.activity.fragment.EventsFragment;
import com.esec.connection.HelperFactory;
import com.esec.model.Todo;
import com.esec.service.DateService;

public class AddEventDialog implements OnClickListener,
		DialogInterface.OnClickListener {

	private Activity activity;
	private AlertDialog dialogEvent;
	private AlertDialog dateDialog;

	private LayoutInflater inflater;

	private EditText titleEvent;
	private EditText descriptionEvent;

	private ImageButton addDate;
	private CheckBox important;
	private TextView calendarDate;
	private boolean isImportant;

	/**
	 * Date
	 */
	private SimpleDateFormat dateFormat;
	private Date dateEvent;
	private DatePicker dateTask;
	private TimePicker timeTask;

	public AddEventDialog(Activity activity) throws SQLException {
		this.activity = activity;
		init();
	}

	/**
	 * init. component of dialog window
	 * 
	 * @throws SQLException
	 */
	@SuppressLint("SimpleDateFormat")
	private void init() throws SQLException {

		inflater = activity.getLayoutInflater();

		/**
		 * Dialog window for adding task
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(activity.getString(R.string.add_event))
				.setView(inflater.inflate(R.layout.add_event_dialog, null))
				.setPositiveButton(activity.getString(android.R.string.ok),
						this)
				.setNegativeButton(activity.getString(android.R.string.cancel),
						this).setCancelable(false);
		dialogEvent = builder.create();
		dialogEvent.show();
		titleEvent = (EditText) dialogEvent.findViewById(R.id.titleEvent);
		descriptionEvent = (EditText) dialogEvent
				.findViewById(R.id.descriptionEvent);

		addDate = (ImageButton) dialogEvent.findViewById(R.id.addDate);
		addDate.setOnClickListener(this);

		dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		dateEvent = new Date();
		calendarDate = (TextView) dialogEvent.findViewById(R.id.calendarDate);

		important = (CheckBox) dialogEvent.findViewById(R.id.important);
		important.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (important.isChecked()) {
					isImportant = true;
				} else
					isImportant = false;
			}
		});

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.addDate:
			/**
			 * Dialog window for adding date
			 */
			AlertDialog.Builder db = new AlertDialog.Builder(activity);
			db.setTitle(activity.getString(R.string.add_time_event));
			db.setView(inflater.inflate(R.layout.date_dialog, null));
			db.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							/**
							 * Set date of event
							 */
							String month = Integer.toString(dateTask.getMonth() + 1);
							if (dateTask.getMonth() < 10) {
								month = "0" + month;
							}

							try {
								dateEvent = dateFormat.parse(dateTask
										.getDayOfMonth()
										+ "."
										+ month
										+ "."
										+ dateTask.getYear()
										+ " "
										+ timeTask.getCurrentHour()
										+ ":"
										+ timeTask.getCurrentMinute());
							} catch (ParseException e) {
								Log.i(getClass().getName(),
										e.getLocalizedMessage());
							}
							calendarDate.setText(DateService
									.convertDate(dateEvent.getTime()));

							dateDialog.cancel();
						}
					});
			db.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dateDialog.cancel();
						}
					});
			dateDialog = db.create();
			dateDialog.show();
			dateTask = (DatePicker) dateDialog.findViewById(R.id.dateEvent);
			timeTask = (TimePicker) dateDialog.findViewById(R.id.timeEvent);
			break;
		}
	}

	/**
	 * Show dialog to add event
	 */
	public void show() {
		dialogEvent.show();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case -2:

			break;
		case -1:
			if (titleEvent.getText().toString().equals("")
					|| descriptionEvent.getText().toString().equals("")) {
				Toast toast = Toast.makeText(activity,
						activity.getString(R.string.field_is_empty),
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			} else if (calendarDate.getText().toString().contains("Set")) {
				Toast toast = Toast.makeText(activity,
						activity.getString(R.string.set_date),
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			} else {
				try {
					HelperFactory
							.getHelper()
							.getTodoDAO()
							.addTodo(
									new Todo(titleEvent.getText().toString(),
											descriptionEvent.getText()
													.toString(), dateEvent
													.getTime(), isImportant));
					MainActivity.getActivity().updateFragment(
							new EventsFragment());
					dialogEvent.cancel();
				} catch (Exception e) {
					Log.i(getClass().getName(), e.getLocalizedMessage());
				}
			}
		default:
			break;
		}
	}

}