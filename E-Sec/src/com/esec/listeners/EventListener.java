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

public class EventListener implements OnItemClickListener {

	private BaseAdapter adapterEvent;
	private int itemMenuSelected;

	public EventListener(BaseAdapter adapterEvent, int i) {
		setAdapterEvent(adapterEvent);
		this.itemMenuSelected = i;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

		switch (itemMenuSelected) {
		case 0:
			if (SwipeDetector.getSwipeDetector().swipeDetected()) {
				SwipeDetector.getSwipeDetector().sendMessage(view, i);
			} else {
				Intent intent = new Intent(MainActivity.getActivity(),
						Event.class);
				intent.putExtra(
						MainActivity.getActivity().getString(R.string.id),
						((Todo) adapterEvent.getItem(i)).getId());
				MainActivity.getActivity().startActivity(intent);
			}
			break;

		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		default:
			break;
		}
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
