package com.esec.service;

import java.sql.SQLException;

import android.util.Log;

import com.esec.connection.DatabaseHelper;
import com.esec.connection.HelperFactory;
import com.esec.model.Status;
import com.esec.model.Todo;

public class StatusService {

	public static Todo updateStatus(Todo todo) {
		if (todo.getDate() < System.currentTimeMillis()) {
			todo.setStatus(Status.STATUS_FAILED);
			DatabaseHelper databaseHelper = HelperFactory.getHelper();
			try {
				databaseHelper.getTodoDAO().updateTodo(todo);
			} catch (SQLException e) {
				Log.i("StatusService", e.getLocalizedMessage());
			}
		}
		return todo;
	}
}
