package org.codejudge.sb.service;

import org.codejudge.sb.exceptions.NoLeadExistsException;
import org.codejudge.sb.exceptions.NoLeadFoundException;
import org.codejudge.sb.request.LeadRequest;
import org.codejudge.sb.response.Lead;
import org.codejudge.sb.response.RestResponse;
import org.springframework.stereotype.Service;

@Service
public interface LeadService {
	public Lead getLead(Integer id) throws NoLeadExistsException;

	public Lead createLead(LeadRequest leadRequest) throws NoLeadFoundException;
	
	public void updateLead(LeadRequest leadRequest, Integer id) throws NoLeadExistsException, NoLeadFoundException;
	
	public void deleteLead(Integer id) throws NoLeadExistsException;
	
	public RestResponse markLead(Integer id, String communication) throws NoLeadExistsException;
}
