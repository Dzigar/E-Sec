package com.esec.model;

import com.esec.string.Strings;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ShoppingList {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = Strings.COLUMN_TITLE)
	private String title;

	public ShoppingList(String title) {
		this.title = title;
	}

	public ShoppingList() {
		// TODO Auto-generated constructor stub
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

}
