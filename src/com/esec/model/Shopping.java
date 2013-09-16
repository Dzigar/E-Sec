package com.esec.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Shopping {

	public Shopping() {
	}

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = "title")
	private String title;

	@DatabaseField(canBeNull = true, dataType = DataType.INTEGER, columnName = "amount")
	private int amount;

	@DatabaseField(canBeNull = false, dataType = DataType.BOOLEAN, columnName = "status")
	private boolean status;

	public Shopping(String title, int amount) {
		this.setTitle(title);
		this.setAmount(amount);
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
	 * @return the number
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
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
}
