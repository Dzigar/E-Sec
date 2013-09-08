package com.esec.listeners;

import java.sql.SQLException;

import android.view.animation.Animation;

import com.esec.controller.ControllerListEvent;
import com.esec.ui.UIListTodo;

public class DeleteAnimationListener implements Animation.AnimationListener {

	private ControllerListEvent controllerListTodo;
	private int position;

	public DeleteAnimationListener(int position) throws SQLException {
		this.position = position;
		controllerListTodo = ControllerListEvent.getTodoController(null);
	}

	/**
	 * delete item from list
	 * 
	 * @throws SQLException
	 */
	private void removeTodo(int index) throws SQLException {
		controllerListTodo.deleteTodo(index);
		UIListTodo.getUiListTodo(null, null).createListEvent();

	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		try {
			removeTodo(position);
		} catch (SQLException e) {
			e.getLocalizedMessage();
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
