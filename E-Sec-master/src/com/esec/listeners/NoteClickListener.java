package com.esec.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.esec.activity.MainActivity;
import com.esec.activity.NoteActivity;
import com.esec.model.Note;
import com.esec.swipe.SwipeDetector;

public class NoteClickListener implements OnItemClickListener{

	private BaseAdapter adapterNote;
	
	public NoteClickListener(BaseAdapter adapterNote) {
		setAdapterNote(adapterNote);
	}
	
	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		if (SwipeDetector.getSwipeDetector().swipeDetected()) 
			SwipeDetector.getSwipeDetector().sendMessage(view, i);
		else{
			Intent intent = new Intent(MainActivity.getActivity(),
					NoteActivity.class);
			intent.putExtra("note",((Note) adapterNote.getItem(i)).getId());
			MainActivity.getActivity().startActivity(intent);
		}
	}
	
	/**
	 * @return the adapterShopping
	 */
	public BaseAdapter getAdapterNote() {
		return adapterNote;
	}
	
	/**
	 * @param adapterEvent
	 *            the adapterShoppint to set
	 */
	public void setAdapterNote(BaseAdapter adapterNote) {
		this.adapterNote= adapterNote;
	}

}
