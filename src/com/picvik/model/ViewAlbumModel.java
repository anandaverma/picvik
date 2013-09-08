package com.picvik.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.picvik.model.ViewPhoto; 
import com.picvik.util.MyLog;
import com.picvik.util.RuntimeSettings;

public class ViewAlbumModel {
	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
    
	public Integer getAlbumCount(Integer uid) {
		Integer totalalbum = 0;
		try {
			String qstring = "SELECT count(*) As totalalbum FROM picvik_picture_album WHERE " +
					"uid = '" + uid + "';";
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	totalalbum = resultSet.getInt("totalalbum");
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return totalalbum;
	}
	
	public Integer getPhotoCount(Integer uid) {
		Integer totalphoto = 0;
		try {
			String qstring = "SELECT count(*) As totalphotos FROM picvik_picture WHERE " +
					"uid = '" + uid + "';";
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	totalphoto = resultSet.getInt("totalphotos");
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return totalphoto;
	}

	public ArrayList getAllAlbums(Integer uid) {
		ArrayList<ViewAlbum>allAlbums = new ArrayList<ViewAlbum>();
		try {
			/*String qstring = "SELECT albumid, albumname, description, location," +
					" date, uid FROM picvik_picture_album WHERE " +
					"uid = '" + uid + "';";
					*/
			
			String qstring = "SELECT album.*, pic.totalphotos FROM " +
					"picvik_picture_album as album , " +
					"(SELECT picture.albumid as id, count(*) as totalphotos FROM" +
					" picvik_picture as picture WHERE " +
					"picture.uid = '" + uid + "' group by picture.albumid) as pic WHERE " +
							"pic.id = album.albumid AND album.uid = '" + uid + "';"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
        		ViewAlbum album = new  ViewAlbum();
                album.setAlbumid(resultSet.getInt("albumid"));
                album.setAlbumname(resultSet.getString("albumname"));
                album.setDescription(resultSet.getString("description"));
                album.setLocation(resultSet.getString("location"));
                album.setDate(resultSet.getDate("date"));
                album.setUid(resultSet.getInt("uid"));
                album.setTotalphotos(resultSet.getInt("totalphotos"));
                allAlbums.add(album);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return allAlbums;
	}

	public ArrayList getAllPhotos(Integer uid) {
		ArrayList<ViewPhoto>photos = new ArrayList<ViewPhoto>();
		
		try {
			String qstring = "SELECT picturealbum. * , user.name, user.img " +
					"FROM ( SELECT picture. * , album.albumname, album.description AS" +
					" 'albumdescription', album.location, album.date " +
					"FROM picvik_picture AS picture, picvik_picture_album AS album " +
					"WHERE picture.albumid = album.albumid AND picture.uid = '" + uid + "' " +
							") AS picturealbum, picvik_userprofile AS user WHERE" +
							" picturealbum.uid = user.uid;"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewPhoto allphotos = new ViewPhoto();
            	allphotos.setPictureid(resultSet.getInt("pictureid"));
            	allphotos.setTitle(resultSet.getString("title"));
            	allphotos.setDescription(resultSet.getString("description"));
            	allphotos.setPhotourl(resultSet.getString("pictureurl"));
            	allphotos.setAlbumid(resultSet.getInt("albumid"));
            	allphotos.setUid(resultSet.getInt("uid"));
            	allphotos.setAlbumname(resultSet.getString("albumname"));
            	allphotos.setAlbumdescription(resultSet.getString("albumdescription"));
            	allphotos.setAlbumlocation(resultSet.getString("location"));
            	allphotos.setAlbumdate(resultSet.getDate("date"));
            	allphotos.setUsername(resultSet.getString("name"));
            	allphotos.setUserimgurl(resultSet.getString("img"));
                photos.add(allphotos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return photos;
	}

	public ArrayList getAlbumPhotos(Integer uid, Integer albumid) {
		ArrayList<ViewPhoto>photos = new ArrayList<ViewPhoto>();
		
		try {
			String qstring = "SELECT * FROM picvik_picture WHERE " +
					"uid = '" + uid + "' AND albumid = '" + albumid + "';"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewPhoto allphotos = new ViewPhoto();
            	allphotos.setPictureid(resultSet.getInt("pictureid"));
            	allphotos.setTitle(resultSet.getString("title"));
            	allphotos.setDescription(resultSet.getString("description"));
            	allphotos.setPhotourl(resultSet.getString("pictureurl"));
            	allphotos.setAlbumid(resultSet.getInt("albumid"));
            	allphotos.setUid(resultSet.getInt("uid"));
                photos.add(allphotos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return photos;
	}

	public ArrayList getAlbumsDetail(Integer uid, Integer albumid) {
		ArrayList<ViewAlbum>allAlbums = new ArrayList<ViewAlbum>();
		try {
			/*String qstring = "SELECT albumid, albumname, description, location," +
					" date, uid FROM picvik_picture_album WHERE " +
					"uid = '" + uid + "';";
					*/
			
			String qstring = "SELECT album.*, pic.totalphotos FROM " +
					"picvik_picture_album as album , " +
					"(SELECT picture.albumid as id, count(*) as totalphotos FROM" +
					" picvik_picture as picture WHERE " +
					"picture.uid = '" + uid + "' group by picture.albumid) as pic WHERE " +
							"pic.id = album.albumid AND album.albumid = '" + albumid + "';"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
        		ViewAlbum album = new  ViewAlbum();
                album.setAlbumid(resultSet.getInt("albumid"));
                album.setAlbumname(resultSet.getString("albumname"));
                album.setDescription(resultSet.getString("description"));
                album.setLocation(resultSet.getString("location"));
                album.setDate(resultSet.getDate("date"));
                album.setUid(resultSet.getInt("uid"));
                album.setTotalphotos(resultSet.getInt("totalphotos"));
                allAlbums.add(album);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return allAlbums;
	}

	public ArrayList getAlbumcomments(Integer albumid, Integer mediaAlbum) {
		ArrayList<ViewComment>comments = new ArrayList<ViewComment>();
		
		try {
			String qstring = "SELECT comment. * , user.name, user.img FROM picvik_comment AS " +
					" comment, (SELECT name, img, uid FROM picvik_userprofile ) AS user " +
					"WHERE user.uid = comment.uid AND mediaid = '" + albumid + "' AND " +
							"mediatype = '" + mediaAlbum + "';"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewComment allcomments = new ViewComment();
            	allcomments.setCommentid(resultSet.getInt("commentid"));
            	allcomments.setComment(resultSet.getString("comment"));
            	allcomments.setDatetime(resultSet.getTimestamp("datetime"));
            	allcomments.setMediaid(resultSet.getInt("mediaid"));
            	allcomments.setMediatype(resultSet.getInt("mediatype"));
            	allcomments.setUid(resultSet.getInt("uid"));
            	allcomments.setName(resultSet.getString("name"));
            	allcomments.setImgurl(resultSet.getString("img"));
                comments.add(allcomments);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return comments;
	}

	public ArrayList getPhoto(Integer uid,
			String pictureid) {
		ArrayList<ViewPhoto>photos = new ArrayList<ViewPhoto>();
		try {
			String qstring = "SELECT picturealbum. * , user.name, user.img " +
					"FROM ( SELECT picture. * , album.albumname, album.description AS" +
					" 'albumdescription', album.location, album.date " +
					"FROM picvik_picture AS picture, picvik_picture_album AS album " +
					"WHERE picture.albumid = album.albumid AND picture.uid = '" + uid + "' AND " +
							"picture.pictureid = '" + pictureid +
							"') AS picturealbum, picvik_userprofile AS user WHERE" +
							" picturealbum.uid = user.uid;"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewPhoto allphotos = new ViewPhoto();
            	allphotos.setPictureid(resultSet.getInt("pictureid"));
            	allphotos.setTitle(resultSet.getString("title"));
            	allphotos.setDescription(resultSet.getString("description"));
            	allphotos.setPhotourl(resultSet.getString("pictureurl"));
            	allphotos.setAlbumid(resultSet.getInt("albumid"));
            	allphotos.setUid(resultSet.getInt("uid"));
            	allphotos.setAlbumname(resultSet.getString("albumname"));
            	allphotos.setAlbumdescription(resultSet.getString("albumdescription"));
            	allphotos.setAlbumlocation(resultSet.getString("location"));
            	allphotos.setAlbumdate(resultSet.getDate("date"));
            	allphotos.setUsername(resultSet.getString("name"));
            	allphotos.setUserimgurl(resultSet.getString("img"));
                photos.add(allphotos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return photos;
	}

	public ArrayList getPhotocomments(String pictureid,
			Integer mediaPhoto) {
		ArrayList<ViewComment>comments = new ArrayList<ViewComment>();
		
		try {
			String qstring = "SELECT comment. * , user.name, user.img FROM picvik_comment AS " +
					" comment, (SELECT name, img, uid FROM picvik_userprofile ) AS user " +
					"WHERE user.uid = comment.uid AND mediaid = '" + pictureid + "' AND " +
							"mediatype = '" + mediaPhoto + "';"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewComment allcomments = new ViewComment();
            	allcomments.setCommentid(resultSet.getInt("commentid"));
            	allcomments.setComment(resultSet.getString("comment"));
            	allcomments.setDatetime(resultSet.getTimestamp("datetime"));
            	allcomments.setMediaid(resultSet.getInt("mediaid"));
            	allcomments.setMediatype(resultSet.getInt("mediatype"));
            	allcomments.setUid(resultSet.getInt("uid"));
            	allcomments.setName(resultSet.getString("name"));
            	allcomments.setImgurl(resultSet.getString("img"));
                comments.add(allcomments);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return comments;
	}

	public Integer getAllPublicAlbumCount() {
		Integer totalpublicalbum = 0;
		try {
			String qstring = "SELECT count(*) As totalpublicalbum FROM picvik_picture_album " +
					"WHERE privacy = '" + RuntimeSettings.mediaPublic + "';";
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	totalpublicalbum = resultSet.getInt("totalpublicalbum");
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return totalpublicalbum;
	}

	public Integer getAllPublicPhotoCount() {
		Integer totalpublicphoto = 0;
		try {
			String qstring = "SELECT count(*) As totalpublicphotos FROM picvik_picture " +
					"WHERE privacy = '" + RuntimeSettings.mediaPublic + "';";
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	totalpublicphoto = resultSet.getInt("totalpublicphotos");
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return totalpublicphoto;
	}

	public ArrayList getAllPublicAlbums() {
		ArrayList<ViewAlbum>allPublicAlbums = new ArrayList<ViewAlbum>();
		try {
			/*String qstring = "SELECT albumid, albumname, description, location," +
					" date, uid FROM picvik_picture_album WHERE " +
					"uid = '" + uid + "';";
					*/
			
			String qstring = "SELECT album.*, pic.totalphotos FROM " +
					"picvik_picture_album as album , " +
					"(SELECT picture.albumid as id, count(*) as totalphotos FROM" +
					" picvik_picture as picture WHERE " +
					"picture.privacy = '" + RuntimeSettings.mediaPublic + "' group by picture.albumid) as pic WHERE " +
							"pic.id = album.albumid AND album.privacy = '" + RuntimeSettings.mediaPublic +"';"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
        		ViewAlbum album = new  ViewAlbum();
                album.setAlbumid(resultSet.getInt("albumid"));
                album.setAlbumname(resultSet.getString("albumname"));
                album.setDescription(resultSet.getString("description"));
                album.setLocation(resultSet.getString("location"));
                album.setDate(resultSet.getDate("date"));
                album.setUid(resultSet.getInt("uid"));
                album.setTotalphotos(resultSet.getInt("totalphotos"));
                allPublicAlbums.add(album);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return allPublicAlbums;
	}

	public ArrayList getAllPublicPhotos() {
		ArrayList<ViewPhoto>publicphotos = new ArrayList<ViewPhoto>();
		
		try {
			String qstring = "SELECT picturealbum. * , user.name, user.img " +
					"FROM ( SELECT picture. * , album.albumname, album.description AS" +
					" 'albumdescription', album.location, album.date " +
					"FROM picvik_picture AS picture, picvik_picture_album AS album " +
					"WHERE picture.albumid = album.albumid AND picture.privacy = '" + RuntimeSettings.mediaPublic + "' " +
							") AS picturealbum, picvik_userprofile AS user WHERE" +
							" picturealbum.uid = user.uid;"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewPhoto allphotos = new ViewPhoto();
            	allphotos.setPictureid(resultSet.getInt("pictureid"));
            	allphotos.setTitle(resultSet.getString("title"));
            	allphotos.setDescription(resultSet.getString("description"));
            	allphotos.setPhotourl(resultSet.getString("pictureurl"));
            	allphotos.setAlbumid(resultSet.getInt("albumid"));
            	allphotos.setUid(resultSet.getInt("uid"));
            	allphotos.setAlbumname(resultSet.getString("albumname"));
            	allphotos.setAlbumdescription(resultSet.getString("albumdescription"));
            	allphotos.setAlbumlocation(resultSet.getString("location"));
            	allphotos.setAlbumdate(resultSet.getDate("date"));
            	allphotos.setUsername(resultSet.getString("name"));
            	allphotos.setUserimgurl(resultSet.getString("img"));
                publicphotos.add(allphotos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return publicphotos;
	}

	public ArrayList getPublicPhoto(String pictureid) {
		ArrayList<ViewPhoto>photos = new ArrayList<ViewPhoto>();
		try {
			String qstring = "SELECT picturealbum. * , user.name, user.img " +
					"FROM ( SELECT picture. * , album.albumname, album.description AS" +
					" 'albumdescription', album.location, album.date " +
					"FROM picvik_picture AS picture, picvik_picture_album AS album " +
					"WHERE picture.albumid = album.albumid AND picture.privacy = '" + RuntimeSettings.mediaPublic + "' AND " +
							"picture.pictureid = '" + pictureid +
							"') AS picturealbum, picvik_userprofile AS user WHERE" +
							" picturealbum.uid = user.uid;"; 
			MyLog.log(qstring);
			//System.out.println;
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewPhoto allphotos = new ViewPhoto();
            	allphotos.setPictureid(resultSet.getInt("pictureid"));
            	allphotos.setTitle(resultSet.getString("title"));
            	allphotos.setDescription(resultSet.getString("description"));
            	allphotos.setPhotourl(resultSet.getString("pictureurl"));
            	allphotos.setAlbumid(resultSet.getInt("albumid"));
            	allphotos.setUid(resultSet.getInt("uid"));
            	allphotos.setAlbumname(resultSet.getString("albumname"));
            	allphotos.setAlbumdescription(resultSet.getString("albumdescription"));
            	allphotos.setAlbumlocation(resultSet.getString("location"));
            	allphotos.setAlbumdate(resultSet.getDate("date"));
            	allphotos.setUsername(resultSet.getString("name"));
            	allphotos.setUserimgurl(resultSet.getString("img"));
                photos.add(allphotos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return photos;
	}

	public ArrayList getPublicAlbumsDetail(Integer albumid) {
		ArrayList<ViewAlbum>allAlbums = new ArrayList<ViewAlbum>();
		try {
			/*String qstring = "SELECT albumid, albumname, description, location," +
					" date, uid FROM picvik_picture_album WHERE " +
					"uid = '" + uid + "';";
					*/
			
			String qstring = "SELECT album.*, pic.totalphotos FROM " +
					"picvik_picture_album as album , " +
					"(SELECT picture.albumid as id, count(*) as totalphotos FROM" +
					" picvik_picture as picture WHERE " +
					"picture.privacy = '" + RuntimeSettings.mediaPublic + "' group by picture.albumid) as pic WHERE " +
							"pic.id = album.albumid AND album.albumid = '" + albumid + "';"; 
			MyLog.log(qstring);
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
        		ViewAlbum album = new  ViewAlbum();
                album.setAlbumid(resultSet.getInt("albumid"));
                album.setAlbumname(resultSet.getString("albumname"));
                album.setDescription(resultSet.getString("description"));
                album.setLocation(resultSet.getString("location"));
                album.setDate(resultSet.getDate("date"));
                album.setUid(resultSet.getInt("uid"));
                album.setTotalphotos(resultSet.getInt("totalphotos"));
                allAlbums.add(album);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return allAlbums;
	}

	public ArrayList getPublicAlbumPhotos(Integer albumid) {
		ArrayList<ViewPhoto>photos = new ArrayList<ViewPhoto>();
		
		try {
			String qstring = "SELECT * FROM picvik_picture WHERE " +
					"albumid = '" + albumid + "' AND privacy = '" + 
					RuntimeSettings.mediaPublic + "';"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewPhoto allphotos = new ViewPhoto();
            	allphotos.setPictureid(resultSet.getInt("pictureid"));
            	allphotos.setTitle(resultSet.getString("title"));
            	allphotos.setDescription(resultSet.getString("description"));
            	allphotos.setPhotourl(resultSet.getString("pictureurl"));
            	allphotos.setAlbumid(resultSet.getInt("albumid"));
            	allphotos.setUid(resultSet.getInt("uid"));
                photos.add(allphotos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return photos;
	}

	public void deletePhoto(Integer mediaid) {
			try{
	            String queryString = "DELETE FROM picvik_picture " +
	                                 "WHERE pictureid = " + mediaid +" ;";
	            
	            connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
	            ptmt = connection.prepareStatement(queryString);
	            ptmt.executeUpdate();
	            
	            ptmt.close();
	            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
