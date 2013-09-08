package com.picvik.model;

import com.picvik.util.MyLog;
import com.picvik.util.RuntimeSettings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminModel {
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
public String getAdminName(String enteredName) {
		
		String adminName = "";
		
		try {
			String qstring = "SELECT uname FROM picvik_admin WHERE uname = '" + enteredName + "'";
			MyLog.log(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(qstring);
            
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
            	// Set adminstrator Name if it is valid else it will remain empty ""
            	adminName = resultSet.getString("uname");
            }
            
            preparedStatement.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			preparedStatement = null;
			resultSet = null;
			connection = null;
			
			e.printStackTrace();
		}
		
		return adminName;
	}
    
	/*
	 * Verify and return administrator password.
	 * Returns: 
	 * -> "" - empty string if adminstrator name doesnot exists.
	 * -> valid password if adminstrator name exists. 
	 */
    public String getAdminPassword(String adminName) {
		String password = "";
		
		try {
			String qstring = "SELECT password FROM picvik_admin WHERE uname = '" + adminName + "'";
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(qstring);
            
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
            	// Set adminstrator Password if it is valid else it will remain empty ""
            	password = resultSet.getString("password");
            }
            
            preparedStatement.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			
			preparedStatement = null;
			connection = null;
			resultSet = null;
			
			e.printStackTrace();
		}
		
		return password;
	}	
}
