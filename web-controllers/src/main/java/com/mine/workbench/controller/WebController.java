package com.mine.workbench.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mine.workbench.dao.api.IDBReadService;
import com.mine.workbench.dao.api.IDBWriteService;
import com.mine.workbench.dao.exception.DatabaseServiceException;
import com.mine.workbench.model.Event;
import com.mine.workbench.model.EventInvitation;
import com.mine.workbench.model.EventResponse;
import com.mine.workbench.model.Invitation;

@RestController
@RequestMapping("/eventservice")
public class WebController {

	@Autowired
	private IDBWriteService dbWriteService;
	@Autowired
	private IDBReadService dbReadService;

	@RequestMapping(value = "/events", produces = "application/json", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> createEvent(@RequestBody Event event) throws Exception {
		dbWriteService.createEvent(event);
		// URI location =
		// ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(1).toUri();
		JSONObject test = new JSONObject();
		test.put("STATUS", "SUCCESS");
		return new ResponseEntity<String>(test.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/events/{eventId}/responses", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<EventResponse> getEventResponses(@PathVariable("eventId") Long eventId) throws Exception {
		// write an exception class for validation
		Event event = this.dbReadService.findEvent(eventId);
		EventResponse eventResponse = new EventResponse();
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
		}
		return new ResponseEntity<EventResponse>(eventResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/invitation", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> createInvitation(@RequestBody String invitation) throws Exception {
		// remove above exception clause and catch exceptions
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode eventInvitationNode = objectMapper.readTree(invitation);
		Event event = new Event();
		event.setName(eventInvitationNode.path("name").getTextValue());
		event.setHostFirstName(eventInvitationNode.path("hostFirstName").getTextValue());
		event.setHostLastName(eventInvitationNode.path("hostLastName").getTextValue());
		event.setEventMessage(eventInvitationNode.path("eventMessage").getTextValue());
		event.setLocation(eventInvitationNode.path("location").getTextValue());
		SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
		event.setEventDate(format.parse(eventInvitationNode.path("eventDate").getTextValue()));
		JsonNode invitationNode = eventInvitationNode.path("invitations");
		List<Invitation> lstInvitation = new ArrayList<>();
		if (invitationNode.isArray()) {
			for (JsonNode node : invitationNode) {
				Invitation invite = new Invitation(); // try using builder
														// pattern
				invite.setEventId(null);
				invite.setRecipientFirstName(node.path("recipientFirstName").getTextValue());
				invite.setRecipientLastName(node.path("recipientLastName").getTextValue());
				invite.setRecipientEmail(node.path("recipientEmail").getTextValue());
				lstInvitation.add(invite);
			}
		}
		EventInvitation eventInvitation = new EventInvitation();
		eventInvitation.setEvent(event);
		eventInvitation.setInvitations(lstInvitation);
		dbWriteService.createInvitation(eventInvitation);
		JSONObject test = new JSONObject();
		test.put("STATUS", "SUCCESS");
		return new ResponseEntity<String>(test.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/invitation", produces = "application/json", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updateInvitation(@RequestBody Invitation invitation) {
		JSONObject responseJson = new JSONObject();
		try {
			int updateCount = this.dbWriteService.updateInvitation(invitation);
			if (updateCount == 0) {
				responseJson.put("STATUS", "ERROR");
				responseJson.put("message", "Could not update the invitation");
			} else {
				responseJson.put("STATUS", "SUCCESS");
				responseJson.put("message", "Invitation updated");
			}
		} catch (DatabaseServiceException e) {
			responseJson.put("STATUS", "ERROR");
			responseJson.put("message", e.getMessage());
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/event", produces = "application/json", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updateEvent(@RequestBody Event event) {
		JSONObject responseJson = new JSONObject();
		try {
			int updateCount = this.dbWriteService.updateEvent(event);
			if (updateCount == 0) {
				responseJson.put("STATUS", "ERROR");
				responseJson.put("message", "Could not update the event");
			} else {
				responseJson.put("STATUS", "SUCCESS");
				responseJson.put("message", "Event updated");
			}
		} catch (DatabaseServiceException e) {
			responseJson.put("STATUS", "ERROR");
			responseJson.put("message", e.getMessage());
			return new ResponseEntity<String>(responseJson.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(responseJson.toString(), HttpStatus.OK);
	}
}
