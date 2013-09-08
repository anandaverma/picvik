package com.picvik.model;

import com.picvik.util.MyLog;
import com.picvik.util.MyUtilityFunctions;
import com.picvik.util.RuntimeSettings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterModel {
	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    
    //Insert into picvik_user table 
    //uid, utype, regtime, status is automatically inserted 
    //utype has default value 1 for general user
    //status has default value 0 for inactive user
    public String registerUser(String username, String email, String password ){
    	String ret = "success";
    	try {
    		password = MyUtilityFunctions.generateMD5(password);
    		
    		//query string 
    		String qstring = "INSERT INTO picvik_user (uname, email, password) " +
    				"VALUES('" + username + "', '" + email + "', '" +
                 password +  "');";
    		MyLog.log(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            //ptmt.setString(1, username);
            //ptmt.setString(2, email);
            //ptmt.setString(3, password);
            ptmt.executeUpdate(qstring);
            //System.out.println(username);
            //System.out.println(qstring);
            ptmt.close();
            connection.close();
		
    	} catch (Exception e) {
    		ret = "error";
		e.printStackTrace();
    	}// end catch
    	return ret;
    }

    //Insert into picvik_user_activation table
	public String userActivation(String verificationurl, Integer uid) {
		String ret = "success";
		try {
			
			//query string
			String qstring = "INSERT INTO picvik_user_activation " +
    				"VALUES('" + verificationurl + "', " + uid + ")";
			//System.out.println(qstring);
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            
            ptmt.executeUpdate(qstring);
            
            ptmt.close();
            connection.close();
            
            
			
		} catch (Exception e) {
			ret =  "error";
			e.printStackTrace();
		}
		return ret;
		
	}

	//returns uid if given username found or returns -1
	public int getUID(String username) {
		int uid = -1; 
		try {
			
			//query string
			String qstring = "SELECT uid FROM picvik_user WHERE uname = '" + username + "'";
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	uid = resultSet.getInt("uid");
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uid;
	}

	//return activationkey given uid from picvik_user_activation table if found or return ""
	public String getKey(int uid) {
		
		String key = "";
		
		//query string
		try {
			String qstring = "SELECT activationKey FROM picvik_user_activation WHERE uid = '" + uid + "'";
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            //System.out.println(qstring);
            resultSet = ptmt.executeQuery();
            
            if(resultSet.next()) {
            	key = resultSet.getString("activationKey");
            }
            ptmt.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	//return email given uid from picvik_user if found or return ""
	public String getEmail(int uid) {
		String mail = "";
		try {
			String qstring = "SELECT email FROM picvik_user WHERE uid = '" + uid + "'";
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            //System.out.println(qstring);
            resultSet = ptmt.executeQuery();
            
            if(resultSet.next()) {
            	mail = resultSet.getString("email");
            }
            ptmt.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mail;
	}
	
	//check whether email exists 
	public String checkEmail(String email) {
		String mail = "";
		try {
			String qstring = "SELECT email FROM picvik_user WHERE email = '" + email + "'";
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            //System.out.println(qstring);
            resultSet = ptmt.executeQuery();
            
            if(resultSet.next()) {
            	mail = resultSet.getString("email");
            }
            ptmt.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mail;
	}
	
	//check whether username exists	
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

	//update user status to picvik_user table
	public void updateUserStatus(int uid, int statusID) {
		
		try {
			String qstring = "UPDATE picvik_user " +
    				"SET status = " + statusID + " WHERE uid = '" + uid + "'";
			//System.out.println(qstring);
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            
            ptmt.executeUpdate(qstring);
            
            ptmt.close();
            connection.close();
            
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	//delete from picvik_user_activation given uid
	public void deleteUserActivationEntry(int uid) {
		
		try {
			String qstring = "DELETE FROM picvik_user_activation " + 
					" WHERE uid = '" + uid + "'";
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
       
            ptmt.executeUpdate(qstring);
            ptmt.close();
            connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
