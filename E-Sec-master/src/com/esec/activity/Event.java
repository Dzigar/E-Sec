package com.esec.activity;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.esec.connection.HelperFactory;
import com.esec.dao.interfaces.TodoDAO;
import com.esec.model.Font;
import com.esec.model.Status;
import com.esec.model.Todo;
import com.esec.service.DateService;
import com.esec.userinterface.dialog.DateDialog;

public class Event extends Activity implements OnClickListener {

	private TodoDAO todoDAO;

	private ImageButton changeDate; // button change date event

	private TextView titleEvent; // title event
	private TextView descriptionEvent; // description event
	private TextView dateEvent; // text date event
	private Todo todo;

	private DatePicker dateTask; // Picker of date event
	private TimePicker timeTask; // Picker of time event

	private CheckBox status; // check box status event
	private CheckBox important;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_activity);
		try {
			todoDAO = HelperFactory.getHelper().getTodoDAO();
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}

		titleEvent = (TextView) findViewById(R.id.title_task);
		titleEvent.setTypeface(Font.getFonts(this).getFontEventList());

		descriptionEvent = (TextView) findViewById(R.id.body_task);
		descriptionEvent.setTypeface(Font.getFonts(this)
				.getFontEventDescription());
		setDateEvent((TextView) findViewById(R.id.date_task));
		dateEvent.setTypeface(Font.getFonts(this).getFontMenu());

		// Loading event id with extras
		Bundle extras = getIntent().getExtras();
		int id = extras.getInt(getString(R.string.id));

		try {
			todo = todoDAO.getTodoById(id);
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}

		important = (CheckBox) findViewById(R.id.important);
		important.setTypeface(Font.getFonts(this).getFontMenu());
		System.out.println(todo.isImportant());
		important.setChecked(todo.isImportant());
		important.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (important.isChecked()) {
					todo.setImportant(true);
				} else
					todo.setImportant(false);
				try {
					todoDAO.updateTodo(todo);
				} catch (SQLException e) {
					Log.i(getClass().getName(), e.getLocalizedMessage());
				}
				restart();
			}
		});

		titleEvent.setText(todo.getTitle());
		descriptionEvent.setText(todo.getDescription());
		dateEvent.setText(DateService.convertDate(todo.getDate()));

		changeDate = (ImageButton) findViewById(R.id.change_date);
		changeDate.setOnClickListener(this);

		status = (CheckBox) findViewById(R.id.statusCheckBox);
		setTextStatus();
		status.setOnClickListener(this);
	}

	/**
	 * ClickListener for change date, status and priority event
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.change_date:

			DateDialog dialog = new DateDialog(getLayoutInflater(), dateTask,
					timeTask, this);
			dialog.createDateDialog();
			break;
		case R.id.statusCheckBox:
			if (status.isChecked()) {
				status.setText(R.string.done);
				todo.setStatus(Status.STATUS_MADE);
			} else {
				status.setText(R.string.performed);
				todo.setStatus(Status.STATUS_WAIT);
			}
			try {
				todoDAO.updateTodo(todo);
			} catch (SQLException e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		stopService(getIntent());
	}

	/**
	 * 
	 */
	private void setTextStatus() {
		if (todo.getStatus() == Status.STATUS_FAILED) {
			status.setText(R.string.failed);
			status.setEnabled(false);
		} else if (todo.getStatus() == Status.STATUS_WAIT) {
			status.setText(R.string.performed);
			status.setChecked(false);
		} else {
			status.setText(R.string.done);
			status.setChecked(true);
		}
	}

	/**
	 * Restart activity after change todo
	 */
	private void restart() {
		Intent intent = new Intent(this, Event.class);
		// write todo id in intent
		intent.putExtra(MainActivity.getActivity().getString(R.string.id),
				todo.getId());
		overridePendingTransition(0, 0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);
	}

	/**
	 * @return the dateEvent
	 */
	public TextView getDateEvent() {
		return dateEvent;
	}

	/**
	 * @param dateEvent
	 *            the dateEvent to set
	 */
	public void setDateEvent(TextView dateEvent) {
		this.dateEvent = dateEvent;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}
}
