package com.mine.workbench.dao.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mine.workbench.dao.exception.DatabaseServiceException;
import com.mine.workbench.model.Event;
import com.mine.workbench.model.EventInvitation;
import com.mine.workbench.model.Invitation;

@Component
public class DBWriteServiceImpl implements IDBWriteService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void createEvent(Event event) throws DatabaseServiceException {
		KeyHolder holder = new GeneratedKeyHolder();
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
		}, holder);
		event.setId(holder.getKey().longValue());
	}

	@Override
	@Transactional
	public void createInvitation(EventInvitation eventInvitation) throws DatabaseServiceException {
		Event event = eventInvitation.getEvent();
		createEvent(event);
		StringBuilder sqlBuilder = new StringBuilder("INSERT INTO INVITATION ");
		sqlBuilder.append("(EVENT_ID, RECIPIENT_FIRST_NAME, RECIPIENT_LAST_NAME, RECIPIENT_EMAIL)");
		sqlBuilder.append("VALUES");
		sqlBuilder.append("(?, ?, ?, ?)");
		this.jdbcTemplate.batchUpdate(sqlBuilder.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
				Invitation invitation = eventInvitation.getInvitations().get(i);
				preparedStatement.setLong(1, event.getId());
				preparedStatement.setString(2, invitation.getRecipientFirstName());
				preparedStatement.setString(3, invitation.getRecipientLastName());
				preparedStatement.setString(4, invitation.getRecipientEmail());
			}

			@Override
			public int getBatchSize() {
				return eventInvitation.getInvitations().size();
			}
		});
	}

	@Override
	public int updateInvitation(Invitation invitation) throws DatabaseServiceException {
		try {
			return this.jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					StringBuilder sqlBuilder = new StringBuilder("UPDATE INVITATION ");
					sqlBuilder.append("SET RESPONSE_DATE=?, RESPONSE_MESSAGE=?, INVITATION_STATUS=?");
					sqlBuilder.append(" WHERE INVITATION_ID=? AND EVENT_ID=?");
					PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());
					preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
					preparedStatement.setString(2, invitation.getResponseMessage());
					preparedStatement.setInt(3, invitation.getResponse().ordinal());
					preparedStatement.setLong(4, invitation.getId());
					preparedStatement.setLong(5, invitation.getEventId());
					return preparedStatement;
				}
			});
		} catch (Exception e) {
			throw new DatabaseServiceException("Error while updating invitation for an event", e);
		}
	}

	@Override
	public int updateEvent(Event event) throws DatabaseServiceException {
		try {
			return this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					StringBuilder sqlBuilder = new StringBuilder("UPDATE EVENT ");
					sqlBuilder.append("SET EVENT_NAME=?, HOST_FIRST_NAME=?, HOST_LAST_NAME=?, ");
					sqlBuilder.append(" LOCATION=?, EVENT_DATE=?, EVENT_MESSAGE=?, EVENT_STATUS=?");
					sqlBuilder.append(" WHERE EVENT_ID=?");
					PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString());
					preparedStatement.setString(1, event.getName());
					preparedStatement.setString(2, event.getHostFirstName());
					preparedStatement.setString(3, event.getHostLastName());
					preparedStatement.setString(4, event.getLocation());
					Timestamp sqlDate = new java.sql.Timestamp(event.getEventDate().getTime());
					preparedStatement.setTimestamp(5, sqlDate);
					preparedStatement.setString(6, event.getEventMessage());
					preparedStatement.setInt(7, event.getStatus().getDbValue());
					preparedStatement.setLong(8, event.getId());
					return preparedStatement;
				}
			});
		} catch (Exception e) {
			throw new DatabaseServiceException("Error while updating event", e);
		}
	}
}
