package com.esec.service.notification;

import java.util.Comparator;

import android.app.Notification;

public class DateComparator implements Comparator<Notification> {

	@Override
	public int compare(Notification lhs, Notification rhs) {
		return (int) (lhs.when - rhs.when);

	}
}
