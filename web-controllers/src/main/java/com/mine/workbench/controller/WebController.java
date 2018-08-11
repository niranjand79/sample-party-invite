package com.mine.workbench.controller;

import java.sql.SQLException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mine.workbench.dao.IDBOperations;
import com.mine.workbench.model.Event;
import com.mine.workbench.model.Vaccine;

@RestController
@RequestMapping("/eventservice")
public class WebController {

	@Autowired
	private IDBOperations dbOps;

	@RequestMapping(value="/events",produces="application/json", method=RequestMethod.POST)
	public Event createEvent(@RequestBody Event event) throws Exception {
		System.out.println(event.getHostFirstName());
		dbOps.createEvent(event);
		return event;
	}

}
