package com.picvik.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.picvik.util.MyLog;

public class CommentModel {
	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    
	public void addComment(String comment, Integer mediaid, Integer mediatype,
			Integer uid) {
		try {
    		
    		//query string 
    		String qstring = "INSERT INTO picvik_comment (comment, mediaid, mediatype, uid) " +
    				"VALUES('" + comment +  "'," + mediaid + ", " + mediatype +", " + uid +  ");";
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

}
