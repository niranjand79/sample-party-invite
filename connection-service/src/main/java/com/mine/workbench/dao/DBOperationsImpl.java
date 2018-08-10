package com.mine.workbench.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.mine.workbench.model.Vaccine;

//@Component
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
}
