package com.esec.listeners;

import java.sql.SQLException;

import android.util.Log;
import android.view.animation.Animation;

import com.esec.connection.HelperFactory;
import com.esec.controller.ControllerListNote;
import com.esec.service.MenuService;

public class DeleteAnimationListener implements Animation.AnimationListener {

	private ControllerListNote controllerListNote;
	private int position;

	public DeleteAnimationListener(int position) throws SQLException {
		this.position = position;
		switch (MenuService.getSelectItem()) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			controllerListNote = ControllerListNote.getNoteController(null);
			break;
		default:
			break;
		}
	}

	/**
	 * delete item from list
	 * 
	 * @throws SQLException
	 */
	private void remove(int id) throws SQLException {
		switch (MenuService.getSelectItem()) {
		case 0:
			HelperFactory.getHelper().getTodoDAO().removeById(id);
			break;
		case 1:
			HelperFactory.getHelper().getShoppingDAO().deleteById(id);
			break;
		case 2:
			controllerListNote.deleteNote(id);
			break;
		}
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		try {
			remove(position);
		} catch (SQLException e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}
