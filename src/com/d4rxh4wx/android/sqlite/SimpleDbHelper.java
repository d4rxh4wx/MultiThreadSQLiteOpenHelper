package com.d4rxh4wx.android.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class SimpleDbHelper {
	
	private final static String TAG = "MULTI-THREAD-DB-HELPER";

	private MultiThreadSQLiteOpenHelper dbHelper;
	
	public static final SimpleDbHelper INSTANCE = new SimpleDbHelper(); 

	private SimpleDbHelper() {
		
	}
	
	public SQLiteDatabase open(Context context) {
		synchronized(this) {
			Logger.INSTANCE.debug(TAG, "asking for opening");
			if (dbHelper == null) {
				dbHelper = new MyMultiThreadSQLiteOpenHelper(context);
			}
			return dbHelper.getWritableDatabase();
		}
	}
	
	public void close() {
		synchronized(this) {
			Logger.INSTANCE.debug(TAG, "asking for closing");
			if (dbHelper != null) {
				if (dbHelper.closeIfNeeded()) {
					dbHelper = null;
				}
			}
		}
	}
}
