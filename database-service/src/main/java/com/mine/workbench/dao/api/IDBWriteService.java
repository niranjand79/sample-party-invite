package com.mine.workbench.dao.api;

import org.springframework.stereotype.Service;

import com.mine.workbench.dao.exception.DatabaseServiceException;
import com.mine.workbench.model.Event;
import com.mine.workbench.model.EventInvitation;
import com.mine.workbench.model.Invitation;

@Service
public interface IDBWriteService {


	void createEvent(Event event)throws DatabaseServiceException;
	
	void createInvitation(EventInvitation eventInvitation)throws DatabaseServiceException;
	
	int updateInvitation(Invitation invitation) throws DatabaseServiceException;
	
	int updateEvent(Event event)throws DatabaseServiceException;
	
}
