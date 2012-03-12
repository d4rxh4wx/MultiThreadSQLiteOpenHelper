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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityA extends Activity {
	
	private Thread thread;
	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				startActivity(new Intent(view.getContext(), ActivityB.class));
				
			}
		});
    }

	@Override
	protected void onStart() {
		Logger.INSTANCE.debug("ACTIVITY", "ActivityA onStart");	    			
		super.onStart();
	}
	
	
	
	

	@Override
	protected void onResume() {
		Logger.INSTANCE.debug("ACTIVITY", "ActivityA onResume");
		SimpleDbHelper.INSTANCE.open(this.getApplicationContext()); // UIThread opens DB
		
		if (thread == null || !thread.isAlive()) { 
			thread = new Thread(new DbBackgroundRunnable(this.getApplicationContext()));
			thread.start();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		Logger.INSTANCE.debug("ACTIVITY", "ActivityA onPause");
		if (thread != null && thread.isAlive()) {
			Logger.INSTANCE.debug("ACTIVITY", "ActivityA interrupts background thread");
			thread.interrupt();
		}
		SimpleDbHelper.INSTANCE.close(); // UIThread asks for closing DB
		super.onPause();
	}

	@Override
	protected void onStop() {
		Logger.INSTANCE.debug("ACTIVITY", "ActivityA onStop");
		super.onStop();
	}
    
	private class DbBackgroundRunnable implements Runnable {
		
		private Context context;
		
		public DbBackgroundRunnable(Context context) {
			this.context = context;
		}
		
		@Override
		public void run() {
			try{
				SimpleDbHelper.INSTANCE.open(context); // background thread opens DB
				while (true) {
					Thread.sleep(1000); // simulates interactions with DB
					Logger.INSTANCE.debug("THREAD", "Thread is still running and simulates operation on DB");
				}
			} catch (InterruptedException e) {
				Logger.INSTANCE.debug("THREAD", "Thread interrupted!");
			} finally {
				SimpleDbHelper.INSTANCE.close(); // background thread asks for closing DB
			}
		}
	}
    
}