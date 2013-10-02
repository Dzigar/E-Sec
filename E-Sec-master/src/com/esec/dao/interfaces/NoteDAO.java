package com.esec.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.esec.model.Note;

public interface NoteDAO {

	public int addNote(Note note) throws SQLException;

	public List<Note> getAllNotes() throws SQLException;

	public Note getNoteById(int id) throws SQLException;

}
