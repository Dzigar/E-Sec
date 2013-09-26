package com.esec.service;


import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.model.Priorities;

public class PriorityService {

	public static int getColorEvent(Priorities priorities) {
		int color = R.color.color_event_iu;
		switch (priorities) {
		case IMPORTANT_URGENT:
			color = MainActivity.getActivity().getResources()
					.getColor(R.color.color_event_iu);
			break;
		case IMPORTANT_NOURGENT:
			color = MainActivity.getActivity().getResources()
					.getColor(R.color.color_event_in);
			break;
		case UNIMPORTANT_URGENT:
			color = MainActivity.getActivity().getResources()
					.getColor(R.color.color_event_nu);
			break;
		case UNIMPORTANT_NOURGENT:
			color = MainActivity.getActivity().getResources()
					.getColor(R.color.color_event_nn);
			break;
		}
		return color;
	}
}
