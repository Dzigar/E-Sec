package com.esec.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.esec.model.Todo;

public interface TodoDAO {

	public List<Todo> getAllEvents() throws SQLException;

	public Todo getTodoById(Integer id) throws SQLException;

	public int addTodo(Todo todo) throws SQLException;

	public int delete(Todo todo) throws SQLException;

	public List<Todo> getTodayEvents() throws SQLException;

	public List<Todo> getTomorrowEvents() throws SQLException;

	public List<Todo> getOtherEvents() throws SQLException;

	public List<Todo> getLostEvents() throws SQLException;

	public void updateTodo(Todo todo) throws SQLException;

	public void deleteById(int id) throws SQLException;
}
