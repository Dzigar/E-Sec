package com.esec.connection;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class HelperFactory {

	private static DatabaseHelper databaseHelper;

	public static DatabaseHelper getHelper() {
		return databaseHelper;
	}

	public static void setHelper(Context context) {
		try {
			databaseHelper = OpenHelperManager.getHelper(context,
					DatabaseHelper.class);
		} catch (Exception e) {
			Log.i(getHelper().toString(), e.getLocalizedMessage());
		}
	}

	public static void releaseHelper() {
		OpenHelperManager.releaseHelper();
		databaseHelper = null;
	}
}