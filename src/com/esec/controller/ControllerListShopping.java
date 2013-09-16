package com.esec.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.util.Log;

import com.esec.connection.HelperFactory;
import com.esec.dao.ShoppingDAO;
import com.esec.model.Shopping;
import com.esec.service.ComparatorShoppingService;

public class ControllerListShopping {

	private static ControllerListShopping controllerListShopping;

	private ShoppingDAO shoppingDAO;

	private ControllerListShopping(Activity activity) throws SQLException {
		HelperFactory.setHelper(activity.getApplicationContext());
		shoppingDAO = HelperFactory.getHelper().getShoppingDAO();

	}

	public static ControllerListShopping getShoppingController(Activity activity)
			throws SQLException {
		if (controllerListShopping == null) {
			controllerListShopping = new ControllerListShopping(activity);
		}
		return controllerListShopping;
	}

	public void addShopping(String title, int number)
			throws java.sql.SQLException, ParseException {
		Shopping shopping = new Shopping(title, number);
		shoppingDAO.create(shopping);
	}

	public List<Shopping> getListShopping() throws SQLException {
		List<Shopping> listShopping = new ArrayList<Shopping>();
		try {
			Iterator<Shopping> iterator = shoppingDAO.getAllPurchases()
					.iterator();
			while (iterator.hasNext()) {
				listShopping.add(iterator.next());
			}
		} catch (Exception e) {
			Log.i(getClass().getName(), e.getLocalizedMessage());
		}
		Collections.sort(listShopping, new ComparatorShoppingService()); // Sort
																			// list
		return listShopping;
	}

	public void deleteShopping(int position) throws SQLException {
		List<Shopping> listShopping = shoppingDAO.getAllPurchases();
		Collections.sort(listShopping, new ComparatorShoppingService());
		shoppingDAO.delete(listShopping.get(position));
	}
}
