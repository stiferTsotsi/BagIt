package com.realtimeverification.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by vaal on 3/10/2015.
 */
public class DatabaseUser {

	private SQLiteDatabase database;
	private BagItDB dbHelper;


	public DatabaseUser(Context context) {
		dbHelper = new BagItDB(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}


	public void addDatabaseUser(){

	}


}
