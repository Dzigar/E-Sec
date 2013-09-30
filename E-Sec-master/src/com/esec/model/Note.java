package com.esec.model;

import com.esec.string.Strings;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public class Note {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = Strings.COLUMN_TITLE)
	private String title;

	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = Strings.COLUMN_DESC)
	private String description;

	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = Strings.COLUMN_DATE)
	private String date;

	public Note(String title, String description, String date) {
		this.title = title;
		this.description = description;
		this.date = date;
	}

	public Note() {
		// TODO Auto-generated constructor stub
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
	 * @return the description
	 */
	public String getDesc() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
