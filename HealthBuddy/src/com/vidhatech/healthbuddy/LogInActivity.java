package com.vidhatech.healthbuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vidhatech.healthbuddy.preferences.OnBoardPreferencesManager;
import com.vidhatech.healthbuddy.service.ServiceCaller;
import com.vidhatech.healthbuddy.service.domain.LogInRequest;
import com.vidhatech.healthbuddy.service.domain.LogInResponse;

/**Activity handling the main log in screen for the application.  This is the activity that is launched by the launcher.*/
public class LogInActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //if the user has previously authenticated then we forward them to the home activity
        if(isUserAlreadyAuthenticated())
        {
        	startHomeActivity();
        }
        
        
        setContentView(R.layout.activity_log_in);
        
        final Button loginButton = (Button)findViewById(R.id.loginButton);
        
        //set the click listener for the button
        loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				logInButtonClicked();
				
			}
		});
    }
    
    /**
     * Checks the preferences manager to see if various identifier tokens are already present
     * @return
     */
    private boolean isUserAlreadyAuthenticated()
    {
    	return (OnBoardPreferencesManager.getDeviceToken(this) != null && OnBoardPreferencesManager.getPrivateKey(this) != null
    			&& OnBoardPreferencesManager.getUserName(this) != null);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_in, menu);
        return true;
    }
    
    /**
     * Implements what happens when the log in button is clicked
     */
    public void logInButtonClicked()
    {
    	
    	//hide the error view in case it was visible
    	final TextView errorView = (TextView)findViewById(R.id.loginError);
    	errorView.setVisibility(View.INVISIBLE);
    	
    	//get the username, password and device id
    	final TextView usernameText = (TextView)findViewById(R.id.userName);
    	final String username = usernameText.getText().toString();
    	
    	final TextView passwordText = (TextView)findViewById(R.id.password);
    	final String password = passwordText.getText().toString();
    	
    	//TODO: enable this later
    	/*final TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
    	final String uid = tManager.getDeviceId();*/
    	final String uid = "uid";
    	
    	//turn on the progress bar so that users have visual feedback
    	final ProgressBar progressBar = (ProgressBar)findViewById(R.id.loginProgressBar);
    	progressBar.setVisibility(View.VISIBLE);
    	
    	//call the service
    	final LogInRequest loginRequest = new LogInRequest(username, password, uid);
    	
    	final ServiceCaller serviceCaller = new ServiceCaller();
    	final LogInResponse response = serviceCaller.doLogIn(loginRequest);
    	
    	//disable the progress bar
    	progressBar.setVisibility(View.GONE);
    	
    	//check what the response was
    	if(response.isSuccessful())
    	{
    		//store the device token, private key and username so that they can be used in the future
    		OnBoardPreferencesManager.storeDeviceToken(this, response.getAuthenticationToken());
    		OnBoardPreferencesManager.storePrivateKey(this, response.getPrivateKey());
    		OnBoardPreferencesManager.storeUserName(this, username);

    		//Launch the home activity
    		startHomeActivity();
    	}
    	else
    	{
    		//show the error to the user
    		errorView.setText(response.getReturnMessage());
    		errorView.setVisibility(View.VISIBLE);
    	}
    }
    
    private void startHomeActivity()
    {
		final Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
    }
    
}
