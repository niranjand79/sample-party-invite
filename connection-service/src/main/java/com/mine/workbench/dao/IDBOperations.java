package com.mine.workbench.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mine.workbench.model.Vaccine;

@Service
public interface IDBOperations {

	List<Vaccine> getAllVx() throws SQLException;
	 Vaccine getVaccine(String id);
}
