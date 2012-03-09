package com.d4rxh4wx.android.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyMultiThreadSQLiteOpenHelper extends MultiThreadSQLiteOpenHelper {

	public MyMultiThreadSQLiteOpenHelper(Context context) {
		super(context, "DUMMY", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
