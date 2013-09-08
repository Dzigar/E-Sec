package com.esec.ui;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.esec.activity.MainActivity;
import com.esec.adapter.ListEventAdapter;
import com.esec.controller.ControllerListEvent;
import com.esec.listeners.EventListener;
import com.esec.swipe.SwipeDetector;

public class UIListTodo {

	private Context context;
	private static UIListTodo uiListTodo;

	private List<?> list;
	private ListEventAdapter eventAdapter;
	private ListView listEvent;

	private UIListTodo(Context context, ListView listView) {
		this.context = context;
		this.listEvent = listView;
	}

	public static UIListTodo getUiListTodo(Context context, ListView listEvent) {
		if (uiListTodo == null) {
			uiListTodo = new UIListTodo(context, listEvent);
		}
		return uiListTodo;
	}

	public void createListEvent() {
		try {
			list = ControllerListEvent.getTodoController(
					MainActivity.getActivity()).getListTodo();
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}

		eventAdapter = new ListEventAdapter(context, list);

		listEvent.setAdapter(eventAdapter);
		listEvent.setOnTouchListener(SwipeDetector.getSwipeDetector());
		listEvent.setOnItemClickListener(new EventListener(eventAdapter, 0));
	}

	/**
	 * @return the list
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<?> list) {
		this.list = list;
	}

	/**
	 * @return the todoAdapter
	 */
	public ListEventAdapter getEventAdapter() {
		return eventAdapter;
	}

	/**
	 * @param todoAdapter
	 *            the todoAdapter to set
	 */
	public void setEventAdapter(ListEventAdapter eventAdapter) {
		this.eventAdapter = eventAdapter;
	}

	/**
	 * @return the listEvent
	 */
	public ListView getListEvent() {
		return listEvent;
	}

	/**
	 * @param listEvent
	 *            the listEvent to set
	 */
	public void setListEvent(ListView listEvent) {
		this.listEvent = listEvent;
	}

}
