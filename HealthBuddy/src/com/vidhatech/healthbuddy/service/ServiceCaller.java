package com.vidhatech.healthbuddy.service;

import com.vidhatech.healthbuddy.service.domain.LogInRequest;
import com.vidhatech.healthbuddy.service.domain.LogInResponse;

public class ServiceCaller {

	/**
	 * Calls the log in operation on the back end service.
	 * @param request the request to use when calling the operation
	 * @return the response object
	 */
	public LogInResponse doLogIn(final LogInRequest request)
	{
		//TODO: enable once server is running properly
/*		try
		{
			//for the login request we don't encrypt anything
			
			//get a https connection
			final URL url = new URL("some encrypted connection");
			final HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
			
			//write the json data
			final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			bw.write(request.toJson());
			
			//read the data
			final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			//if the server didn't respond with an OK message (which indicates a server error) then show an error
			final int statusCode = connection.getResponseCode();
			if(statusCode != HttpStatus.SC_OK)
			{
				return new LogInResponse(false, null, null, "Error contacting server.  Please try again");
			}
			
			//parse the response and return it
			final String jsonResponse = br.readLine();
			final LogInResponse response = new LogInResponse(jsonResponse);
			return response;
		}
		catch (final MalformedURLException murlE)
		{
			Log.e(ServiceCaller.class.getCanonicalName(), murlE.getMessage());
		}
		catch (final IOException e)
		{
			Log.e(ServiceCaller.class.getCanonicalName(), e.getMessage());
		}
		
		return new LogInResponse(false, null, null, "Error contacting server.  Please try again");*/
		
		final String json = request.toJson();
		return new LogInResponse(true, "privateKey", "token", null);
	}
	
}
