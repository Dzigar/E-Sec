package com.esec.dao;

import java.sql.SQLException;
import java.util.List;

import com.esec.model.Shopping;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ShoppingDAO extends BaseDaoImpl<Shopping, Integer> {

	public ShoppingDAO(ConnectionSource connectionSource,
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
		return super.create(shopping);
	}

	@Override
	public int delete(Shopping shopping) throws SQLException {
		return super.delete(shopping);
	}
	
	
}
