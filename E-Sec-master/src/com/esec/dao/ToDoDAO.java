package com.esec.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.esec.model.Todo;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ToDoDAO extends BaseDaoImpl<Todo, Integer> {

	public ToDoDAO(ConnectionSource connectionSource, Class<Todo> dataClass)
			throws SQLException {
		super(connectionSource, dataClass);
	}

	public List<Todo> getAllEvents() throws SQLException {
		return super.queryForAll();
	}

	@Override
	public Todo queryForId(Integer id) throws SQLException {
		return super.queryForId(id);
	}

	@Override
	public int create(Todo todo) throws SQLException {
		return super.create(todo);
	}

	@Override
	public int delete(Todo todo) throws SQLException {
		return super.delete(todo);
	}

	public List<Todo> getTodayTodos() throws SQLException {
		List<Todo> listToday = new ArrayList<Todo>();
		List<Todo> list = getAllEvents();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String today = dateFormat.format(Calendar.getInstance()
				.getTimeInMillis());
		for (int i = 0; i < list.size(); i++) {
			if (today.equals(dateFormat.format(list.get(i).getDate()))) {
				listToday.add(list.get(i));
			}
		}
		return listToday;
	}

}
