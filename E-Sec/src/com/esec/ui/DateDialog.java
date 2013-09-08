package com.esec.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.esec.activity.Event;
import com.esec.activity.R;
import com.esec.service.DateService;

public class DateDialog {

	private LayoutInflater inflater;
	private DatePicker dateTask;
	private TimePicker timeTask;
	private Date dateEvent;
	private SimpleDateFormat dateFormat;

	private AlertDialog dateDialog;

	private Event event;

	public DateDialog(LayoutInflater inflater, DatePicker dateTask,
			TimePicker timeTask, Event event) {
		this.inflater = inflater;
		this.dateTask = dateTask;
		this.timeTask = timeTask;
		this.event = event;
		this.dateEvent = new Date();
		this.dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	}

	/**
	 * Create dialog window of set date event
	 */
	public void createDateDialog() {
		AlertDialog.Builder db = new AlertDialog.Builder(event);
		db.setTitle(event.getString(R.string.add_time_event));
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
							Log.i(getClass().getName(), e.getLocalizedMessage());
						}
						event.getDateEvent().setText(
								DateService.convertDate(dateEvent.getTime()));
						event.getTodo().setDate(dateEvent.getTime());
						event.setTodo(event.getTodo());
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
	}

}
