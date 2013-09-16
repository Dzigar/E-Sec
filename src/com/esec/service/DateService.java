package com.esec.service;

public class DateService {

	private static String dateText;

	public static String convertDate(long l) {

		if (java.text.DateFormat.getDateInstance()
				.format(System.currentTimeMillis())
				.equals(java.text.DateFormat.getDateInstance().format(l))) {
			dateText = java.text.DateFormat.getTimeInstance(
					java.text.DateFormat.DATE_FIELD).format(l);

		} else {
			dateText = java.text.DateFormat.getDateInstance().format(l)
					.substring(0);
		}

		return dateText;
	}
}
