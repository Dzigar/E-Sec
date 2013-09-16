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
import com.esec.adapter.ListShoppingAdapter;
import com.esec.controller.ControllerListShopping;
import com.esec.listeners.ShoppingListener;
import com.esec.model.Shopping;
import com.esec.swipe.SwipeDetector;

public class ListShoppingFragment extends Fragment {

	private List<Shopping> list;
	private ListShoppingAdapter shoppingAdapter;
	private ListView listShopping;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.event_fragment, container,
				false);

		listShopping = (ListView) rootView.findViewById(R.id.listEvent);
		try {
			list = ControllerListShopping.getShoppingController(
					MainActivity.getActivity()).getListShopping();
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}

		shoppingAdapter = new ListShoppingAdapter(MainActivity.getActivity(),
				list);

		listShopping.setAdapter(shoppingAdapter);
		listShopping.setOnTouchListener(SwipeDetector.getSwipeDetector());
		listShopping.setOnItemClickListener(new ShoppingListener(
				shoppingAdapter, 0));

		return rootView;
	}
}
