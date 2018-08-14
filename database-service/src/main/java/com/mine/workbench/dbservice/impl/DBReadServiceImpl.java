package com.mine.workbench.dbservice.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mine.workbench.dbservice.IDBReadService;
import com.mine.workbench.dbservice.exception.DatabaseServiceException;
import com.mine.workbench.model.Event;
import com.mine.workbench.model.EventStatus;
import com.mine.workbench.model.Invitation;
import com.mine.workbench.model.InvitationStatus;

@Component
public class DBReadServiceImpl implements IDBReadService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Invitation> findResponsesForEvent(Long eventId) throws DatabaseServiceException {
		List<Invitation> lstInvitation = new ArrayList<>();
		try {
			lstInvitation = this.jdbcTemplate.query(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					StringBuilder sqlBuilder = new StringBuilder();
					sqlBuilder.append("SELECT * FROM INVITATION WHERE EVENT_ID=?");
					PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());
					preparedStatement.setLong(1, eventId);
					return preparedStatement;
				}
			}, new RowMapper<Invitation>() {

				@Override
				public Invitation mapRow(ResultSet rs, int rowNum) throws SQLException {
					Invitation invitation = new Invitation();
					invitation.setResponse(InvitationStatus.getEnumValue(rs.getInt("INVITATION_STATUS")));
					return invitation;
				}
			});
		} catch (Exception e) {
			throw new DatabaseServiceException("Error while finding response for an event", e);
		}
		return lstInvitation;
	}

	@Override
	public Event findEvent(Long eventId) throws DatabaseServiceException {
		// add queries to constant file
		try {
			return this.jdbcTemplate.queryForObject("SELECT * FROM EVENT WHERE EVENT_ID=?", new Object[] { eventId },
					new RowMapper<Event>() {
						@Override
						public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
							Event event = new Event();
							event.setName(rs.getString("EVENT_NAME"));
							event.setEventDate(Date.valueOf(rs.getTimestamp("EVENT_DATE").toLocalDateTime().toLocalDate()));
							event.setEventMessage(rs.getString("EVENT_MESSAGE"));
							event.setId(rs.getLong("EVENT_ID"));
							event.setHostFirstName(rs.getString("HOST_FIRST_NAME"));
							event.setHostLastName(rs.getString("HOST_LAST_NAME"));
							event.setLocation(rs.getString("LOCATION"));
							event.setStatus(EventStatus.getEnumValue(rs.getInt("EVENT_STATUS")));
							return event;
						}
					});
		} catch (Exception e) {
			throw new DatabaseServiceException("Error while finding an event", e);
		}
	}

}
