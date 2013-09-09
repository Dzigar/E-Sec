package com.esec.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.esec.activity.R;
import com.esec.controller.ControllerListEvent;
import com.esec.model.Priorities;
import com.esec.ui.UIListTodo;

public class AddEventDialog implements OnClickListener, OnItemSelectedListener,
		DialogInterface.OnClickListener {

	private Activity activity;
	private AlertDialog dialogEvent;
	private AlertDialog dateDialog;

	private LayoutInflater inflater;

	private EditText titleEvent;
	private EditText descriptionEvent;

	private ImageButton addDate;

	private TextView calendarDate;

	private Spinner selectPriority;
	/**
	 * Date
	 */
	private SimpleDateFormat dateFormat;
	private Date dateEvent;
	private DatePicker dateTask;
	private TimePicker timeTask;

	private Priorities priority;

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
				.setView(inflater.inflate(R.layout.dialog_window, null))
				.setPositiveButton(activity.getString(android.R.string.ok),
						this)
				.setNegativeButton(activity.getString(R.string.cancel), null)
				.setCancelable(false);
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

		selectPriority = (Spinner) dialogEvent.findViewById(R.id.priority);
		selectPriority.setAdapter(new ArrayAdapter<String>(activity,
				android.R.layout.simple_list_item_1, getListPriorities()));
		selectPriority.setOnItemSelectedListener(this);
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
			db.setNegativeButton(R.string.cancel,
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

	private ArrayList<String> getListPriorities() {
		ArrayList<String> priorities = new ArrayList<String>();
		priorities.add(Priorities.IMPORTANT_URGENT.getPriorityText());
		priorities.add(Priorities.IMPORTANT_NOURGENT.getPriorityText());
		priorities.add(Priorities.UNIMPORTANT_URGENT.getPriorityText());
		priorities.add(Priorities.UNIMPORTANT_NOURGENT.getPriorityText());
		return priorities;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int i, long arg3) {
		switch (i) {

		case 0:
			priority = Priorities.IMPORTANT_URGENT;
			break;
		case 1:
			priority = Priorities.IMPORTANT_NOURGENT;
			break;
		case 2:
			priority = Priorities.UNIMPORTANT_URGENT;
			break;
		case 3:
			priority = Priorities.UNIMPORTANT_NOURGENT;
			break;

		default:
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

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
					ControllerListEvent.getTodoController(activity).addEvent(
							titleEvent.getText().toString(),
							descriptionEvent.getText().toString(),
							dateEvent.getTime(), priority);
					UIListTodo.getUiListTodo(null, null).createListEvent();
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