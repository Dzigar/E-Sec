package com.esec.activity.fragment;

import java.sql.SQLException;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.connection.HelperFactory;
import com.esec.model.Font;
import com.esec.model.Note;
import com.esec.service.MetricsService;

public class NotesFragment extends Fragment implements OnTabChangeListener {

	private TabHost tabHost;
	private TabHost.TabSpec titleNoteSpec; //
	private List<Note> listNote;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.events_fragment, container,
				false);

		tabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);

		tabHost.setup();
		tabHost.setOnTabChangedListener(this);

		HelperFactory.setHelper(getActivity());
		// check whether created a shopping list
		try {
			listNote = HelperFactory.getHelper().getNoteDAO().getAllNotes();
		} catch (SQLException e1) {
			Log.i(getClass().getName(), e1.getLocalizedMessage());
		}

		for (int i = 0; i < listNote.size(); i++) {
			addTab(titleNoteSpec, listNote.get(i).getTitle(), listNote.get(i)
					.getTitle(), listNote.get(i).getId());
			tabHost.getTabWidget().getChildAt(i).getLayoutParams().width = MetricsService
					.getWidthWindow() / getChildCounts();
		}
		return rootView;

	}

	/**
	 * 
	 * @param tabSpec
	 * @param tag
	 * @param indicator
	 * @param contentRes
	 */
	private void addTab(TabHost.TabSpec tabSpec, String tag, String indicator,
			final int id) {

		// create tab and specify the tag
		tabSpec = tabHost.newTabSpec(tag);
		// title tag
		tabSpec.setIndicator(createTabView(tabHost.getContext(), indicator));
		// create tab content
		tabSpec.setContent(new TabContentFactory() {
			@Override
			public View createTabContent(String tag) {
				return getNoteInfo(id);
			}
		});
		tabHost.addTab(tabSpec);
	}

	/**
	 * Create custom tab for tab widget
	 * 
	 * @param context
	 * @param text
	 * @return
	 */
	private View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.drawable.tab_bg,
				null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		tv.setTypeface(Font.getFonts(context).getFontMenu());
		tv.setTextColor(MainActivity.getActivity().getResources()
				.getColor(R.color.background_actionbar));
		return view;
	}

	/**
	 * @return
	 */
	private View getNoteInfo(int id) {
		RelativeLayout layout = (RelativeLayout) LayoutInflater.from(
				getActivity()).inflate(R.layout.note_item, null);
		TextView title = (TextView) layout.findViewById(R.id.titleNote);
		EditText infoNote = (EditText) layout.findViewById(R.id.infoNote);
		try {
			Note note = HelperFactory.getHelper().getNoteDAO().getNoteById(id);
			title.setText(note.getTitle());
			infoNote.setText(note.getDesc());
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		return layout;
	}

	private int getChildCounts() {
		if (listNote.size() > 3) {
			return 3;
		} else
			return listNote.size();
	}

	@Override
	public void onTabChanged(String tabId) {

	}

}
