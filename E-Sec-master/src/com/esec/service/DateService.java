package com.esec.service;

import java.util.Calendar;

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

	public static long getTodayDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static long getTomorrowDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static long getDifferenceTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static long getInterval() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 366);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static long getDifferenceForToday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -366);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

}
