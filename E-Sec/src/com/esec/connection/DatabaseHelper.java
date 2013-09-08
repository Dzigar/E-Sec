package com.esec.connection;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.esec.dao.ToDoDAO;
import com.esec.model.Todo;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String TAG = DatabaseHelper.class.getSimpleName();

	private static final String DATABASE_NAME = "iManager.db";

	private static final int DATABASE_VERSION = 1;

	private static final AtomicInteger usageCounter = new AtomicInteger(0);

	private ToDoDAO toDoDAO = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Todo.class);
		} catch (SQLException e) {
			Log.e(TAG, "error creating DB " + DATABASE_NAME);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVer, int newVer) {
		try {
			TableUtils.dropTable(connectionSource, Todo.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(TAG, "error upgrading db " + DATABASE_NAME + "from ver "
					+ oldVer);
			throw new RuntimeException(e);
		}
	}

	public ToDoDAO getToDoDAO() throws SQLException {
		if (toDoDAO == null) {
			try {
				toDoDAO = new ToDoDAO(getConnectionSource(), Todo.class);
			} catch (Exception e) {
				Log.i(getClass().getName(), e.getMessage());
			}
		}
		return toDoDAO;
	}

	@Override
	public void close() {
		try {
			if (usageCounter.decrementAndGet() <= 0) {
				HelperFactory.releaseHelper();
				toDoDAO = null;
				super.close();
			}
		} catch (Exception e) {
			System.out.println("ERROR " + e.getLocalizedMessage());
		}
	}
}