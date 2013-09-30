package com.esec.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.esec.activity.Event;
import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.model.Todo;
import com.esec.swipe.SwipeDetector;

public class EventClickListener implements OnItemClickListener {

	private BaseAdapter adapterEvent;

	public EventClickListener(BaseAdapter adapterEvent) {
		setAdapterEvent(adapterEvent);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

		if (SwipeDetector.getSwipeDetector().swipeDetected()) {
			SwipeDetector.getSwipeDetector().sendMessage(view,
					((Todo) adapterView.getItemAtPosition(i)).getId());
		} else {
			Intent intent = new Intent(MainActivity.getActivity(), Event.class);
			intent.putExtra(MainActivity.getActivity().getString(R.string.id),
					((Todo) adapterEvent.getItem(i)).getId());
			MainActivity.getActivity().startActivity(intent);
		}
		view.setBackgroundColor(MainActivity.getActivity().getResources()
				.getColor(R.color.backgroun_main_fragment));

	}

	/**
	 * @return the adapterEvent
	 */
	public BaseAdapter getAdapterEvent() {
		return adapterEvent;
	}

	/**
	 * @param adapterEvent
	 *            the adapterEvent to set
	 */
	public void setAdapterEvent(BaseAdapter adapterEvent) {
		this.adapterEvent = adapterEvent;
	}
}
