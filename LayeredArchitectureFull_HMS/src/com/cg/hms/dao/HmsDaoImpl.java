package com.cg.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cg.hms.exception.HMSException;
import com.cg.hms.model.Patient;
import com.cg.hms.utility.JdbcUtility;

public class HmsDaoImpl implements HmsDao {

	Connection connection = null;
	
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	static Logger logger = Logger.getLogger(HmsDaoImpl.class);

	/**
	 * method        : addpatientsDetails
	 * argument      : it's taking model object as an argument
	 * return type   : this method return the generated id to the user
	 * Author        : Capgemini
	 * Date          : 14 - Jan - 2019
	
	**/
	
	public int addPatientDetails(Patient patient) throws HMSException{
		logger.info("in add patients method");
		
	
		connection=JdbcUtility.getConnection();
		logger.info("connection object created");
		int generatedId=0;
			try {
				
					statement = connection.prepareStatement(QueryMapper.insertPatientDetails);
					logger.debug("statement object created");
					statement.setString(1, patient.getName());
					statement.setString(2, patient.getGender());
					statement.setLong(3, patient.getPhoneNumber());
					statement.setString(4, patient.getProblem());
					statement.executeUpdate();
					
					logger.info("execute update called");
		
					statement = connection.prepareStatement(QueryMapper.getpatientId);
					logger.info("statement created to getId");
					resultSet = statement.executeQuery();
					logger.info("result object get created");
					resultSet.next();
					generatedId = resultSet.getInt(1);
					logger.info("generated id is: " + generatedId);
				} catch (SQLException e) {
					logger.error(e.getMessage());
					throw new HMSException("problem occured while creating the statement object");
				}
					
				finally {
					logger.info("in finally block");
				}
					try {
						resultSet.close();
						logger.info("resultset closed");
					}catch (SQLException e) {
						throw new HMSException("problem occured while closing resultset");
					}
					try {
						statement.close();
						logger.info("statement closed");
					}catch (SQLException e) {
						logger.error(e.getMessage());
					throw new HMSException("problem occured while closing statement");
					}
					try {
					connection.close();
					logger.info("connection closed");
					}
					catch (SQLException e) {
						logger.error(e.getMessage());
						throw new HMSException("problem occured while closing connection");
					}
				
		return generatedId;
	}
}
			
