package com.esec.userinterface.dialog;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.activity.fragment.NotesFragment;
import com.esec.connection.HelperFactory;
import com.esec.model.Note;

public class AddNoteDialog implements DialogInterface.OnClickListener {

	private Activity activity;
	private LayoutInflater inflater;

	private AlertDialog dialogNote;

	private SimpleDateFormat dateFormat;
	private Date date;
	private EditText titleNote;
	private EditText descNote;
	private String dateString;

	@SuppressLint("SimpleDateFormat")
	public AddNoteDialog(Activity activity) throws SQLException {
		this.activity = activity;
		inflater = activity.getLayoutInflater();

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(activity.getString(R.string.add_note))
				.setView(inflater.inflate(R.layout.add_note_dialog, null))
				.setPositiveButton(activity.getString(android.R.string.ok),
						this)
				.setNegativeButton(activity.getString(android.R.string.cancel),
						this).setCancelable(false);

		dialogNote = builder.create();
		dialogNote.show();

		titleNote = (EditText) dialogNote.findViewById(R.id.titleNote);
		descNote = (EditText) dialogNote.findViewById(R.id.descNote);

		dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case DialogInterface.BUTTON_NEGATIVE:
			dialog.cancel();
			break;
		case DialogInterface.BUTTON_POSITIVE:
			try {
				if (titleNote.getText().toString().equals("")
						|| descNote.getText().toString().equals("")) {
					Toast toast = Toast.makeText(activity,
							activity.getString(R.string.field_is_empty),
							Toast.LENGTH_SHORT);
					toast.show();
					break;
				}
				date = new Date();
				dateString = dateFormat.format(date);
				HelperFactory
						.getHelper()
						.getNoteDAO()
						.addNote(
								new Note(titleNote.getText().toString(),
										descNote.getText().toString(),
										dateString));
				MainActivity.getActivity().updateFragment(new NotesFragment());
			} catch (Exception e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}

			dialog.cancel();
			break;
		default:
			break;
		}

	}

	/**
	 * Show dialog to add note
	 */
	public void show() {
		dialogNote.show();
	}

}
