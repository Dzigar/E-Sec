package com.esec.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.util.Log;

import com.esec.activity.MainActivity;
import com.esec.activity.fragment.ListNoteFragment;
import com.esec.comparator.ComparatorNoteService;
import com.esec.connection.HelperFactory;
import com.esec.dao.NoteDAO;
import com.esec.model.Note;

public class ControllerListNote {

	private static ControllerListNote controllerListNote;
	
	private NoteDAO noteDAO;
	
	private ControllerListNote(Activity activity) throws SQLException {
		HelperFactory.setHelper(activity.getApplicationContext());
		noteDAO = HelperFactory.getHelper().getNoteDAO();

	}
	
	public static ControllerListNote getNoteController(Activity activity)
			throws SQLException {
		if (controllerListNote == null) {
			controllerListNote = new ControllerListNote(activity);
		}
		return controllerListNote;
	}
	
	public void addNote(String title, String desc, String date) throws java.sql.SQLException, ParseException {
		Note note = new Note(title, desc, date);
		noteDAO.create(note);
		
	}
	
	public List<Note> getListNote() throws SQLException {
		List<Note> listNote= new ArrayList<Note>();
		try {
			Iterator<Note> iterator = noteDAO.getAllNotes().iterator();
			while (iterator.hasNext()) {
				listNote.add(iterator.next());
			}
		} catch (Exception e) {
			Log.i("deb", e.getLocalizedMessage());
		}
		Collections.sort(listNote, new ComparatorNoteService()); // Sort list
		return listNote;
	}
	
	public void deleteNote(int position) throws SQLException {
		List<Note> listNote = noteDAO.getAllNotes();
		Collections.sort(listNote, new ComparatorNoteService());
		noteDAO.delete(listNote.get(position));
		MainActivity.getActivity().updateFragment(new ListNoteFragment());
	}
	
}






