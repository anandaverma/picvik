//sandeep's
package com.picvik.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.picvik.util.MyLog;
import com.picvik.util.RuntimeSettings;

public class UserModel {
	
	private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    
    /*
     * Get UserName from user id.
     * Returns : -> Valid "UserName" if uid is valid.
     * 			 -> "", empty string if uid is invalid. 
     */
    public static String getUserNameFromID(int uid){
    	String uName = "";
    	try{
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
    		
    		String query = "SELECT uname FROM picvik_user WHERE uid = " + uid + " ;";
    		MyLog.log(query);
    		preparedStatement = connection.prepareStatement(query);
    		resultSet = preparedStatement.executeQuery();
    		if(resultSet.next())
    			uName = resultSet.getString("uname");
    		
    		preparedStatement.close();
    		connection.close();
    		resultSet.close();
    		
    	}catch(Exception ex){
    		preparedStatement = null;
    		resultSet = null;
    		connection = null;
    		
    		System.out.println("In User Model : getUserNameFromID");
    		ex.printStackTrace();    		
    	}
    	
    	return uName;    	
    }
    
    /*
     * Get User Existing Details.
     * Returns : List of "ViewProfile" objects.
     */
    public static ArrayList<UserInformation> GetAllUserInfo(){
    	
    	ArrayList<UserInformation> allUserProfiles = new ArrayList<UserInformation>();
    	
    	
    	try{
    		String queryString = "SELECT * " +
    							 "FROM picvik_user, picvik_userprofile " +
    							 "WHERE picvik_user.uid = picvik_userprofile.uid"; 
    		MyLog.log(queryString);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
    		preparedStatement = connection.prepareStatement(queryString);
    		resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()){
    			UserInformation tempUserInfo = new UserInformation();
    			/*
    			 * NOTE: not all details are pulled, only currently required data are pulled.
    			 * If in future we require more details, update below code by approriately
    			 * initializing "tempUserInfo" by value extracted from "resultSet". 
    			 */

    			// Few important details of User table
    			tempUserInfo.setUid(resultSet.getInt("uid"));
    			tempUserInfo.setUname(resultSet.getString("uname"));
    			tempUserInfo.setEmail(resultSet.getString("email"));
    			tempUserInfo.setPassword(resultSet.getString("password"));
    			tempUserInfo.setStatus(resultSet.getInt("status"));
    			
    			// Few important details of User Profile table.
    			tempUserInfo.setImg(resultSet.getString("img"));    			
    			tempUserInfo.setDob(resultSet.getDate("dob"));
    			tempUserInfo.setName(resultSet.getString("name"));
    			
    			// Add to our list of user informations.
    			allUserProfiles.add(tempUserInfo);
    		}
    		
    		preparedStatement.close();
    		resultSet.close();
    		connection.close();  		
    		
    	}catch(Exception ex){
    		
    		preparedStatement = null;
    		resultSet = null;
    		connection = null;
    		
    		ex.printStackTrace();
    	}    	
    	
    	return allUserProfiles;
    }    
    /*
     * Remove a user, provided by his user id.
     * Returns : true - successfully removed user.
     *              false - unable to remove user.
     */
    public static boolean RemoveUser(int uid){
        
        boolean result = true;
        
        try{
            String queryString = "DELETE FROM picvik_user " +
                                 "WHERE uid = " + uid +" ;";
            MyLog.log(queryString);
            connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            resultSet.close();
            connection.close();  
            
        }catch(Exception ex){
            result = false;
            
            preparedStatement = null;
            resultSet = null;
            connection = null;
            
            ex.printStackTrace();
        }
        
        return result;
    }
    
    /*
     * Ban a user by changing status field, provided his user id
     * Returns: True - on successful banning.
     *             False - On Unsuccessful banning.
     */
    public static boolean BanUser(int uid){
        
        boolean result = true;
        
        try{
            String queryString = "UPDATE picvik_user SET status = " + RuntimeSettings.statusBanned + 
                                 " WHERE uid = " + uid +" ;";
            MyLog.log(queryString);
            connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            resultSet.close();
            connection.close();  
            
        }catch(Exception ex){
            result = false;
            
            preparedStatement = null;
            resultSet = null;
            connection = null;
            
            ex.printStackTrace();
        }
        
        return result;
    }

	public void AllowUser(Integer uid) {
		try{
            String queryString = "UPDATE picvik_user SET status = " + RuntimeSettings.statusActive + 
                                 " WHERE uid = " + uid +" ;";
            MyLog.log(queryString);
            connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            resultSet.close();
            connection.close();  
            
        }catch(Exception ex){
            
            preparedStatement = null;
            resultSet = null;
            connection = null;
            
            ex.printStackTrace();
        }
        
	}

	public void ActivateUser(Integer uid) {
		try{
            String queryString = "UPDATE picvik_user SET status = " + RuntimeSettings.statusActive + 
                                 " WHERE uid = " + uid +" ;";
            MyLog.log(queryString);
            connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            resultSet.close();
            connection.close();  
            
        }catch(Exception ex){
            
            preparedStatement = null;
            resultSet = null;
            connection = null;
            
            ex.printStackTrace();
        }
        
	}
}
