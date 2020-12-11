package org.codejudge.sb.response;

import javax.validation.constraints.NotNull;

public class RestResponse {
	@NotNull
    private String status;

    private String message;

    private String communication;

    //default constructor
    public RestResponse() {
    }
    
    //constructor with one parameter
    public RestResponse(String status) {
    this.status = status;
    }

    //parameterized constructor
    public RestResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
    
  //parameterized constructor
    public RestResponse(String status, String message, String communication) {
        this.status = status;
        this.message = message;
        this.communication = communication;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}
	
	
}