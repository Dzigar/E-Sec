package com.esec.dao.implementation;

import java.sql.SQLException;
import java.util.List;

import com.esec.activity.MainActivity;
import com.esec.activity.fragment.ShoppingFragment;
import com.esec.connection.HelperFactory;
import com.esec.dao.interfaces.ShoppingListDAO;
import com.esec.model.ShoppingList;
import com.esec.string.Strings;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ListDAOImpl extends BaseDaoImpl<ShoppingList, Integer> implements
		ShoppingListDAO {

	public ListDAOImpl(ConnectionSource connectionSource,
			Class<ShoppingList> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	@Override
	public void createNewList(ShoppingList list) throws SQLException {
		super.create(list);
		MainActivity.getActivity().updateFragment(new ShoppingFragment());
	}

	@Override
	public void deleteListById(int id) throws SQLException {
		HelperFactory
				.getHelper()
				.getShoppingDAO()
				.deleteListShopping(
						HelperFactory.getHelper().getShoppingDAO()
								.getListShoppingByListTitle(id));
		super.deleteById(id);
	}

	@Override
	public List<ShoppingList> getLists() throws SQLException {
		return super.queryForAll();
	}

	@Override
	public ShoppingList getListByTitle(String title) throws SQLException {
		return super.queryForEq(Strings.COLUMN_TITLE, title).get(0);
	}

}
