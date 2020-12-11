package org.codejudge.sb.request;

import javax.validation.constraints.NotNull;

public class MarkLeadRequest {

	@NotNull
	private String communication;

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}
	
	
}
