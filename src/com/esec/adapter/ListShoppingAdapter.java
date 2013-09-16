package com.esec.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.model.Font;
import com.esec.model.Shopping;

public class ListShoppingAdapter extends BaseAdapter {

	private LayoutInflater layoutInflaternflater;
	private List<Shopping> listShopping;

	public ListShoppingAdapter(Context context, List<Shopping> list) {
		this.listShopping = list;
		this.layoutInflaternflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listShopping.size();
	}

	@Override
	public Object getItem(int position) {
		return listShopping.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listShopping.get(position).getId();
	}

	/**
	 * @param position
	 * @return
	 */
	private Shopping getShoppingItem(int position) {
		return (Shopping) getItem(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		Shopping shopping = getShoppingItem(position);

		if (view == null) {
			view = layoutInflaternflater.inflate(R.layout.shopping_item,
					parent, false);
			TextView textView = (TextView) view.findViewById(R.id.Shopping);
			textView.setText(shopping.getTitle());
			textView.setTypeface(Font.getFonts(MainActivity.getActivity())
					.getFontEventList());

			((TextView) view.findViewById(R.id.Number)).setText(shopping
					.getAmount() + "");
		}
		return view;
	}

}
