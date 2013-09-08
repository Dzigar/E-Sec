package com.esec.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.model.Font;
import com.esec.model.ItemMenu;

public class ListMenuAdapter extends BaseAdapter {
	private LayoutInflater layoutInflaternflater;
	private ArrayList<ItemMenu> mainMenu;
	private TextView menuItem;

	public ListMenuAdapter(Context context, ArrayList<ItemMenu> items) {
		this.mainMenu = items;
		this.layoutInflaternflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * кол-во элементов
	 */
	@Override
	public int getCount() {
		return mainMenu.size();
	}

	/**
	 * элемент меню по позиции(non-Javadoc)
	 * 
	 */
	@Override
	public Object getItem(int position) {
		return mainMenu.get(position);
	}

	/**
	 * id menu section
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * –аздел главного меню
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = layoutInflaternflater.inflate(R.layout.item_menu, parent,
					false);
		}

		ItemMenu itemMenu = getItemMenu(position);

		/**
		 * заполн€ем View иконкой и названием раздела
		 */
		menuItem = ((TextView) view.findViewById(R.id.textItem));
		menuItem.setText(itemMenu.getTitleMenu());
		menuItem.setTypeface(Font.getFonts(MainActivity.getActivity())
				.getFontMenu());
		((ImageView) view.findViewById(R.id.iconItem))
				.setImageResource(itemMenu.getIcon());
		return view;
	}

	/**
	 * раздел по позиции
	 * 
	 * @param position
	 * @return
	 */
	private ItemMenu getItemMenu(int position) {
		return (ItemMenu) getItem(position);
	}

}