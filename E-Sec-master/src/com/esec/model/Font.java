package com.esec.model;

import com.esec.string.Strings;

import android.content.Context;
import android.graphics.Typeface;

public class Font {

	private static Font fonts;

	private Typeface fontMenu;
	private Typeface fontEventList;
	private Typeface fontEventDescription;

	private Font(Context context) {
		setFontMenu(Typeface.createFromAsset(context.getAssets(),
				Strings.PATHFONTMENU));
		setFontEventList(Typeface.createFromAsset(context.getAssets(),
				Strings.PATHFONTTODOTITLE));
		setFontEventDescription(Typeface.createFromAsset(context.getAssets(),
				Strings.PATHFONTDESCRIPTION));
	}

	public static Font getFonts(Context context) {
		if (fonts == null) {
			fonts = new Font(context);
		}
		return fonts;
	}

	/**
	 * @return the fontMenu
	 */
	public Typeface getFontMenu() {
		return fontMenu;
	}

	/**
	 * @param fontMenu
	 *            the fontMenu to set
	 */
	public void setFontMenu(Typeface fontMenu) {
		this.fontMenu = fontMenu;
	}

	/**
	 * @return the fontEventList
	 */
	public Typeface getFontEventList() {
		return fontEventList;
	}

	/**
	 * @param fontEventList
	 *            the fontEventList to set
	 */
	public void setFontEventList(Typeface fontEventList) {
		this.fontEventList = fontEventList;
	}

	/**
	 * @return the fontEventDescription
	 */
	public Typeface getFontEventDescription() {
		return fontEventDescription;
	}

	/**
	 * @param fontEventDescription
	 *            the fontEventDescription to set
	 */
	public void setFontEventDescription(Typeface fontEventDescription) {
		this.fontEventDescription = fontEventDescription;
	}
}
