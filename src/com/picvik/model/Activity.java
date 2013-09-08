package com.picvik.model;

import java.sql.*;
import java.util.ArrayList;

import com.picvik.util.MyLog;

public class Activity {
	
	// DB related private variables
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
	// Private Variables
	
	private int user_id;
	private int activity_type_id;
	private int activity_source_id;
	private int activity_source_type_id;
	private String data;
	
	// Getters and Setters
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getActivity_type_id() {
		return activity_type_id;
	}

	public void setActivity_type_id(int activity_type_id) {
		this.activity_type_id = activity_type_id;
	}

	public int getActivity_source_id() {
		return activity_source_id;
	}

	public void setActivity_source_id(int activity_source_id) {
		this.activity_source_id = activity_source_id;
	}

	public int getActivity_source_type_id() {
		return activity_source_type_id;
	}

	public void setActivity_source_type_id(int activity_source_type_id) {
		this.activity_source_type_id = activity_source_type_id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	// Methods
	public boolean insert_activity(){
		
		boolean status = true;
		String queryString = "INSERT INTO picvik_activity " +
				"(user_id, activity_type_id, activity_source_id, activity_source_type_id, data) " +
				"VALUES (" + this.user_id + ", " + this.activity_type_id +", "+this.activity_source_id+", "+this.activity_source_type_id+", '"+this.data+"');";
		
		MyLog.log(queryString);
		try{
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
			
			preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
			
		}catch(Exception ex){
			preparedStatement=null;
			connection = null;
			ex.printStackTrace();
			status = false;
			
			System.out.println("In Activity Model : insert activity");
		}
		
		return status; 
	}
	

	/*
	 * Get all ActivityFeeds as a list of "ActivityFeedInformation".
	 * Returns: List of "ActivityFeedInformation".
	 * 			Empty List if no activity exists.
	 */
	public ArrayList<ActivityFeedInformation> getActivityFeeds(){
				
		ArrayList<ActivityFeedInformation> activityFeeds = new ArrayList<ActivityFeedInformation>();
		
		try{
			String getActivityQuery = "SELECT * FROM picvik_activity ORDER BY timestamp DESC LIMIT 25;";
			MyLog.log(getActivityQuery);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
			
			preparedStatement = connection.prepareStatement(getActivityQuery);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				ActivityFeedInformation tempActivityInformation = new ActivityFeedInformation();
				
				// Step 1: Get and set user id and User Name.
				int uid = resultSet.getInt("user_id");
				tempActivityInformation.setUid(uid);
				tempActivityInformation.setUname(UserModel.getUserNameFromID(uid));
				
				// Step 2: Set Activity Date and Time
				tempActivityInformation.setActivityDate(resultSet.getDate("timeStamp"));
				
				// Step 3: Set Activity Type ID. (See "ActivityType" class).
				int activitySourceTypeId = resultSet.getInt("activity_source_type_id");
				tempActivityInformation.setActivityType(activitySourceTypeId);
				
				// Step 3: Based on Activity type get and set activity data(Ex: url of photo, video etc..).
				
				String activityData = "";
								
				int activitySourceId = resultSet.getInt("activity_source_id");
				
				// TODO : SANDEEP -> Refactor and restructure code by Anand on photo model, video model.
				// Getting photo url by photoid, video url by video id, etc... should be part of photo and video model only.
				if(activitySourceTypeId == ActivitySourceType.get_Picture_type_id()){

					// Activity is uploading of new picture, so query picture table.
					String getPictureURLQuery = "SELECT pictureurl FROM picvik_picture " +
										   "WHERE pictureid = " + activitySourceId +" ;";
					MyLog.log(getPictureURLQuery);
					preparedStatement = connection.prepareStatement(getPictureURLQuery);
					ResultSet pictureResultSet = preparedStatement.executeQuery();
					if(pictureResultSet.next()){
						activityData = pictureResultSet.getString("pictureurl");
					}
					
				}else if(activitySourceTypeId == ActivitySourceType.get_Picture_album_type_id()){
					// Activity is Creation of new photo album, so query album table.
					String getPictureAlbumNameQuery = "SELECT albumname FROM picvik_picture_album " +
										   "WHERE albumid = " + activitySourceId +" ;";
					MyLog.log(getPictureAlbumNameQuery);
					preparedStatement = connection.prepareStatement(getPictureAlbumNameQuery);
					ResultSet albumResultSet = preparedStatement.executeQuery();
					if(albumResultSet.next()){
						activityData = albumResultSet.getString("albumname");
					}
				}
				else if(activitySourceTypeId == ActivitySourceType.getNew_video()){
					// Activity is uploading a new video, so album table picture table.
					String getVideoURLQuery = "SELECT videourl FROM picvik_video " +
										   "WHERE videoid = " + activitySourceId +" ;";
					MyLog.log(getVideoURLQuery);
					preparedStatement = connection.prepareStatement(getVideoURLQuery);
					ResultSet videoResultSet = preparedStatement.executeQuery();
					if(videoResultSet.next()){
						activityData = videoResultSet.getString("videourl");
					}
				}
				else if(activitySourceTypeId == ActivitySourceType.getNew_video_channel()){
					// Activity is Creation of new video channel, so query video channel table.
					String getVideoChannelNameQuery = "SELECT channelname FROM picvik_video_channel " +
										   "WHERE channelid = " + activitySourceId +" ;";
					MyLog.log(getVideoChannelNameQuery);
					preparedStatement = connection.prepareStatement(getVideoChannelNameQuery);
					ResultSet videoChannelResultSet = preparedStatement.executeQuery();
					if(videoChannelResultSet.next()){
						activityData = videoChannelResultSet.getString("channelname");
					}
				}
				
				tempActivityInformation.setActivityData(activityData);
				
				// Step:5 - Get and Set User Profile Pic Url.
				// TODO: Sandeep -> Refactor code of "ProfileModel" class. It should have only static methods.
				ViewProfileModel profileModel = new ViewProfileModel();
				String userProfilePicUrl = profileModel.getProfilePic(uid);
				tempActivityInformation.setUserProfilePicUrl(userProfilePicUrl);
				
				// Step:4 - Add a activityFeedInformation to list.
				activityFeeds.add(tempActivityInformation);
			}
			
			connection.close();
			preparedStatement.close();
			resultSet.close();
			
		}catch(Exception ex){
			connection = null;
			preparedStatement = null;
			resultSet = null;
			
			ex.printStackTrace();
		}
		
		return activityFeeds;
	}
}