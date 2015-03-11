package com.realtimeverification.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.realtimeverification.app.ui.activities.ActivityMain;

/**
 * Created by vaal on 3/10/2015.
 */
public class BagItDB extends SQLiteOpenHelper {

	public static final String TABLE_USER = "tblUser";
	public static final String USER_ID = "userId";
	public static final String FULL_NAME = "full_name";
	public static final String EMAIL = "email";
	public static final String CONTACT_NO = "contact_no";
	public static final String ACTIVE = "active";
	private static final String DATABASE_NAME = "bagIt.db";
	private static final int DATABASE_VERSION = 1;

	public BagItDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table " + TABLE_USER + "("
			+ USER_ID + " integer primary key autoincrement, "
			+ FULL_NAME + " text not null,"
			+ EMAIL + " text not null,"
			+ CONTACT_NO + " text not null,"
			+ ACTIVE + " text not null" + ");";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ActivityMain.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		onCreate(db);
	}
}
