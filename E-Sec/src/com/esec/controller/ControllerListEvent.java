package com.esec.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.util.Log;

import com.esec.connection.DatabaseHelper;
import com.esec.connection.HelperFactory;
import com.esec.dao.ToDoDAO;
import com.esec.model.Priorities;
import com.esec.model.Todo;
import com.esec.service.ComparatorEventService;
import com.esec.service.notification.AlarmService;

public class ControllerListEvent {

	private static ControllerListEvent controllerListTodo;

	private ToDoDAO toDoDAO;

	private ControllerListEvent(Activity activity) throws SQLException {
		HelperFactory.setHelper(activity.getApplicationContext());
		toDoDAO = HelperFactory.getHelper().getToDoDAO();

	}

	public static ControllerListEvent getTodoController(Activity activity)
			throws SQLException {
		if (controllerListTodo == null) {
			controllerListTodo = new ControllerListEvent(activity);
		}
		return controllerListTodo;
	}

	public void addEvent(String title, String description, long date,
			Priorities priorities) throws java.sql.SQLException, ParseException {
		Todo todo = new Todo(title, description, date, priorities);
		toDoDAO.create(todo);

		AlarmService.getAlarmService().disturb();
	}

	public List<?> getListTodo() throws SQLException {
		List<Todo> listTodo = new ArrayList<Todo>();
		try {
			Iterator<Todo> iterator = toDoDAO.getAllEvents().iterator();
			while (iterator.hasNext()) {
				listTodo.add(iterator.next());
			}
		} catch (Exception e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		Collections.sort(listTodo, new ComparatorEventService()); // Sort list

		return listTodo;
	}

	public void deleteTodo(int position) throws SQLException {
		List<Todo> listTodo = toDoDAO.getAllEvents();
		Collections.sort(listTodo, new ComparatorEventService());
		toDoDAO.delete(listTodo.get(position));
	}
}
