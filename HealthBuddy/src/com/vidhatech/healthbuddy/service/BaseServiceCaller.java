package com.vidhatech.healthbuddy.service;

import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class BaseServiceCaller {

	private String deviceToken;
	private String privateKey;
	
	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	public URLConnection getAuthenticatedConnection(String urlString) throws Exception
	{
		URL url = new URL(urlString);
		HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
		
		//add the device token so that the server can authenticate this request
		connection.addRequestProperty("DEVICE_TOKEN", deviceToken);
		
		return connection;
	}
	
	/**
	 * encrypts the provided data
	 */
	protected final String encryptData(String data) throws Exception
	{
		return EncryptionUtils.encrypt(data, privateKey);
	}
	
	/**
	 * decrypts the provided data
	 */
	protected final String decryptData(String data) throws Exception
	{
		return EncryptionUtils.decrypt(data, privateKey);
	}
}
