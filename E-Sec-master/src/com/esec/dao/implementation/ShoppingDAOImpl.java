package com.esec.dao.implementation;

import java.sql.SQLException;
import java.util.List;

import com.esec.activity.MainActivity;
import com.esec.activity.fragment.ShoppingFragment;
import com.esec.dao.interfaces.ShoppingDAO;
import com.esec.model.Shopping;
import com.esec.string.Strings;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ShoppingDAOImpl extends BaseDaoImpl<Shopping, Integer> implements
		ShoppingDAO {

	public ShoppingDAOImpl(ConnectionSource connectionSource,
			Class<Shopping> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	public List<Shopping> getAllPurchases() throws SQLException {
		return super.queryForAll();
	}

	@Override
	public Shopping queryForId(Integer id) throws SQLException {
		return super.queryForId(id);
	}

	@Override
	public int create(Shopping shopping) throws SQLException {
		int i = super.create(shopping);
		MainActivity.getActivity().updateFragment(new ShoppingFragment());
		return i;
	}

	@Override
	public int delete(Shopping shopping) throws SQLException {
		return super.delete(shopping);
	}

	@Override
	public int deleteById(int id) throws SQLException {
		int i = super.delete(getListShoppingByListTitle(
				ShoppingFragment.idSelectedList).get(id));
		MainActivity.getActivity().updateFragment(new ShoppingFragment());
		return i;
	}

	@Override
	public List<Shopping> getListShoppingByListTitle(int id)
			throws SQLException {
		return super.queryForEq(Strings.COLUMN_RELATION, id);
	}

	@Override
	public int deleteListShopping(List<Shopping> list) throws SQLException {
		return super.delete(list);
	}

}
