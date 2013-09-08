package com.picvik.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.picvik.util.MyLog;

public class UploadVideoModel {
	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;

	public void createVideoChannel(String channelname, String channeldesc,
			String location, Date takendate, Integer channelprivacy, Integer uid) {
		try {
			String qstring = "INSERT INTO picvik_video_channel (channelname, " +
					"description, location, date, privacy, uid) " +
    				"VALUES('" + channelname + "', '" + channeldesc + "', '" 
					+ location + "', '" + takendate + "', '" + channelprivacy + "', '" + uid +"');";
			MyLog.log(qstring);
			System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            
            ptmt.executeUpdate(qstring);
            
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

	public Integer getVideoChannelID(String channelname, Integer uid) {
		Integer channelid = 0;
		try {
			String qstring = "SELECT channelid FROM picvik_video_channel WHERE " +
					"channelname = '" + channelname + "' AND uid = '" + uid + "';";
			MyLog.log(qstring);
			System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	channelid = resultSet.getInt("channelid");
            }
            
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return channelid;
	}

	public void insertVideos(String title, String desc, String url,
			Integer videoprivacy, Integer videoid, Integer uid) {
		try {
			String qstring = "INSERT INTO picvik_video (title, " +
					"description, videourl, privacy, channelid, uid) " +
    				"VALUES('" + title + "', '" + desc + "', '" 
					+ url + "', '" + videoprivacy + "', '" + videoid + "', '" + uid + "');";
			MyLog.log(qstring);
			System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            
            ptmt.executeUpdate(qstring);
            
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Get video id given its name and uid.
	 */
	public int getVideoID(String videoName, Integer uid) {
			
			int videoId = 0;
			try {
				String qstring = "SELECT videoid FROM picvik_video WHERE " +
						"title = '" + videoName + "' AND uid = '" + uid + "';";
				MyLog.log(qstring);
	    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
	            ptmt = connection.prepareStatement(qstring);
	            resultSet = ptmt.executeQuery();
	            if(resultSet.next()) {
	            	videoId = resultSet.getInt("videoid");
	            }
	            
	            ptmt.close();
	            connection.close();        
				
			} catch (Exception e) {
				//System.out.println("In Upload Video Model : getVideoID");
				e.printStackTrace();
			}		
			
			return videoId;
		}
}
