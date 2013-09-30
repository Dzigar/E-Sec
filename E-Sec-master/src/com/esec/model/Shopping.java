package com.esec.model;

import com.esec.string.Strings;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Shopping {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = Strings.COLUMN_TITLE)
	private String title;

	@DatabaseField(canBeNull = true, dataType = DataType.INTEGER, columnName = Strings.COLUMN_RELATION)
	private int idShoppingList;

	@DatabaseField(canBeNull = true, dataType = DataType.BOOLEAN, columnName = Strings.COLUMN_STATUS)
	private boolean status;

	public Shopping(String title, int listId) {
		this.title = title;
		this.idShoppingList = listId;
		this.status = false;
	}

	public Shopping() {

	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the idShoppingList
	 */
	public int getIdShoppingList() {
		return idShoppingList;
	}

	/**
	 * @param idShoppingList
	 *            the idShoppingList to set
	 */
	public void setIdShoppingList(int idShoppingList) {
		this.idShoppingList = idShoppingList;
	}
}
