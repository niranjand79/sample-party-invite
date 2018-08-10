package com.mine.workbench.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mine.workbench.dao.IDBOperations;
import com.mine.workbench.model.Vaccine;

@RestController
public class WebController {

//	@Autowired
	private IDBOperations dbOps;

	@RequestMapping(value="/vaccines",produces="application/json")
	public List<Vaccine> sayHello() throws SQLException {
		return this.dbOps.getAllVx();
	}

}
