package com.esec.dao.implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

import com.esec.activity.MainActivity;
import com.esec.activity.fragment.ShoppingFragment;
import com.esec.comparator.ComparatorShoppingService;
import com.esec.dao.interfaces.ShoppingDAO;
import com.esec.model.Shopping;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ShoppingDAOImpl extends BaseDaoImpl<Shopping, Integer> implements
		ShoppingDAO {

	public ShoppingDAOImpl(ConnectionSource connectionSource,
			Class<Shopping> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	public List<Shopping> getAllPurchases() throws SQLException {
		List<Shopping> listShopping = new ArrayList<Shopping>();
		try {
			Iterator<Shopping> iterator = super.queryForAll().iterator();
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

	@Override
	public Shopping queryForId(Integer id) throws SQLException {
		return super.queryForId(id);
	}

	@Override
	public int create(Shopping shopping) throws SQLException {
		return super.create(shopping);
	}

	@Override
	public int delete(Shopping shopping) throws SQLException {
		return super.delete(shopping);
	}

	@Override
	public void deleteById(int id) throws SQLException {
		List<Shopping> listShopping = getAllPurchases();
		Collections.sort(listShopping, new ComparatorShoppingService());
		delete(listShopping.get(id));
		MainActivity.getActivity().updateFragment(new ShoppingFragment());
	}

}
