package come.mine.workbench.validationservice.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.mine.workbench.model.Event;
import com.mine.workbench.model.EventInvitation;
import com.mine.workbench.model.Invitation;

import come.mine.workbench.validationservice.IValidator;
import come.mine.workbench.validationservice.exception.ValidatorServiceException;

@Component
public class ValidatorServiceImpl implements IValidator {

	@Override
	public EventInvitation validateEventCreation(final String inputJson)
			throws ValidatorServiceException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode eventInvitationNode = null;
		Event event = new Event();
		List<Invitation> lstInvitation = new ArrayList<>();
		try {
			eventInvitationNode = objectMapper.readTree(inputJson);
			event.setName(eventInvitationNode.path("name").getTextValue());
			event.setHostFirstName(eventInvitationNode.path("hostFirstName").getTextValue());
			event.setHostLastName(eventInvitationNode.path("hostLastName").getTextValue());
			event.setEventMessage(eventInvitationNode.path("eventMessage").getTextValue());
			event.setLocation(eventInvitationNode.path("location").getTextValue());
			SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
			try {
				event.setEventDate(format.parse(eventInvitationNode.path("eventDate").getTextValue()));
			} catch (ParseException e) {
				throw new ValidatorServiceException("Unable to parse date", e);
			}
			JsonNode invitationNode = eventInvitationNode.path("invitations");
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
		} catch (JsonProcessingException jpException) {
			throw new ValidatorServiceException("Wrong JSON input received", jpException);
		} catch (IOException ioException) {
			throw new ValidatorServiceException("Error reading input json", ioException);
		}
		EventInvitation validatedObject = new EventInvitation();
		validatedObject.setEvent(event);
		validatedObject.setInvitations(lstInvitation);
		return validatedObject;
	}

}
