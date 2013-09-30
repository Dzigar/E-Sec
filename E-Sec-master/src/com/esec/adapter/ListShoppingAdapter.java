package com.esec.adapter;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.connection.HelperFactory;
import com.esec.model.Font;
import com.esec.model.Shopping;

public class ListShoppingAdapter extends BaseAdapter {

	private LayoutInflater layoutInflaternflater;
	private List<Shopping> listShopping;

	public ListShoppingAdapter(Context context, String titleList) {
		HelperFactory.setHelper(MainActivity.getActivity());
		try {
			this.listShopping = HelperFactory
					.getHelper()
					.getShoppingDAO()
					.getListShoppingByListTitle(
							HelperFactory.getHelper().getShoppingListDAO()
									.getListByTitle(titleList).getId());
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
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
		}
		return view;
	}
}
