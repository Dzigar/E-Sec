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
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.adapter.ListEventAdapter;
import com.esec.connection.HelperFactory;
import com.esec.dao.interfaces.TodoDAO;
import com.esec.listeners.EventClickListener;
import com.esec.model.Font;
import com.esec.service.MetricsService;
import com.esec.swipe.SwipeDetector;

public class EventsFragment extends Fragment {

	private static final String TAG_TODAY = "tagToday";
	private static final String TAG_TOMORROW = "tagTomorrow";
	private static final String TAG_OTHER = "tagOther";
	private static final String TAG_ARCHIVE = "tagArchive";

	private TabHost tabHost;
	private TabHost.TabSpec tabToday;
	private TabHost.TabSpec tabTomorrow;
	private TabHost.TabSpec tabOther;
	private TabHost.TabSpec tabArchive;

	private List<?> list;
	private ListEventAdapter eventAdapter;
	private ListView listView;

	private TodoDAO todoDAO;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		try {
			HelperFactory.setHelper(MainActivity.getActivity()
					.getApplicationContext());
			todoDAO = HelperFactory.getHelper().getTodoDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		View rootView = inflater.inflate(R.layout.events_fragment, container,
				false);

		tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);

		tabHost.setup(); // init.

		addTab(tabToday, TAG_TODAY, getString(R.string.today), R.string.today);
		addTab(tabTomorrow, TAG_TOMORROW, getString(R.string.tomorrow),
				R.string.tomorrow);
		addTab(tabOther, TAG_OTHER, getString(R.string.other), R.string.other);
		addTab(tabArchive, TAG_ARCHIVE, getString(R.string.archive),
				R.string.archive);
		for (int i = 0; i < tabHost.getTabWidget().getTabCount(); i++) {
			tabHost.getTabWidget().getChildAt(i).getLayoutParams().width = MetricsService
					.getWidthWindow() / 3;
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
			final int contentRes) {
		tabSpec = tabHost.newTabSpec(tag);// create tab and specify the tag
		tabSpec.setIndicator(createTabView(tabHost.getContext(), indicator)); // title
																				// tag
		tabSpec.setContent(new TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				return getListEventsBy(contentRes);
			}
		});
		tabHost.addTab(tabSpec);
	}

	/**
	 * Get list of events by day
	 * 
	 * @param str
	 * @return
	 */
	private ListView getListEventsBy(int str) {
		listView = new ListView(getActivity());
		listView.setSelector(R.color.backgroun_main_fragment);
		switch (str) {
		case R.string.today:
			try {
				list = todoDAO.getTodayEvents();
			} catch (SQLException e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}

			break;
		case R.string.tomorrow:
			try {
				list = todoDAO.getTomorrowEvents();
			} catch (SQLException e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
			break;
		case R.string.other:
			try {
				list = todoDAO.getOtherEvents();
			} catch (SQLException e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
			break;
		case R.string.archive:
			try {
				list = todoDAO.getLostEvents();
			} catch (SQLException e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
			break;
		}

		eventAdapter = new ListEventAdapter(MainActivity.getActivity(), list);
		listView.setAdapter(eventAdapter);
		listView.setOnItemClickListener(new EventClickListener(eventAdapter));
		listView.setOnTouchListener(SwipeDetector.getSwipeDetector());
		listView.setDivider(MainActivity.getActivity().getResources()
				.getDrawable(R.drawable.divider_list_events));
		listView.setDividerHeight(2);
		return listView;
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
}
