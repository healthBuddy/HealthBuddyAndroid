package com.vidhatech.healthbuddy;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**Activity handling the main log in screen for the application.  This is the activity that is launched by the launcher.*/
public class LogInAcitivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_in, menu);
        return true;
    }
    
}
