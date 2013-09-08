package com.picvik.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.picvik.util.MyLog;

public class MyAcitivityModel {
	// DB related private variables
		private Connection connection = null;
	    private PreparedStatement preparedStatement = null;
	    private ResultSet resultSet = null;
	    
	    public ArrayList getActivityFeeds(){
			
			ArrayList<MyActivity> activityFeeds = new ArrayList<MyActivity>();
			
			String query = "SELECT * FROM picvik_activity ORDER BY timestamp LIMIT 25;";
			MyLog.log(query);
			try{
				connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
				
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()){
					MyActivity activity = new MyActivity();
					
					
					activityFeeds.add(activity);
				}
				
				preparedStatement.close();
				connection.close();
				
			}catch(Exception ex){
				ex.printStackTrace();
				
				resultSet = null;
				preparedStatement = null;
				connection = null;
			}
			
			return activityFeeds;
		}
	
}
