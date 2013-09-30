package com.esec.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.esec.model.ShoppingList;

public interface ShoppingListDAO {

	public void createNewList(ShoppingList list) throws SQLException;

	public void deleteListById(int id) throws SQLException;

	public List<ShoppingList> getLists() throws SQLException;

	public ShoppingList getListByTitle(String title) throws SQLException;
}
