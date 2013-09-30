package com.esec.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.esec.swipe.SwipeDetector;

public class ShoppingClickListener implements OnItemClickListener {

	private BaseAdapter adapterShopping;

	public ShoppingClickListener(BaseAdapter adapterShopping) {
		setAdapterShopping(adapterShopping);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		if (SwipeDetector.getSwipeDetector().swipeDetected()) {
			SwipeDetector.getSwipeDetector().sendMessage(view, i);
		}
	}

	/**
	 * @return the adapterShopping
	 */
	public BaseAdapter getAdapterShopping() {
		return adapterShopping;
	}

	/**
	 * @param adapterEvent
	 *            the adapterShoppint to set
	 */
	public void setAdapterShopping(BaseAdapter adapterShopping) {
		this.adapterShopping = adapterShopping;
	}

}
