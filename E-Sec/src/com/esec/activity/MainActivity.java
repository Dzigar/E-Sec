package com.esec.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.esec.adapter.ListMenuAdapter;
import com.esec.listeners.ActionBarListener;
import com.esec.listeners.MenuListener;
import com.esec.model.ItemMenu;

public class MainActivity extends FragmentActivity {

	private static MainActivity activity;

	private MenuItem buttonAdd; // button add in ActionBar
	private MenuItem buttonSettings; // button settings in ActionBar
	private MenuItem titleAction;

	private ListView mainList; // Event list
	private ListView menu; // List of menu items

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		activity = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

		setTitleAction((MenuItem) findViewById(R.id.action_settings));

		mainList = (ListView) findViewById(R.id.list_main);
		/**
		 * init. of menu
		 */
		menu = (ListView) findViewById(R.id.menu);
		menu.setAdapter(new ListMenuAdapter(this, createMainMenu()));
		menu.setOnItemClickListener(new MenuListener(this, drawer, mainList,
				menu));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.context_menu, menu);
		ActionBarListener listenerForActionBar = new ActionBarListener();
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.color.backgroun_actionbar));

		buttonAdd = menu.findItem(R.id.action_add);
		buttonAdd.setOnMenuItemClickListener(listenerForActionBar);

		buttonSettings = menu.findItem(R.id.action_settings);
		buttonSettings.setOnMenuItemClickListener(listenerForActionBar);

		titleAction = menu.findItem(R.id.title);
		return true;
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setTitle(getString(R.string.exit))
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								System.exit(0);
							}

						}).setNegativeButton(getString(R.string.cancel), null)
				.create().show();
	}

	/**
	 * @return the iManager
	 */
	public static MainActivity getActivity() {
		return activity;
	}

	/**
	 * create items of main menu
	 * 
	 * @return
	 */
	private ArrayList<ItemMenu> createMainMenu() {
		ArrayList<ItemMenu> itemsMenu = new ArrayList<ItemMenu>();

		itemsMenu.add(new ItemMenu(getString(R.string.todo_list),
				R.drawable.ic_list));
		itemsMenu.add(new ItemMenu(getString(R.string.shopping),
				R.drawable.ic_shopping));
		itemsMenu.add(new ItemMenu(getString(R.string.notes),
				R.drawable.ic_note));
		itemsMenu.add(new ItemMenu(getString(R.string.costs),
				R.drawable.ic_wallet));
		return itemsMenu;
	}

	/**
	 * @return the titleAction
	 */
	public MenuItem getTitleAction() {
		return titleAction;
	}

	/**
	 * @param titleAction
	 *            the titleAction to set
	 */
	public void setTitleAction(MenuItem titleAction) {
		this.titleAction = titleAction;
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

	}
}
