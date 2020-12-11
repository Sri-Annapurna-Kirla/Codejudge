package org.codejudge.sb.controller;

import javax.validation.Valid;

import org.codejudge.sb.exceptions.NoLeadExistsException;
import org.codejudge.sb.exceptions.NoLeadFoundException;
import org.codejudge.sb.request.LeadRequest;
import org.codejudge.sb.request.MarkLeadRequest;
import org.codejudge.sb.response.Lead;
import org.codejudge.sb.response.RestResponse;
import org.codejudge.sb.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api")
@RestController
public class AppController {

	private LeadService leadService;

	@Autowired
	public AppController(LeadService leadService1) {
		leadService = leadService1;
	}

	@ApiOperation("This is the hello world api")
	@GetMapping("/")
	public String hello() {
		return "Hello World!!";
	}

	//method to get the leads
	@RequestMapping(value = "/leads/{lead_id}", method = RequestMethod.GET)
	public Lead getLead(@PathVariable("lead_id") Integer leadId)
			throws JsonProcessingException, NoLeadExistsException, NoLeadFoundException {
		return leadService.getLead(leadId);
	}

	//method that handles when path variable is not present
	@RequestMapping(value = "/leads/", method = RequestMethod.GET)
	public RestResponse getLeadWithoutPathVariable() throws NoLeadFoundException {
		throw new NoLeadFoundException("Bad Request");
	}

	//method to create leads
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/leads/", method = RequestMethod.POST)
	public Lead createUser(@RequestBody @Valid LeadRequest leadRequest, BindingResult bindingResult)
			throws NoLeadFoundException {
		throwIfInvalid(bindingResult);
		return leadService.createLead(leadRequest);
	}

	//method to update leads
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/leads/{lead_id}", method = RequestMethod.PUT)
	public RestResponse updateUser(@PathVariable("lead_id") Integer leadId, @RequestBody @Valid LeadRequest leadRequest,
			BindingResult bindingResult) throws NoLeadFoundException, JsonProcessingException, NoLeadExistsException {
		throwIfInvalid(bindingResult);
		
		leadService.updateLead(leadRequest, leadId);
		return new RestResponse("Success");
	}

	//method that handles when path variable is not present
	@RequestMapping(value = "/leads/", method = RequestMethod.PUT)
	public RestResponse updateLeadWithoutPathVariable() throws NoLeadFoundException {
		throw new NoLeadFoundException("Bad Request");
	}

	//method to delete leads
	@RequestMapping(value = "/leads/{lead_id}", method = RequestMethod.DELETE)
	public RestResponse deleteLead(@PathVariable("lead_id") Integer leadId)
			throws JsonProcessingException, NoLeadExistsException, NoLeadFoundException {
		leadService.deleteLead(leadId);
		return new RestResponse("success");
	}

	//method that handles when path variable is not present
	@RequestMapping(value = "/leads/", method = RequestMethod.DELETE)
	public RestResponse deleteLeadWithoutPathVariable() throws NoLeadFoundException {
		throw new NoLeadFoundException("Bad Request");
	}

	//method to mark the leads
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/mark_lead/{lead_id}", method = RequestMethod.PUT)
	public RestResponse markLead(@PathVariable("lead_id") Integer leadId,
			@RequestBody @Valid MarkLeadRequest markLeadRequest, BindingResult bindingResult)
			throws JsonProcessingException, NoLeadExistsException, NoLeadFoundException {
		throwIfInvalid(bindingResult);

		return leadService.markLead(leadId, markLeadRequest.getCommunication());
	}

	////method that handles when path variable is not present
	@RequestMapping(value = "/mark_lead/", method = RequestMethod.PUT)
	public RestResponse markLeadWithoutPathVariable() throws NoLeadFoundException {
		throw new NoLeadFoundException("Bad Request");
	}

	/*
	 * @RequestMapping(value = "/leads/", method = RequestMethod.GET) public Lead
	 * getLeads() throws JsonProcessingException, NoLeadExistsException,
	 * NoLeadFoundException { throw new NoLeadFoundException("Invalid Request"); }
	 */

	public void throwIfInvalid(BindingResult bindingResult) throws NoLeadFoundException {
		if (bindingResult.hasErrors()) {
			String message = null;
			if (bindingResult.getFieldError() != null) {
				message = bindingResult.getFieldError().getDefaultMessage();
			} else {
				message = "Please check your request";
			}
			throw new NoLeadFoundException(message);
		}

	}

	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleException(NoLeadExistsException exception) {
		return "{}";
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestResponse handleException(NoLeadFoundException exception) {
		RestResponse restResponse = new RestResponse();
		restResponse.setStatus("failure");
		restResponse.setMessage(exception.getMessage());
		return restResponse;
	}
}