package com.esec.dao;

import java.sql.SQLException;

import com.esec.model.Shopping;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class ShoppingDAO extends BaseDaoImpl<Shopping, Integer> {

	protected ShoppingDAO(ConnectionSource connectionSource,
			Class<Shopping> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

}
