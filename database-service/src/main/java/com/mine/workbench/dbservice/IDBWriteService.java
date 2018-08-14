package com.mine.workbench.dbservice;

import org.springframework.stereotype.Service;

import com.mine.workbench.dbservice.exception.DatabaseServiceException;
import com.mine.workbench.model.Event;
import com.mine.workbench.model.EventInvitation;
import com.mine.workbench.model.Invitation;

/**
 * Service API to write values to database
 * @author niranjandeshpande
 *
 */
@Service
public interface IDBWriteService {


	void createEvent(Event event)throws DatabaseServiceException;
	
	void createInvitation(EventInvitation eventInvitation)throws DatabaseServiceException;
	
	int updateInvitation(Invitation invitation) throws DatabaseServiceException;
	
	int updateEvent(Event event)throws DatabaseServiceException;
	
}
