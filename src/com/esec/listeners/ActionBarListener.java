package com.esec.listeners;

import java.sql.SQLException;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.service.MenuService;
import com.esec.userinterface.dialog.AddEventDialog;
import com.esec.userinterface.dialog.AddShoppingDialog;

public class ActionBarListener implements OnMenuItemClickListener {

	@Override
	public boolean onMenuItemClick(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_add:
			int i = MenuService.getSelectItem();
			switch (i) {
			case 0:
				try {
					AddEventDialog dialogScreenService = new AddEventDialog(
							MainActivity.getActivity());
					dialogScreenService.show();
				} catch (SQLException e) {
					Log.i(null, e.getLocalizedMessage());
					MainActivity.getActivity().startActivity(
							new Intent("com.imanager.SETTINGS"));
				}
				break;
			case 1:
				AddShoppingDialog dialogScreenService = new AddShoppingDialog(
						MainActivity.getActivity());
				dialogScreenService.show();

				break;
			case 2:
				// try{
				// AddNotesgDialog dialogScreenService = new AddNotesDialog(
				// MainActivity.getActivity());
				// dialogScreenService.show();
				// }catch(SQLException e){
				// Log.i(null, e.getLocalizedMessage());
				// MainActivity.getActivity().startActivity(
				// new Intent("com.imanager.SETTINGS"));
				// }
				break;
			case 3:
				// try{
				// AddCostsDialog dialogScreenService = new AddCostsDialog(
				// MainActivity.getActivity());
				// dialogScreenService.show();
				// }catch(SQLException e){
				// Log.i(null, e.getLocalizedMessage());
				// MainActivity.getActivity().startActivity(
				// new Intent("com.imanager.SETTINGS"));
				// }
				break;
			default:
				break;
			}
		}
		return true;
	}
}
