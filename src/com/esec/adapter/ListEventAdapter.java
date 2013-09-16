package com.esec.adapter;

import java.util.List;

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
import com.esec.model.Todo;
import com.esec.service.DateService;
import com.esec.service.StatusService;

public class ListEventAdapter extends BaseAdapter {

	private LayoutInflater layoutInflaternflater;
	private List<?> listEvent;

	public ListEventAdapter(Context context, List<?> list) {
		this.listEvent = list;
		this.layoutInflaternflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listEvent.size();
	}

	@Override
	public Object getItem(int position) {
		return listEvent.get(position);
	}

	@Override
	public long getItemId(int position) {
		return ((Todo) listEvent.get(position)).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		Todo todo = getTodoItem(position);
		todo = StatusService.updateStatus(todo);

		if (view == null) {
			view = layoutInflaternflater.inflate(R.layout.todo_item, parent,
					false);
			TextView textView = (TextView) view.findViewById(R.id.event);
			textView.setText(todo.getTitle());
			textView.setTypeface(Font.getFonts(MainActivity.getActivity())
					.getFontEventList());

			((TextView) view.findViewById(R.id.dateEvent)).setText(DateService
					.convertDate(todo.getDate()));
			ImageView imageStatus = (ImageView) view
					.findViewById(R.id.imageStatus);
			setImagePriority(imageStatus, todo);
		}

		switch (todo.getStatus().getStatus()) {
		case -1:
			view.findViewById(R.id.ic_status).setBackgroundResource(
					R.drawable.ic_x);
			break;
		case 0:
			view.findViewById(R.id.ic_status).setBackgroundResource(
					R.drawable.ic_clock);
			break;
		case 1:
			view.findViewById(R.id.ic_status).setBackgroundResource(
					R.drawable.ic_ok);
			break;
		}

		return view;
	}

	private void setImagePriority(ImageView imageView, Todo todo) {
		switch (todo.getPriority().getId()) {
		case 0:
			imageView.setBackgroundResource(R.drawable.ic_a);
			break;
		case 1:
			imageView.setBackgroundResource(R.drawable.ic_b);
			break;
		case 2:
			imageView.setBackgroundResource(R.drawable.ic_c);
			break;
		case 3:
			imageView.setBackgroundResource(R.drawable.ic_d);
			break;

		default:
			break;
		}
	}

	/**
	 * @param position
	 * @return
	 */
	private Todo getTodoItem(int position) {
		return (Todo) getItem(position);
	}

}
