package com.vidhatech.healthbuddy.service.domain;

/**
 * Domain class used to talk to the server.  It override toString() in order to generate a JSON parsable string
 *
 */
public class LogInRequest extends BaseServiceRequest{

	public final String userName;
	public final String password;
	public final String deviceId;
	
	public LogInRequest(final String userName, final String password, final String deviceId) {
		super();
		this.userName = userName;
		this.password = password;
		this.deviceId = deviceId;
	}
	
	@Override
	public String toJson() {
		
		final StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		sb.append(" \"username\": ");
		sb.append("\""+userName+"\"");
		sb.append(" \"password\": ");
		sb.append("\""+password+"\"");
		sb.append(" \"deviceid\": ");
		sb.append("\""+deviceId+"\"");
		sb.append("}");

		return sb.toString();
	}

	
}
