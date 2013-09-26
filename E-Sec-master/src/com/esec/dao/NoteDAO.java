package com.esec.dao;

import java.sql.SQLException;
import java.util.List;

import com.esec.model.Note;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class NoteDAO extends BaseDaoImpl<Note, Integer> {

	public NoteDAO(ConnectionSource connectionSource, Class<Note> dataClass)
			throws SQLException {
		super(connectionSource, dataClass);
	}

	public List<Note> getAllNotes() throws SQLException {
		return super.queryForAll();
	}

	@Override
	public Note queryForId(Integer id) throws SQLException {
		return super.queryForId(id);
	}

	@Override
	public int create(Note note) throws SQLException {
		return super.create(note);
	}

	@Override
	public int delete(Note note) throws SQLException {
		return super.delete(note);
	}

}
