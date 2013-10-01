package com.esec.activity.fragment;

import java.sql.SQLException;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.adapter.ListNoteAdapter;
import com.esec.listeners.NoteClickListener;
import com.esec.swipe.SwipeDetector;

public class NotesFragment extends Fragment {

	private ListNoteAdapter noteAdapter;
	private ListView listNote;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.events_fragment, container,
				false);
		try {
			noteAdapter = new ListNoteAdapter(MainActivity.getActivity());
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		listNote = new ListView(getActivity());
		listNote.setAdapter(noteAdapter);
		listNote.setOnTouchListener(SwipeDetector.getSwipeDetector());
		listNote.setOnItemClickListener(new NoteClickListener(noteAdapter));

		return rootView;

	}

}
