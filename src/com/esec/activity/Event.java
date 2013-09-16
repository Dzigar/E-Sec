package com.esec.activity;

import java.sql.SQLException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.esec.connection.HelperFactory;
import com.esec.dao.ToDoDAO;
import com.esec.model.Font;
import com.esec.model.Priorities;
import com.esec.model.Status;
import com.esec.model.Todo;
import com.esec.service.DateService;
import com.esec.userinterface.dialog.DateDialog;

public class Event extends Activity implements OnClickListener {

	private ToDoDAO toDoDAO;

	private ImageButton changeDate; // button change date event
	private ImageButton changePriority; // button change priority event

	private TextView titleEvent; // title event
	private TextView descriptionEvent; // description event
	private TextView dateEvent; // text date event
	private TextView priority; // text priority event
	private Todo todo;

	private DatePicker dateTask; // Picker of date event
	private TimePicker timeTask; // Picker of time event

	private CheckBox status; // check box status event

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_activity);
		try {
			toDoDAO = HelperFactory.getHelper().getToDoDAO();
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

		priority = (TextView) findViewById(R.id.priority);
		priority.setTypeface(Font.getFonts(this).getFontMenu());

		// Loading event id with extras
		Bundle extras = getIntent().getExtras();
		int id = extras.getInt(getString(R.string.id));

		try {
			todo = toDoDAO.queryForId(id);
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}

		titleEvent.setText(todo.getTitle());
		descriptionEvent.setText(todo.getDescription());
		dateEvent.setText(DateService.convertDate(todo.getDate()));
		priority.setText(todo.getPriority().getPriorityText());

		changeDate = (ImageButton) findViewById(R.id.change_date);
		changeDate.setOnClickListener(this);
		changePriority = (ImageButton) findViewById(R.id.change_priority);
		changePriority.setOnClickListener(this);

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
		case R.id.change_priority:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.change_priority)
					.setCancelable(false)
					.setSingleChoiceItems(Priorities.getValuesTitleList(),
							todo.getPriority().getId(),
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									todo.setPriority(Priorities
											.getPriorityById(which));
								}
							})
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									try {
										toDoDAO.update(todo);
										restart();
									} catch (SQLException e) {
										Log.i(getClass().getName(),
												e.getLocalizedMessage());
									}
								}
							})

					.setNegativeButton(android.R.string.cancel,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();

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
				toDoDAO.update(todo);
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
		try {
			toDoDAO.update(getTodo());

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
