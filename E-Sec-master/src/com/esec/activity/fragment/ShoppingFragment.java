package com.esec.activity.fragment;

import java.sql.SQLException;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.adapter.ListShoppingAdapter;
import com.esec.connection.HelperFactory;
import com.esec.listeners.ShoppingClickListener;
import com.esec.model.Font;
import com.esec.model.ShoppingList;
import com.esec.service.MetricsService;
import com.esec.swipe.SwipeDetector;

public class ShoppingFragment extends Fragment implements OnTabChangeListener {

	private TabHost tabHost;
	private TabHost.TabSpec shoppingList;

	private ListShoppingAdapter shoppingAdapter;
	private ListView listShopping;
	private List<ShoppingList> shoppingLists;
	public static int idSelectedList = 1;

	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.events_fragment, container, false);

		tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);

		tabHost.setup();
		tabHost.setOnTabChangedListener(this);

		HelperFactory.setHelper(getActivity());
		// check whether created a shopping list
		try {
			if (HelperFactory.getHelper().getShoppingListDAO().getLists()
					.isEmpty()) {
				HelperFactory
						.getHelper()
						.getShoppingListDAO()
						.createNewList(
								new ShoppingList(
										getString(R.string.default_title_list)));
			}
			shoppingLists = HelperFactory.getHelper().getShoppingListDAO()
					.getLists();
		} catch (SQLException e1) {
			Log.i(getClass().getName(), e1.getLocalizedMessage());
		}

		for (int i = 0; i < shoppingLists.size(); i++) {
			addTab(shoppingList, shoppingLists.get(i).getTitle(),
					getString(R.string.sh_list), shoppingLists.get(i)
							.getTitle());
			tabHost.getTabWidget().getChildAt(i).getLayoutParams().width = MetricsService
					.getWidthWindow() / getChildCounts();
		}
		return rootView;
	}

	/**
	 * 
	 * @param tabSpec
	 * @param tag
	 * @param indicator
	 * @param contentRes
	 */
	private void addTab(TabHost.TabSpec tabSpec, String tag, String indicator,
			final String content) {
		tabSpec = tabHost.newTabSpec(tag);// create tab and specify the tag
		tabSpec.setIndicator(createTabView(tabHost.getContext(), content)); // title
		// tag
		tabSpec.setContent(new TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				return getShoppingList(content);
			}
		});
		tabHost.addTab(tabSpec);
	}

	/**
	 * Create custom tab for tab widget
	 * 
	 * @param context
	 * @param text
	 * @return
	 */
	private View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.drawable.tab_bg,
				null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		tv.setTypeface(Font.getFonts(context).getFontMenu());
		tv.setTextColor(MainActivity.getActivity().getResources()
				.getColor(R.color.background_actionbar));
		return view;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	private ListView getShoppingList(String context) {
		listShopping = new ListView(getActivity());
		shoppingAdapter = new ListShoppingAdapter(MainActivity.getActivity(),
				context);
		listShopping.setAdapter(shoppingAdapter);
		listShopping.setOnItemClickListener(new ShoppingClickListener(
				shoppingAdapter));
		listShopping.setOnTouchListener(SwipeDetector.getSwipeDetector());

		return listShopping;
	}

	private int getChildCounts() {
		if (shoppingLists.size() > 3) {
			return 3;
		} else
			return shoppingLists.size();
	}

	@Override
	public void onTabChanged(String arg0) {
		try {
			idSelectedList = HelperFactory.getHelper().getShoppingListDAO()
					.getListByTitle(arg0).getId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
