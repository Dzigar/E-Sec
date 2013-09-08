package com.esec.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Todo {

	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_DESC = "description";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_PRIORITY = "priority";

	public Todo() {

	}

	@DatabaseField(generatedId = true)
	private int id;

	/**
	 * title event
	 */
	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = COLUMN_TITLE)
	private String title;

	/**
	 * description event
	 */
	@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = COLUMN_DESC)
	private String description;

	/**
	 * date event
	 */
	@DatabaseField(canBeNull = false, dataType = DataType.LONG_OBJ, columnName = COLUMN_DATE)
	private Long date;

	/**
	 * Status event
	 */
	@DatabaseField(canBeNull = true, dataType = DataType.ENUM_INTEGER, columnName = COLUMN_STATUS)
	private Status status;

	/**
	 * Priority event
	 */
	@DatabaseField(canBeNull = false, dataType = DataType.ENUM_STRING, columnName = COLUMN_PRIORITY)
	private Priorities priority;

	/**
	 * 
	 * @param title
	 * @param description
	 * @param date
	 */
	public Todo(String title, String description, Long date,
			Priorities priorities) {
		this.title = title;
		this.description = description;
		this.setDate(date);
		this.setPriority(priorities);
		this.setStatus(Status.STATUS_WAIT);
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
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
	 * @return the date
	 */
	public Long getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Long date) {
		this.date = date;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the priority
	 */
	public Priorities getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Priorities priority) {
		this.priority = priority;
	}

}
