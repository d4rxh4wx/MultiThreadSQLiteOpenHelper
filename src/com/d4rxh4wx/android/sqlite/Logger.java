package com.d4rxh4wx.android.sqlite;

import android.util.Log;

public class Logger {

	private static final String LOG_TAG = "LOGGER";

	private static final boolean PROD = false;
	
	public final static Logger INSTANCE = new Logger();
	
	public void verbose(String subTag, String msg) {
		if (!PROD) {
			Log.v(LOG_TAG + "-" + subTag, format(msg));
		}
	}
	
	public void verbose(String msg) {
		if (!PROD) {
			Log.v(LOG_TAG, format(msg));
		}
	}
	
	public void debug(String subTag, String msg) {
		if (!PROD) {
			Log.d(LOG_TAG + "-" + subTag, format(msg));
		}
	}
	
	public void debug(String msg) {
		if (!PROD) {
			Log.d(LOG_TAG, format(msg));
		}
	}

	public void warning(String msg) {
		Log.w(LOG_TAG, format(msg));
	}
	
	public void warning(String msg, Throwable e) {
		Log.w(LOG_TAG, format(msg), e);
	}
	
	public void error(String msg) {
		Log.e(LOG_TAG, format(msg));
	}
	
	public void error(String msg, Throwable e) {
		Log.e(LOG_TAG, format(msg), e);
	}
	
	private String format(String msg) {
		return String.format("[%2s] %s", Thread.currentThread().getId(), msg);
	}
}
