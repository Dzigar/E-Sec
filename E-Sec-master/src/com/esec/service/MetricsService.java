package com.esec.service;

import com.esec.activity.MainActivity;

import android.util.DisplayMetrics;

public class MetricsService {

	public static int getWidthWindow() {
		DisplayMetrics metrics = new DisplayMetrics();
		MainActivity.getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		return metrics.widthPixels;
	}
}
