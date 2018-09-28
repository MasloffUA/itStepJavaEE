package server;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private RequestType requestType;
	
	public Message (String message, RequestType requestType) {
		this.message = message;
		this.requestType = requestType;
	}

	public String getMessage() {
		return message;
	}

	public RequestType getRequestType() {
		return requestType;
	}
	
	
}
