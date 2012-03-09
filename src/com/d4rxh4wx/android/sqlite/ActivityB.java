package com.d4rxh4wx.android.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityB extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button) findViewById(R.id.button1);
        button.setVisibility(View.GONE);
    }
    
    @Override
	protected void onStart() {
    	Logger.INSTANCE.debug("ACTIVITY", "ActivityB onStart");
		super.onStart();
	}
    
    

	@Override
	protected void onResume() {
		Logger.INSTANCE.debug("ACTIVITY", "ActivityB onResume");
		SimpleDbHelper.INSTANCE.open(this.getApplicationContext());
		super.onResume();
	}

	@Override
	protected void onPause() {
		Logger.INSTANCE.debug("ACTIVITY", "ActivityB onPause");
		SimpleDbHelper.INSTANCE.close();
		super.onPause();
	}

	@Override
	protected void onStop() {
		Logger.INSTANCE.debug("ACTIVITY", "ActivityB onStop");
		super.onStop();
	}

}
