package com.esec.listeners;

import java.sql.SQLException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.userinterface.dialog.AddEventDialog;
import com.esec.userinterface.dialog.AddShoppingDialog;

public class ActionBarListener implements OnMenuItemClickListener {

	MainActivity mainActivity;

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		mainActivity = MainActivity.getActivity();
		switch (item.getItemId()) {
		case R.id.add_new_list:
			final EditText titleList = new EditText(mainActivity);
			titleList.setHint(mainActivity.getString(R.string.list_title));
			new AlertDialog.Builder(mainActivity)
					.setTitle(mainActivity.getString(R.string.new_list))
					.setView(titleList)
					.setPositiveButton(
							mainActivity.getString(android.R.string.ok),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// add new shoping list
								}
							})
					.setNegativeButton(android.R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// nothing
								}
							}).show();
			break;
		case R.id.new_event:
			AddEventDialog dialogEventService;
			try {
				dialogEventService = new AddEventDialog(mainActivity);
				dialogEventService.show();
			} catch (SQLException e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
			break;
		// AddNoteDialog dialogNoteService = new AddNoteDialog(
		// MainActivity.getActivity());
		// dialogNoteService.show();
		case R.id.new_shopping:
			AddShoppingDialog dialogShoppingService = new AddShoppingDialog(
					MainActivity.getActivity());
			dialogShoppingService.show();
			break;
		default:
			break;
		}

		return true;
	}
}
