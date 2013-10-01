package com.esec.listeners;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LauncherActivity.ListItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.activity.fragment.EventsFragment;
import com.esec.activity.fragment.NotesFragment;
import com.esec.activity.fragment.ShoppingFragment;
import com.esec.service.MenuService;

public class MenuClickListener extends ListItem implements OnItemClickListener {

	private MainActivity activity;
	private Fragment fragment;
	private DrawerLayout drawerLayout;
	private ListView menu;

	public MenuClickListener(DrawerLayout drawer, ListView menu) {
		this.drawerLayout = drawer;
		this.menu = menu;
		activity = MainActivity.getActivity();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

		MenuService.setSelectItem(i);
		switch (i) {
		case 0:
			fragment = new EventsFragment();
			break;
		case 1:
			fragment = new ShoppingFragment();
			break;
		case 2:
			fragment = new NotesFragment();
			break;
		case 3:

			break;
		default:
			break;

		}
		activity.setTitleActionBar(activity.getResources().getStringArray(
				R.array.title_items)[i]);
		/**
		 * Close menu after click item
		 */
		FragmentManager fragmentManager = activity.getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.main, fragment)
				.commit();
		drawerLayout.closeDrawer(menu);

	}
}
