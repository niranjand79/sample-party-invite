package com.mine.workbench.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mine.workbench.dbservice.IDBReadService;
import com.mine.workbench.dbservice.IDBWriteService;
import com.mine.workbench.dbservice.exception.DatabaseServiceException;
import com.mine.workbench.model.Event;
import com.mine.workbench.model.EventInvitation;
import com.mine.workbench.model.EventResponse;
import com.mine.workbench.model.Invitation;
import com.mine.workbench.model.ResponseStatus;

import come.mine.workbench.validationservice.IValidator;
import come.mine.workbench.validationservice.exception.ValidatorServiceException;
import come.mine.workbench.validationservice.impl.ValidatorServiceImpl;

@RestController
@RequestMapping("/eventservice")
public class WebController {

	@Autowired
	private IDBWriteService dbWriteService;
	@Autowired
	private IDBReadService dbReadService;
	// This should have been auto-wired
	private IValidator validatorService;
	private static final String ERROR="ERROR";
	private static final String SUCCESS="SUCCESS";

	/**
	 * Get request for genrating JSON for event and its responses
	 * @param eventId
	 * @return ResponseEntity<EventResponse>
	 */
	@RequestMapping(value = "/events/{eventId}/responses", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<EventResponse> getEventResponses(@PathVariable("eventId") Long eventId) {
		Event event;
		EventResponse eventResponse = new EventResponse();
		try {
			event = this.dbReadService.findEvent(eventId);
			if (event != null) {
				eventResponse.setEvent(event);
				List<Invitation> lstInvitation = this.dbReadService.findResponsesForEvent(eventId);
				eventResponse.setTotalInvitations(lstInvitation.size());
				for (Invitation invitation : lstInvitation) {
					if (invitation.getResponse().getDbValue() == 0) {
						eventResponse.setNotResponded(eventResponse.getNotResponded() + 1);
					}
					if (invitation.getResponse().getDbValue() == 1) {
						eventResponse.setAttending(eventResponse.getAttending() + 1);
					}
					if (invitation.getResponse().getDbValue() == 2) {
						eventResponse.setNotAttending(eventResponse.getNotAttending() + 1);
					}
					if (invitation.getResponse().getDbValue() == 4) {
						eventResponse.setTentative(eventResponse.getTentative() + 1);
					}
				}
			} else {
				eventResponse.setStatus(ERROR);
				eventResponse.setErrorMEssage("Event doesn't exist.");
				return new ResponseEntity<EventResponse>(eventResponse, HttpStatus.BAD_REQUEST);
			}
		} catch (DatabaseServiceException e) {
			eventResponse.setStatus(ERROR);
			eventResponse.setErrorMEssage("Could not process the request. " + e.getMessage());
			return new ResponseEntity<EventResponse>(eventResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EventResponse>(eventResponse, HttpStatus.OK);
	}

	/**
	 * POST request for creating event and invitation
	 * @param inputJson
	 * @return ResponseEntity<ResponseStatus>
	 */
	@RequestMapping(value = "/invitation", produces = "application/json", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ResponseStatus> createInvitation(@RequestBody String inputJson) {
		ResponseStatus responseJson = new ResponseStatus();
		try {
			this.validatorService = new ValidatorServiceImpl(); 
			EventInvitation eventInvitation = this.validatorService.validateEventCreation(inputJson);
			dbWriteService.createInvitation(eventInvitation);
			responseJson.setStatus(SUCCESS);
			responseJson.setMessage("event and invitations created.");
			return new ResponseEntity<ResponseStatus>(responseJson, HttpStatus.OK);
		} catch (ValidatorServiceException e) {
			responseJson.setStatus(ERROR);
			responseJson.setMessage(e.getMessage());
			return new ResponseEntity<ResponseStatus>(responseJson, HttpStatus.BAD_REQUEST);
		} catch (DatabaseServiceException e1) {
			responseJson.setStatus(ERROR);
			responseJson.setMessage(e1.getMessage());
			return new ResponseEntity<ResponseStatus>(responseJson, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * PUT request to update the invitation
	 * @param invitation
	 * @return ResponseEntity<ResponseStatus>
	 */
	@RequestMapping(value = "/invitation", produces = "application/json", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ResponseStatus> updateInvitation(@RequestBody Invitation invitation) {
		ResponseStatus responseJson = new ResponseStatus();
		try {
			int updateCount = this.dbWriteService.updateInvitation(invitation);
			if (updateCount == 0) {
				responseJson.setStatus(ERROR);
				responseJson.setMessage("Could not update the invitation");
			} else {
				responseJson.setStatus(SUCCESS);
				responseJson.setMessage("Invitation updated");
			}
		} catch (DatabaseServiceException e) {
			responseJson.setStatus(ERROR);
			responseJson.setMessage(e.getMessage());
			return new ResponseEntity<ResponseStatus>(responseJson, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseStatus>(responseJson, HttpStatus.OK);
	}

	/**
	 * PUT request to update the event
	 * @param event
	 * @return ResponseEntity<ResponseStatus>
	 */
	@RequestMapping(value = "/event", produces = "application/json", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ResponseStatus> updateEvent(@RequestBody Event event) {
		ResponseStatus responseJson = new ResponseStatus();
		try {
			int updateCount = this.dbWriteService.updateEvent(event);
			if (updateCount == 0) {
				responseJson.setStatus(ERROR);
				responseJson.setMessage("Could not update the event.");
			} else {
				responseJson.setStatus(SUCCESS);
				responseJson.setMessage("Event updated");
			}
		} catch (DatabaseServiceException e) {
			responseJson.setStatus(ERROR);
			responseJson.setMessage(e.getMessage());
			return new ResponseEntity<ResponseStatus>(responseJson, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ResponseStatus>(responseJson, HttpStatus.OK);
	}
}
