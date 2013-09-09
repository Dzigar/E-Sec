package com.esec.model;

public class ItemMenu {

	private String titleMenu;
	private int icon;
	private String amount;

	public ItemMenu(String titleMenu, int icon, int amount) {
		setTitleMenu(titleMenu);
		setIcon(icon);
		setAmount(amount);
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

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = Integer.toString(amount);
	}

}
