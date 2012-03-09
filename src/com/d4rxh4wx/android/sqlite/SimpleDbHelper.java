package com.d4rxh4wx.android.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * 
 * SimpleDbHelper asking for an opened or closed database 
 * 
 * @author d4rxh4wx
 *
 */
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
			return dbHelper.getWritableDatabase(); // getting a cached database
		}
	}
	
	public void close() {
		synchronized(this) {
			Logger.INSTANCE.debug(TAG, "asking for closing");
			if (dbHelper != null) {
				// Ask for closing database
				if (dbHelper.closeIfNeeded()) {
					dbHelper = null; // database closed: free resource for GC
				}
			}
		}
	}
}
