package com.esec.model;

public class ItemMenu {

	private String titleMenu;

	private int icon;

	public ItemMenu(String titleMenu, int icon) {
		setTitleMenu(titleMenu);
		setIcon(icon);
	}

	/**
	 * @return the titleMenu
	 */
	public String getTitleMenu() {
		return titleMenu;
	}

	/**
	 * @param titleMenu
	 *            the titleMenu to set
	 */
	public void setTitleMenu(String titleMenu) {
		this.titleMenu = titleMenu;
	}

	/**
	 * @return the icon
	 */
	public int getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(int icon) {
		this.icon = icon;
	}

}
