package com.esec.activity.fragment;

import java.sql.SQLException;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.adapter.ListEventAdapter;
import com.esec.controller.ControllerListEvent;
import com.esec.listeners.EventClickListener;
import com.esec.swipe.SwipeDetector;

public class ListEventFragment extends Fragment {
	public static final String ARG_ITEM_NUMBER = "menu_item_selected";

	private ListView listEvent;

	private List<?> list;
	private ListEventAdapter eventAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.event_fragment, container,
				false);

		listEvent = (ListView) rootView.findViewById(R.id.listEvent);
		try {
			list = ControllerListEvent.getTodoController(
					MainActivity.getActivity()).getListTodo();
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}

		eventAdapter = new ListEventAdapter(MainActivity.getActivity(), list);

		listEvent.setAdapter(eventAdapter);
		listEvent.setOnTouchListener(SwipeDetector.getSwipeDetector());
		listEvent
				.setOnItemClickListener(new EventClickListener(eventAdapter));

		return rootView;
	}
}
