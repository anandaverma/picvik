package com.picvik.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.picvik.util.MyLog;
import com.picvik.util.MyUtilityFunctions;
import com.picvik.util.RuntimeSettings;

public class ViewProfileModel {
	
	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    
	public ArrayList getProfile(Integer uid) {
		
		ArrayList<ViewProfile> userprofile = new ArrayList<ViewProfile>();
		
		try {
			String qstring = "SELECT * FROM picvik_userprofile WHERE uid = '" + uid + "'";
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            //System.out.println(qstring);
            resultSet = ptmt.executeQuery();
            
            if(resultSet.next()) {
            	ViewProfile profile = new ViewProfile(); 
            	profile.setImg(resultSet.getString("img"));
            	profile.setName(resultSet.getString("name"));
            	profile.setGender(resultSet.getString("gender"));
            	profile.setDob(resultSet.getDate("dob"));
            	profile.setAddress1(resultSet.getString("address1"));
            	profile.setAddress2(resultSet.getString("address2"));
            	profile.setCity(resultSet.getString("city"));
            	profile.setState(resultSet.getString("state"));
            	profile.setCountry(resultSet.getString("country"));
            	profile.setZip(resultSet.getString("zip"));
            	userprofile.add(profile);
            }
            ptmt.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userprofile;
	}
	
public String getProfilePic(Integer uid) {
		
		String imgurl=""; 
		try {
			String qstring = "SELECT img FROM picvik_userprofile WHERE uid = '" + uid + "'";
			MyLog.log(qstring);
			connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            //System.out.println(qstring);
            resultSet = ptmt.executeQuery();
            
            if(resultSet.next()) {
            	imgurl = resultSet.getString("img");
            	
            }
            ptmt.close();
            connection.close();
            resultSet.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgurl;
	}
	
	public void createDummyProfile(Integer uid){
		try {
    		
    		//query string 
    		String qstring = "INSERT INTO picvik_userprofile (uid, img) " +
    				"VALUES('" + uid +  "', 'ViewResources/images/noprofile.gif');";
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
		e.printStackTrace();
    	}// end catch
	}

	public void updateProfile(String name, String gender, Date dob,
			String address1, String address2, String city, String state,
			String country, String zip, Integer uid) {
		// TODO Auto-generated method stub
		
		try {
			String qstring = "UPDATE picvik_userprofile " +
    				"SET name = '" + name + "', gender = '" + gender + 
    				"', dob = '" + dob + "', address1 = '" + address1 + 
    				"', address2 = '" + address2 +
    				"', city = '" + city + "', state = '" + state +
    				"', country = '" + country + "', zip = '" + zip +
    				"' WHERE uid = '" + uid + "'";
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

	public void updateUserPhotoUrl(String imgUrl, Integer uid) {
		
		try {
			String qstring = "UPDATE picvik_userprofile " +
    				"SET img = '" + imgUrl + "' WHERE uid = '" + uid + "'";
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

}
