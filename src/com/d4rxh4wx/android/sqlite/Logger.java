/*
 * Copyright (c) 2012 d4rxh4wx
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
