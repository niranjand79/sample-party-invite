package com.mine.workbench.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import com.mine.workbench.model.Event;
import com.mine.workbench.model.Vaccine;

@Component
public class DBOperationsImpl implements IDBOperations {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Vaccine> getAllVx() throws SQLException {
		return jdbcTemplate.query("SELECT * FROM hfowner.vaccine", (rs, rowNum) -> {
			Vaccine vaccine = new Vaccine();
			vaccine.setVaccineId(rs.getInt("VACCINE_ID"));
			vaccine.setVaccineGroup(rs.getString("VACCINE_GROUP"));
			vaccine.setVaccineName(rs.getString("VACCINE_NAME"));
			vaccine.setVaccineComponenent(rs.getString("VACCINE_COMPONENT"));
			vaccine.setStateSuplied(rs.getInt("STATE_SUPPLIED"));
			vaccine.setAgeRange(rs.getString("AGE_RANGE"));
			vaccine.setDose(rs.getString("DOSE"));
			vaccine.setRoute(rs.getString("ROUTE"));
			vaccine.setManufacturer(rs.getString("MANUFACTURER"));
			vaccine.setCptCode(rs.getString("CPT_CODE"));
			vaccine.setCvxCode(rs.getString("CVX_CODE"));
			return vaccine;
		});
	}

	@Override
	public Vaccine getVaccine(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createEvent(Event event) {
		this.jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				StringBuilder sqlBuilder = new StringBuilder("INSERT INTO EVENT");
				sqlBuilder.append("(EVENT_NAME,HOST_FIRST_NAME, HOST_LAST_NAME, LOCATION, EVENT_DATE, EVENT_MESSAGE)");
				sqlBuilder.append("VALUES");
				sqlBuilder.append("(?,?,?,?,?,?)");
				PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());
				preparedStatement.setString(1, event.getName());
				preparedStatement.setString(2, event.getHostFirstName());
				preparedStatement.setString(3, event.getHostLastName());
				preparedStatement.setString(4, event.getLocation());
				Timestamp sqlDate = new java.sql.Timestamp(event.getEventDate().getTime());
				preparedStatement.setTimestamp(5, sqlDate);
				preparedStatement.setString(6, event.getEventMessage());
				return preparedStatement;
			}
		});

	}
}
