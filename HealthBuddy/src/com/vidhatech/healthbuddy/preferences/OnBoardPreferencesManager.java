package com.vidhatech.healthbuddy.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Utility class that helps access the on-board shared preferences manager
 *
 */
public class OnBoardPreferencesManager {
	
	private static final String USERNAME = "USERNAME";
	private static final String PRIVATE_KEY = "PRIVATE_KEY";
	private static final String DEVICE_TOKEN = "DEVICE_TOKEN";

	public static void storePrivateKey(final Context context, final String privateKey)
	{
		storeData(context, PRIVATE_KEY, privateKey);
	}
	
	public static void storeDeviceToken(final Context context, final String deviceToken)
	{
		storeData(context, DEVICE_TOKEN, deviceToken);
	}
	
	public static String getDeviceToken(final Context context)
	{
		return getData(context, DEVICE_TOKEN);
	}
	
	public static String getPrivateKey(final Context context)
	{
		return getData(context, PRIVATE_KEY);
	}
	
	public static void storeUserName(final Context context, final String userName)
	{
		storeData(context, USERNAME, userName);
	}

	public static String getUserName(final Context context)
	{
		return getData(context, USERNAME);
	}
	
	/**
	 * Stores some data in the shared preferences
	 */
	public static void storeData(final Context context, final String key, final String value)
	{
		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		
		final Editor edit = preferences.edit();
		edit.putString(key, value);
		edit.commit(); 
	}
	
	/**
	 * Gets some data from the shared preferences
	 * @return the value or null if it doesn't exist
	 */
	public static String getData(final Context context, final String key)
	{
		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return preferences.getString(key, null);
	}
}
