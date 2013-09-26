package com.esec.model;

import com.esec.activity.MainActivity;
import com.esec.activity.R;
import com.esec.service.notification.NotificationService;

public enum Priorities {
	IMPORTANT_URGENT(0, R.string.important_urgent), IMPORTANT_NOURGENT(1,
			R.string.important_non_urgent), UNIMPORTANT_URGENT(2,
			R.string.unimportant_urgent), UNIMPORTANT_NOURGENT(3,
			R.string.unimportant_non_urgent);

	private String priority;
	private int id;

	private Priorities(int id, int res) {
		try {
			priority = NotificationService.getContext().getString(res);
		} catch (Exception e) {
			priority = MainActivity.getActivity().getString(res);
		}
		this.id = id;
	}

	public static String[] getValuesTitleList() {

		String[] prioritiesTitleList = new String[Priorities.values().length];
		prioritiesTitleList[0] = Priorities.IMPORTANT_URGENT.getPriorityText();
		prioritiesTitleList[1] = Priorities.IMPORTANT_NOURGENT
				.getPriorityText();
		prioritiesTitleList[2] = Priorities.UNIMPORTANT_URGENT
				.getPriorityText();
		prioritiesTitleList[3] = Priorities.UNIMPORTANT_NOURGENT
				.getPriorityText();
		return prioritiesTitleList;
	}

	public static Priorities getPriorityById(int id) {

		switch (id) {
		case 0:
			return Priorities.IMPORTANT_URGENT;
		case 1:
			return Priorities.IMPORTANT_NOURGENT;
		case 2:
			return Priorities.UNIMPORTANT_URGENT;
		case 3:
			return Priorities.UNIMPORTANT_NOURGENT;
		default:
			return null;
		}
	}

	/**
	 * @return the priority
	 */
	public String getPriorityText() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
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
}
