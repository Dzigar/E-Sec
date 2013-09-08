package com.esec.listeners;

import android.app.LauncherActivity.ListItem;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.ui.UIListTodo;

public class MenuListener extends ListItem implements OnItemClickListener {

	private UIListTodo uiListTodo;
	private DrawerLayout drawerLayout;
	private ListView menu;

	public MenuListener(Context context, DrawerLayout drawer,
			ListView listEvent, ListView menu) {
		this.drawerLayout = drawer;
		this.menu = menu;
		uiListTodo = UIListTodo.getUiListTodo(context, listEvent);
		uiListTodo.createListEvent();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

		switch (i) {
		case 0:
			uiListTodo.createListEvent();
			MainActivity.getActivity().getTitleAction()
					.setTitle(R.string.todo_list);
			break;
		case 1:
			MainActivity.getActivity().getTitleAction()
					.setTitle(R.string.shopping);
			break;
		case 2:
			MainActivity.getActivity().getTitleAction()
					.setTitle(R.string.notes);
			break;
		case 3:
			MainActivity.getActivity().getTitleAction()
					.setTitle(R.string.costs);
			break;

		default:
			break;
		}

		/**
		 * Close menu after click item
		 */
		drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		});
		drawerLayout.closeDrawer(menu);

	}

}
