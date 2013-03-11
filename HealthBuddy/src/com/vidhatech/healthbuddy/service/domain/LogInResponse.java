package com.vidhatech.healthbuddy.service.domain;

/**
 * Represents a response received after a login request.
 *
 */
public class LogInResponse extends BaseServiceResponse{
	
	private final boolean successful;
	private final String privateKey;
	private final String authenticationToken;
	private final String returnMessage;
	
	public LogInResponse(final boolean successful, final String privateKey, final String authenticationToken, final String returnMessage)
	{
		this.successful = successful;
		this.privateKey = privateKey;
		this.authenticationToken = authenticationToken;
		this.returnMessage = returnMessage;
		json = null;
	}
	
	public LogInResponse(final String json)
	{
		boolean defaultSuccess = false;
		String privateKey = null;
		String defaultAuthenticationToken = null;
		String defaultReturnMessage = null;
		
		if(json == null || json.length() == 0)
		{
			defaultSuccess = false;
			privateKey = null;
			defaultAuthenticationToken = null;
			defaultReturnMessage = null;
		}
		else
		{
			//remove the outer brackets
			String subString = json.substring(1, json.length() - 1);
			
			//remove quotation marks for all keys and string values
			subString = subString.replace("\"", "");
			
			//split the key/value pairs
			final String [] dataArray = subString.split(",");
			
			//parse each key/value pair
			for (final String data : dataArray)
			{
				//split out the key and value
				final String [] splitData = data.split(":");
						
				//figure out the key type
				if(splitData[0].equalsIgnoreCase("successful"))
				{
					defaultSuccess = Boolean.valueOf(splitData[1]);
				}
				else if(splitData[0].equalsIgnoreCase("privateKey"))
				{
					privateKey = String.valueOf(splitData[1]);
				}
				else if(splitData[0].equalsIgnoreCase("authenticationToken"))
				{
					defaultAuthenticationToken = String.valueOf(splitData[1]);
				}
				else if(splitData[0].equalsIgnoreCase("returnMessage"))
				{
					defaultReturnMessage = String.valueOf(splitData[1]);
				}
			}
		}
		
		successful = defaultSuccess;
		authenticationToken = defaultAuthenticationToken;
		this.privateKey = privateKey;
		returnMessage = defaultReturnMessage;
	}
	
	/**
	 * Returns the message returned by the server
	 */
	public String getReturnMessage()
	{
		return returnMessage;
	}
	
	public boolean isSuccessful()
	{
		return successful;
	}
	
	/**
	 * Returns the private key to be used in future requests.  The encryption key is a AES-256 bit key.
	 * @return the private key or null if the successful flag is set to false
	 */
	public String getPrivateKey()
	{
		return privateKey;
	}
	
	/**
	 * Returns the authentication token to be sent in with future requests.
	 * @return the authentication token or null if the successful flag is set to false
	 */
	public String getAuthenticationToken()
	{
		return authenticationToken;
	}
}
