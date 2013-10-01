package com.esec.service;

import android.util.DisplayMetrics;

import com.esec.activity.MainActivity;

public class MetricsService {

	public static int getWidthWindow() {
		DisplayMetrics metrics = new DisplayMetrics();
		MainActivity.getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		return metrics.widthPixels;
	}
}
