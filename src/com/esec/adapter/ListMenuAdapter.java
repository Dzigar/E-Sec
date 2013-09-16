package com.esec.adapter;

import java.sql.SQLException;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.controller.ControllerListEvent;
import com.esec.controller.ControllerListShopping;
import com.esec.model.Font;
import com.esec.model.ItemMenu;

public class ListMenuAdapter extends BaseAdapter {
	private LayoutInflater layoutInflaternflater;
	private ItemMenu[] titleItemsMenu;
	private TextView menuItem;
	private TextView amount;

	public ListMenuAdapter(ItemMenu[] items) {
		this.titleItemsMenu = items;
		this.layoutInflaternflater = (LayoutInflater) MainActivity
				.getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return titleItemsMenu.length;
	}

	@Override
	public Object getItem(int position) {
		return titleItemsMenu[position];
	}

	/**
	 * id menu section
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = layoutInflaternflater.inflate(R.layout.item_menu, parent,
					false);
		}

		ItemMenu itemMenu = getItemMenu(position);

		menuItem = ((TextView) view.findViewById(R.id.textItem));
		menuItem.setText(itemMenu.getTitleMenu());
		menuItem.setTypeface(Font.getFonts(MainActivity.getActivity())
				.getFontMenu());
		((ImageView) view.findViewById(R.id.iconItem))
				.setImageResource(itemMenu.getIcon());

		try {
			if (getNumber(position) != 0) {
				amount = ((TextView) view.findViewById(R.id.amount));
				amount.setText(Integer.toString(getNumber(position)));
				amount.setTypeface(Font.getFonts(MainActivity.getActivity())
						.getFontMenu());
			}
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		return view;
	}

	/**
	 * 
	 * @param position
	 * @return
	 */
	private ItemMenu getItemMenu(int position) {
		return (ItemMenu) getItem(position);
	}

	private int getNumber(int i) throws SQLException {
		switch (i) {
		case 0:
			return ControllerListEvent
					.getTodoController(MainActivity.getActivity())
					.getListTodo().size();
		case 1:
			return ControllerListShopping
					.getShoppingController(MainActivity.getActivity())
					.getListShopping().size();
		default:
			return 0;
		}
	}
}