package com.esec.service;

import java.util.ArrayList;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.model.ItemMenu;

public class MenuService {

	private static int counter = 0;

	/**
	 * create items of main menu
	 * 
	 * @return
	 */
	public static ArrayList<ItemMenu> createMainMenu() {
		ArrayList<ItemMenu> itemsMenu = new ArrayList<ItemMenu>();
		itemsMenu.add(new ItemMenu(MainActivity.getActivity().getString(
				R.string.todo_list), R.drawable.ic_list, 0));
		itemsMenu.add(new ItemMenu(MainActivity.getActivity().getString(
				R.string.shopping), R.drawable.ic_shopping, 0));
		itemsMenu.add(new ItemMenu(MainActivity.getActivity().getString(
				R.string.notes), R.drawable.ic_note, 0));
		itemsMenu.add(new ItemMenu(MainActivity.getActivity().getString(
				R.string.costs), R.drawable.ic_wallet, 0));
		return itemsMenu;
	}

	public static void setCounter(int i) {
		counter = i;
	}

	public static int getCounter() {
		return counter;
	}

}
