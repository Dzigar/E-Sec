package com.esec.listeners;

import java.sql.SQLException;

import android.view.animation.Animation;

import com.esec.activity.MainActivity;
import com.esec.activity.fragment.ListShoppingFragment;
import com.esec.controller.ControllerListEvent;
import com.esec.controller.ControllerListShopping;
import com.esec.service.MenuService;

public class DeleteAnimationListener implements Animation.AnimationListener {

	private ControllerListEvent controllerListTodo;
	private ControllerListShopping controllerListShopping;
	private int position;
	private int i;

	public DeleteAnimationListener(int position) throws SQLException {
		this.position = position;
		i = MenuService.getSelectItem();
		switch (i) {
		case 0:
			controllerListTodo = ControllerListEvent.getTodoController(null);
			break;
		case 1:
			controllerListShopping = ControllerListShopping
					.getShoppingController(null);
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
	private void remove(int index) throws SQLException {
		switch (i) {
		case 0:
			controllerListTodo.deleteTodo(index);
			// UIListTodo.getUiListTodo(null, null).createListEvent();
			break;
		case 1:
			controllerListShopping.deleteShopping(index);
			MainActivity.getActivity().updateFragment(
					new ListShoppingFragment());
			break;
		default:
			break;
		}
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		try {
			remove(position);
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
