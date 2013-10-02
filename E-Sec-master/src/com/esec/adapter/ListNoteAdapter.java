package com.esec.adapter;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.connection.HelperFactory;
import com.esec.model.Font;
import com.esec.model.Note;

public class ListNoteAdapter extends BaseAdapter {

	private LayoutInflater layoutInflaternflater;
	private List<Note> listNote;

	public ListNoteAdapter(Context context) throws SQLException {
		this.listNote = HelperFactory.getHelper().getNoteDAO().getAllNotes();
		this.layoutInflaternflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listNote.size();
	}

	@Override
	public Object getItem(int position) {
		return listNote.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listNote.get(position).getId();
	}

	/**
	 * @param position
	 * @return
	 */
	private Note getNoteItem(int position) {
		return (Note) getItem(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		Note note = getNoteItem(position);

		System.out.println(note.getDesc());

//		if (view == null) {
//			view = layoutInflaternflater.inflate(R.layout.note_item, parent,
//					false);
//			TextView textView = (TextView) view.findViewById(R.id.Note);
//			textView.setText(note.getTitle());
//			textView.setTypeface(Font.getFonts(MainActivity.getActivity())
//					.getFontEventList());
//
//			((TextView) view.findViewById(R.id.Date)).setText(note.getDate()
//					+ "");
//		}
		return view;
	}
}
