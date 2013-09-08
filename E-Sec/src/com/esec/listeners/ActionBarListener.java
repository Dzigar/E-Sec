package com.esec.listeners;

import java.sql.SQLException;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.service.DialogScreenService;

public class ActionBarListener implements OnMenuItemClickListener {

	public ActionBarListener() {

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_add:
			try {
				DialogScreenService dialogScreenService = new DialogScreenService(
						MainActivity.getActivity());
				dialogScreenService.show();
			} catch (SQLException e) {
				Log.i(null, e.getLocalizedMessage());
				MainActivity.getActivity().startActivity(
						new Intent("com.imanager.SETTINGS"));
			}
			break;
		}
		return true;
	}
}
