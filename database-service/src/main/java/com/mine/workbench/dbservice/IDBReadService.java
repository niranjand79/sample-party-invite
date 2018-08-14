package com.mine.workbench.dbservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mine.workbench.dbservice.exception.DatabaseServiceException;
import com.mine.workbench.model.Event;
import com.mine.workbench.model.Invitation;

/**
 * Service API to read values from database
 * @author niranjandeshpande
 *
 */
@Service
public interface IDBReadService {
	
	/**
	 * Finds responses to an event
	 * @param eventId
	 * @return List<Invitation>
	 * @throws DatabaseServiceException
	 */
	List<Invitation> findResponsesForEvent(Long eventId) throws DatabaseServiceException;
	
	/**
	 * Find an event by event id
	 * @param eventId
	 * @return Event
	 * @throws DatabaseServiceException
	 */
	Event findEvent(Long eventId) throws DatabaseServiceException;

}
