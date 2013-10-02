package com.esec.dao.implementation;

import java.sql.SQLException;
import java.util.List;

import com.esec.dao.interfaces.NoteDAO;
import com.esec.model.Note;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class NoteDAOImpl extends BaseDaoImpl<Note, Integer> implements NoteDAO {

	public NoteDAOImpl(ConnectionSource connectionSource, Class<Note> dataClass)
			throws SQLException {
		super(connectionSource, dataClass);
	}

	@Override
	public List<Note> getAllNotes() throws SQLException {
		return super.queryForAll();
	}

	@Override
	public Note getNoteById(int id) throws SQLException {
		return super.queryForId(id);
	}

	@Override
	public int addNote(Note note) throws SQLException {
		return create(note);
	}
}
