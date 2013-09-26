package com.esec.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.esec.model.Shopping;

public interface ShoppingDAO {

	public List<Shopping> getAllPurchases() throws SQLException;

	public Shopping queryForId(Integer id) throws SQLException;

	public int create(Shopping shopping) throws SQLException;

	public int delete(Shopping shopping) throws SQLException;

	public void deleteById(int id) throws SQLException;
}
