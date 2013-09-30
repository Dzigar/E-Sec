package com.esec.activity;

import java.sql.SQLException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.esec.connection.HelperFactory;
import com.esec.dao.NoteDAO;
import com.esec.model.Font;
import com.esec.model.Note;

public class NoteActivity extends Activity{

	private TextView titleNote;
	private TextView descriptionNote;
	
	private NoteDAO noteDAO;
	private Note note;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_activity);
		try {
			noteDAO = HelperFactory.getHelper().getNoteDAO();
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
				
		titleNote = (TextView) findViewById(R.id.noteTitle);
		titleNote.setTypeface(Font.getFonts(this).getFontEventList());
		
		descriptionNote = (TextView) findViewById(R.id.descriptionNote);
		descriptionNote.setTypeface(Font.getFonts(this).getFontEventList());
		
		Bundle extras = getIntent().getExtras();
		int id = extras.getInt("note");;
		
		try {
			note = noteDAO.queryForId(id);
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		
		titleNote.setText(note.getTitle());
		descriptionNote.setText(note.getDesc());
	}

	
}
