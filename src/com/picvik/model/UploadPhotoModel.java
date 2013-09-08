package com.picvik.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.picvik.util.MyLog;

public class UploadPhotoModel {
	
	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;

	public void insertPhotoURL(String photoUrl, Integer uid) {
		try {
			String qstring = "INSERT INTO picvik_picture (pictureurl, uid) " +
    				"VALUES('" + photoUrl + "', " + uid + ");";
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

	public void createPhotoAlbum(String albumname, String albumdesc,
			String location, Date takendate, Integer albumprivacy, Integer uid) {
		
		try {
			String qstring = "INSERT INTO picvik_picture_album (albumname, " +
					"description, location, date, privacy, uid) " +
    				"VALUES('" + albumname + "', '" + albumdesc + "', '" 
					+ location + "', '" + takendate + "', '" + albumprivacy + "', '" + uid +"');";
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
	
	public Integer getPhotoAlbumID(String albumname, Integer uid) {
		
		Integer albumid = 0;
		try {
			String qstring = "SELECT albumid FROM picvik_picture_album WHERE " +
					"albumname = '" + albumname + "' AND uid = '" + uid + "';";
			MyLog.log(qstring);
			System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	albumid = resultSet.getInt("albumid");
            }
            
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return albumid;
	}

	public void insertPhotos(String title, String desc, String url,
			Integer photoprivacy, Integer albumid, Integer uid) {
		try {
			String qstring = "INSERT INTO picvik_picture (title, " +
					"description, pictureurl, privacy, albumid, uid) " +
    				"VALUES('" + title + "', '" + desc + "', '" 
					+ url + "', '" + photoprivacy + "', '" + albumid + "', '" + uid + "');";
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
	
	//sandeep's function
	public Integer getPhotoID(String pictureName, Integer uid) {
		
		Integer pictureID = 0;
		try {
			String qstring = "SELECT pictureid FROM picvik_picture WHERE " +
					"title = '" + pictureName + "' AND uid = '" + uid + "';";
			MyLog.log(qstring);
			System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	pictureID = resultSet.getInt("pictureid");
            }
            
            ptmt.close();
            connection.close();        
			
		} catch (Exception e) {
			System.out.println("In Upload Photo Model : getPhotoID");
			e.printStackTrace();
		}		
		return pictureID;
	}
}
