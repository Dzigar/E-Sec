package com.esec.service;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.model.ItemMenu;

public class MenuService {

	private static int selectItem = 0;

	/**
	 * create items of main menu
	 * 
	 * @return
	 */
	public static ItemMenu[] createMainMenu() {
		String[] items = MainActivity.getActivity().getResources()
				.getStringArray(R.array.title_items);
		ItemMenu[] itemsMenu = new ItemMenu[items.length];
		itemsMenu[0] = new ItemMenu(items[0], R.drawable.event, 0);
		itemsMenu[1] = new ItemMenu(items[1], R.drawable.shopping, 0);
		itemsMenu[2] = new ItemMenu(items[2], R.drawable.note, 0);
		itemsMenu[3] = new ItemMenu(items[3], R.drawable.new_shopping, 0);
		return itemsMenu;
	}

	public static void setSelectItem(int i) {
		selectItem = i;
	}

	public static int getSelectItem() {
		return selectItem;
	}

}
