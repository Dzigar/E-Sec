package com.esec.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.adapter.ListShoppingAdapter;
import com.esec.listeners.ShoppingClickListener;
import com.esec.swipe.SwipeDetector;

public class ShoppingFragment extends Fragment implements OnTabChangeListener {

	private static final String TAG_SHOPPING = "tagShopping";

	private TabHost tabHost;
	private TabHost.TabSpec shoppingList;

	private ListShoppingAdapter shoppingAdapter;
	private ListView listShopping;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.events_fragment, container,
				false);

		tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);

		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);

		int x = metrics.widthPixels;

		tabHost.setup(); // init.

		addTab(shoppingList, TAG_SHOPPING, getString(R.string.sh_list),
				R.string.sh_list);
		for (int i = 0; i < tabHost.getTabWidget().getTabCount(); i++) {
			tabHost.getTabWidget().getChildAt(i).getLayoutParams().width = x
					/ tabHost.getChildCount();
		}
		return rootView;
	}

	private void addTab(TabHost.TabSpec tabSpec, String tag, String indicator,
			final int contentRes) {
		tabSpec = tabHost.newTabSpec(tag);// create tab and specify the tag
		tabSpec.setIndicator(indicator); // title tag
		tabSpec.setContent(new TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				return getShoppingList(contentRes);
			}
		});
		tabHost.addTab(tabSpec);
	}

	private ListView getShoppingList(int str) {
		listShopping = new ListView(getActivity());
		shoppingAdapter = new ListShoppingAdapter(MainActivity.getActivity());
		listShopping.setAdapter(shoppingAdapter);
		listShopping.setOnItemClickListener(new ShoppingClickListener(
				shoppingAdapter));
		listShopping.setOnTouchListener(SwipeDetector.getSwipeDetector());

		return listShopping;
	}

	@Override
	public void onTabChanged(String tabId) {

	}
}
