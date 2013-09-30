package com.esec.connection;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.esec.dao.NoteDAO;
import com.esec.dao.implementation.ListDAOImpl;
import com.esec.dao.implementation.ShoppingDAOImpl;
import com.esec.dao.implementation.TodoDAOImpl;
import com.esec.dao.interfaces.ShoppingDAO;
import com.esec.dao.interfaces.ShoppingListDAO;
import com.esec.dao.interfaces.TodoDAO;
import com.esec.model.Note;
import com.esec.model.Shopping;
import com.esec.model.ShoppingList;
import com.esec.model.Todo;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String TAG = DatabaseHelper.class.getSimpleName();

	private static final String DATABASE_NAME = "esec.db";

	private static final int DATABASE_VERSION = 1;

	private static final AtomicInteger usageCounter = new AtomicInteger(0);

	private TodoDAO todoDAO = null;
	private ShoppingDAO shoppingDAO = null;
	private NoteDAO noteDAO = null;
	private ShoppingListDAO listDAO = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Todo.class);
			TableUtils.createTable(connectionSource, Shopping.class);
			TableUtils.createTable(connectionSource, ShoppingList.class);
			TableUtils.createTable(connectionSource, Note.class);
		} catch (SQLException e) {
			Log.e(TAG, "error creating DB " + DATABASE_NAME);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVer, int newVer) {
		Log.d("deb", "db onUpgrade ");
		try {
			TableUtils.createTable(connectionSource, Note.class);
			Log.d("deb", "db Upgraded");
		} catch (SQLException e) {
			Log.e(TAG, "error upgrading db " + DATABASE_NAME + "from ver "
					+ oldVer);
			throw new RuntimeException(e);
		}

	}

	public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer) {// in
																		// use
																		// when
																		// oldVer>newVer
		Log.d("deb", "db onDowngrade");
		// try {
		// TableUtils.createTable(connectionSource, Todo.class);
		// } catch (SQLException e) {
		// Log.d("deb", "error upgrading db " + DATABASE_NAME + "from ver "
		// + oldVer);
		//
		// }
	}

	public TodoDAO getTodoDAO() throws SQLException {
		if (todoDAO == null) {
			try {
				todoDAO = new TodoDAOImpl(getConnectionSource(), Todo.class);
			} catch (Exception e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
		}
		return todoDAO;
	}

	public ShoppingDAO getShoppingDAO() throws SQLException {
		if (shoppingDAO == null) {
			try {
				shoppingDAO = new ShoppingDAOImpl(getConnectionSource(),
						Shopping.class);
			} catch (Exception e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
		}
		return shoppingDAO;
	}

	public NoteDAO getNoteDAO() throws SQLException {
		if (noteDAO == null) {
			try {
				noteDAO = new NoteDAO(getConnectionSource(), Note.class);
			} catch (Exception e) {
				Log.i(getClass().getName(), e.getMessage());
			}
		}
		return noteDAO;
	}

	public ShoppingListDAO getShoppingListDAO() throws SQLException {
		if (listDAO == null) {
			try {
				listDAO = new ListDAOImpl(getConnectionSource(),
						ShoppingList.class);
			} catch (Exception e) {
				Log.i(getClass().getName(), e.getLocalizedMessage());
			}
		}
		return listDAO;
	}

	@Override
	public void close() {
		try {
			if (usageCounter.decrementAndGet() <= 0) {
				HelperFactory.releaseHelper();
				todoDAO = null;
				shoppingDAO = null;
				noteDAO = null;
				super.close();
			}
		} catch (Exception e) {
			System.out.println("ERROR " + e.getLocalizedMessage());
		}
	}
}