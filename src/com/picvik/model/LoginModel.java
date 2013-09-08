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

public class LoginModel {
	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    
    public String checkUsername(String uname) {
		String username = "";
		try {
			String qstring = "SELECT uname FROM picvik_user WHERE uname = '" + uname + "'";
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            //System.out.println(qstring);
            resultSet = ptmt.executeQuery();
            
            if(resultSet.next()) {
            	username = resultSet.getString("uname");
            }
            ptmt.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}
    
    public String getPassword(String uname) {
		String password = "";
		try {
			String qstring = "SELECT password FROM picvik_user WHERE uname = '" + uname + "'";
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            //System.out.println(qstring);
            resultSet = ptmt.executeQuery();
            
            if(resultSet.next()) {
            	password = resultSet.getString("password");
            }
            ptmt.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}  
    
    public Integer getUserStatus(String uname) {
		int status = 0;
		try {
			String qstring = "SELECT status FROM picvik_user WHERE uname = '" + uname + "'";
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            //System.out.println(qstring);
            resultSet = ptmt.executeQuery();
            
            if(resultSet.next()) {
            	status = resultSet.getInt("status");
            }
            ptmt.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}  
}
