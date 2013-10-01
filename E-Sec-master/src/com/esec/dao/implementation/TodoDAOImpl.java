package com.esec.dao.implementation;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.esec.activity.MainActivity;
import com.esec.activity.fragment.EventsFragment;
import com.esec.comparator.ComparatorListEvents;
import com.esec.dao.interfaces.TodoDAO;
import com.esec.model.Todo;
import com.esec.service.DateService;
import com.esec.string.Strings;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

public class TodoDAOImpl extends BaseDaoImpl<Todo, Integer> implements TodoDAO {

	public TodoDAOImpl(ConnectionSource connectionSource, Class<Todo> dataClass)
			throws SQLException {
		super(connectionSource, dataClass);
	}

	public List<Todo> getAllEvents() throws SQLException {
		return super.queryForAll();
	}

	@Override
	public int delete(Todo todo) throws SQLException {
		return super.delete(todo);
	}

	@Override
	public List<Todo> getTodayEvents() throws SQLException {
		QueryBuilder<Todo, Integer> queryBuilder = this.queryBuilder();
		queryBuilder.where().between(Strings.COLUMN_DATE,
				DateService.getTodayDate(), DateService.getTomorrowDate());
		PreparedQuery<Todo> preparedQuery = queryBuilder.prepare();
		List<Todo> list = query(preparedQuery);
		Collections.sort(list, new ComparatorListEvents());
		return list;
	}

	@Override
	public List<Todo> getTomorrowEvents() throws SQLException {
		QueryBuilder<Todo, Integer> queryBuilder = queryBuilder();
		queryBuilder.where().between(Strings.COLUMN_DATE,
				DateService.getTomorrowDate(),
				DateService.getDifferenceTomorrow());
		PreparedQuery<Todo> preparedQuery = queryBuilder.prepare();
		List<Todo> list = query(preparedQuery);
		Collections.sort(list, new ComparatorListEvents());
		return list;
	}

	@Override
	public List<Todo> getOtherEvents() throws SQLException {
		QueryBuilder<Todo, Integer> queryBuilder = queryBuilder();
		queryBuilder.where().between(Strings.COLUMN_DATE,
				DateService.getDifferenceTomorrow(), DateService.getInterval());
		PreparedQuery<Todo> preparedQuery = queryBuilder.prepare();
		List<Todo> list = query(preparedQuery);
		Collections.sort(list, new ComparatorListEvents());
		return list;
	}

	@Override
	public List<Todo> getLostEvents() throws SQLException {
		QueryBuilder<Todo, Integer> queryBuilder = queryBuilder();
		queryBuilder.where()
				.between(Strings.COLUMN_DATE,
						DateService.getDifferenceForToday(),
						DateService.getTodayDate());
		PreparedQuery<Todo> preparedQuery = queryBuilder.prepare();
		List<Todo> list = query(preparedQuery);
		Collections.sort(list, new ComparatorListEvents());
		return list;
	}

	@Override
	public Todo getTodoById(Integer id) throws java.sql.SQLException {
		return super.queryForId(id);
	}

	@Override
	public int addTodo(Todo todo) throws java.sql.SQLException {
		return super.create(todo);
	}

	@Override
	public void updateTodo(Todo todo) throws SQLException {
		super.update(todo);
	}

	@Override
	public void deleteById(int id) throws SQLException {
		super.deleteById(id);
		MainActivity.getActivity().updateFragment(new EventsFragment());
	}
}
