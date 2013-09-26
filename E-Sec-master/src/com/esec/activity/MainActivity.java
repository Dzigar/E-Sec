package com.esec.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.esec.activity.fragment.EventsFragment;
import com.esec.adapter.ListMenuAdapter;
import com.esec.connection.HelperFactory;
import com.esec.listeners.ActionBarListener;
import com.esec.listeners.MenuClickListener;
import com.esec.model.ItemMenu;
import com.esec.service.MenuService;

public class MainActivity extends FragmentActivity {

	private static MainActivity activity;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	private MenuItem addNewEvent;
	private MenuItem addNewList;
	private MenuItem addNewShopping;

	private ListView menu; // list of menu items
	private ItemMenu[] titleItems; // titles of items menu
	private CharSequence title; // title ActionBar

	@Override
	public void onCreate(Bundle savedInstanceState) {
		activity = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		titleItems = MenuService.createMainMenu();
		menu = (ListView) findViewById(R.id.menu);
		menu.setAdapter(new ListMenuAdapter(titleItems));
		menu.setOnItemClickListener(new MenuClickListener(drawerLayout, menu));

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		// getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		drawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.app_name, /* "open drawer" description for accessibility */
		R.string.menu /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				if (title != null) {
					getActionBar().setTitle(getTitleActionBar());
				} else
					getActionBar().setTitle(R.string.app_name);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(R.string.menu);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		drawerLayout.setDrawerListener(mDrawerToggle);

		updateFragment(new EventsFragment());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBarListener listenerForActionBar = new ActionBarListener();
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.color.background_actionbar));
		addNewList = menu.findItem(R.id.add_new_list);
		addNewList.setOnMenuItemClickListener(listenerForActionBar);
		addNewList.setVisible(false);

		addNewShopping = menu.findItem(R.id.new_shopping);
		addNewShopping.setOnMenuItemClickListener(listenerForActionBar);
		addNewShopping.setVisible(false);

		addNewEvent = menu.findItem(R.id.new_event);
		addNewEvent.setOnMenuItemClickListener(listenerForActionBar);
		addNewEvent.setVisible(false);

		switch (MenuService.getSelectItem()) {
		case 0:
			addNewEvent.setVisible(true);
			break;
		case 1:
			addNewList.setVisible(true);
			addNewShopping.setVisible(true);
			break;
		case 2:

			break;

		default:
			break;
		}
		if (MenuService.getSelectItem() != 1) {

		}
		return true;
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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

						})
				.setNegativeButton(getString(android.R.string.cancel), null)
				.create().show();
	}

	// update fragment after changing the list
	public void updateFragment(Fragment fragment) {
		FragmentManager fragmentManager = activity.getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.main, fragment)
				.commit();
		// update menu
		menu.setAdapter(new ListMenuAdapter(titleItems));
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateFragment(new EventsFragment());
	}

	/**
	 * @return the iManager
	 */
	public static MainActivity getActivity() {
		return activity;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		HelperFactory.getHelper().close();
		HelperFactory.releaseHelper();

	}

	public ItemMenu[] getTitleItems() {
		return titleItems;
	}

	public CharSequence getTitleActionBar() {
		return title;
	}

	public void setTitleActionBar(String title) {
		this.title = title;
	}

	public DrawerLayout getDrawerLayout() {
		return drawerLayout;
	}

	public ListView getMenu() {
		return menu;
	}

}
