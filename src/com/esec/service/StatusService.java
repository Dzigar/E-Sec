package com.esec.service;

import java.sql.SQLException;

import com.esec.connection.DatabaseHelper;
import com.esec.connection.HelperFactory;
import com.esec.dao.ToDoDAO;
import com.esec.model.Status;
import com.esec.model.Todo;

public class StatusService {

	public static Todo updateStatus(Todo todo) {
		if (todo.getDate() < System.currentTimeMillis()) {
			todo.setStatus(Status.STATUS_FAILED);
			DatabaseHelper databaseHelper = HelperFactory.getHelper();
			try {
				ToDoDAO toDoDAO = databaseHelper.getToDoDAO();
				toDoDAO.update(todo);
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		return todo;
	}
}
