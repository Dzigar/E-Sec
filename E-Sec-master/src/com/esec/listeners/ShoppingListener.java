package com.esec.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.esec.swipe.SwipeDetector;

public class ShoppingListener  implements OnItemClickListener  {

	private BaseAdapter adapterShopping;
	private int itemMenuSelected;
	
	public ShoppingListener(BaseAdapter adapterShopping, int i) {
		setAdapterShopping(adapterShopping);
		this.itemMenuSelected = i;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		itemMenuSelected=1;//temporarily
		switch (itemMenuSelected) {
		case 0:
			break;
		case 1:
			if (SwipeDetector.getSwipeDetector().swipeDetected()) {
				SwipeDetector.getSwipeDetector().sendMessage(view, i);
			} else {
				/*Intent intent = new Intent(MainActivity.getActivity(),
						Shopping.class);
				intent.putExtra(
						MainActivity.getActivity().getString(R.string.id),
						( (Shopping) adapterShopping.getItem(i)).getId());
				MainActivity.getActivity().startActivity(intent);*/
			}
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
		this.adapterShopping= adapterShopping;
	}

}
