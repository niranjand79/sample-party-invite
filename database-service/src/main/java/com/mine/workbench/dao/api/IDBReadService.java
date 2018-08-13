package com.mine.workbench.dao.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mine.workbench.dao.exception.DatabaseServiceException;
import com.mine.workbench.model.Event;
import com.mine.workbench.model.Invitation;

@Service
public interface IDBReadService {
	
	List<Invitation> findResponsesForEvent(Long eventId) throws DatabaseServiceException;
	
	Event findEvent(Long eventId) throws DatabaseServiceException;

}
