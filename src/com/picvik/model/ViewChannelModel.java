package com.picvik.model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.picvik.util.RuntimeSettings;
public class ViewChannelModel {
	private Connection connection = null;
    private PreparedStatement ptmt = null;
    private ResultSet resultSet = null;
	public Integer getChannelCount(Integer uid) {
		Integer totalchannel = 0;
		try {
			String qstring = "SELECT count(*) As totalchannel FROM picvik_video_channel WHERE " +
					"uid = '" + uid + "';";
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	totalchannel = resultSet.getInt("totalchannel");
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return totalchannel;
	}
	public Integer getVideoCount(Integer uid) {
		Integer totalvideo = 0;
		try {
			String qstring = "SELECT count(*) As totalvideos FROM picvik_video WHERE " +
					"uid = '" + uid + "';";
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	totalvideo = resultSet.getInt("totalvideos");
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return totalvideo;
	}
	public ArrayList<ViewChannel> getAllChannels(Integer uid) {
		ArrayList<ViewChannel>allChannels = new ArrayList<ViewChannel>();
		try {
			/*String qstring = "SELECT albumid, albumname, description, location," +
					" date, uid FROM picvik_picture_album WHERE " +
					"uid = '" + uid + "';";
					*/
			
			String qstring = "SELECT channel.*, vid.totalvideos FROM " +
					"picvik_video_channel as channel , " +
					"(SELECT video.channelid as id, count(*) as totalvideos FROM" +
					" picvik_video as video WHERE " +
					"video.uid = '" + uid + "' group by video.channelid) as vid WHERE " +
							"vid.id = channel.channelid AND channel.uid = '" + uid + "';"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
        		ViewChannel channel = new  ViewChannel();
                channel.setChannelid(resultSet.getInt("channelid"));
                channel.setChannelname(resultSet.getString("channelname"));
                channel.setDescription(resultSet.getString("description"));
                channel.setLocation(resultSet.getString("location"));
                channel.setDate(resultSet.getDate("date"));
                channel.setUid(resultSet.getInt("uid"));
                channel.setTotalvideos(resultSet.getInt("totalvideos"));
                allChannels.add(channel);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return allChannels;
	}
	public ArrayList<ViewVideo> getAllVideos(Integer uid) {
		ArrayList<ViewVideo>videos = new ArrayList<ViewVideo>();
		
		try {
			String qstring = "SELECT videochannel. * , user.name, user.img " +
					"FROM ( SELECT video. * , channel.channelname, channel.description AS" +
					" 'channeldescription', channel.location, channel.date " +
					"FROM picvik_video AS video, picvik_video_channel AS channel " +
					"WHERE video.channelid = channel.channelid AND video.uid = '" + uid + "' " +
							") AS videochannel, picvik_userprofile AS user WHERE" +
							" videochannel.uid = user.uid;"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewVideo allvideos = new ViewVideo();
            	allvideos.setVideoid(resultSet.getInt("videoid"));
            	allvideos.setTitle(resultSet.getString("title"));
            	allvideos.setDescription(resultSet.getString("description"));
            	allvideos.setVideourl(resultSet.getString("videourl"));
            	allvideos.setChannelid(resultSet.getInt("channelid"));
            	allvideos.setUid(resultSet.getInt("uid"));
            	allvideos.setChannelname(resultSet.getString("channelname"));
            	allvideos.setChanneldescription(resultSet.getString("channeldescription"));
            	allvideos.setChannellocation(resultSet.getString("location"));
            	allvideos.setChanneldate(resultSet.getDate("date"));
            	allvideos.setUsername(resultSet.getString("name"));
            	allvideos.setUserimgurl(resultSet.getString("img"));
                videos.add(allvideos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return videos;
	}
	public ArrayList getChannelDetails(Integer uid,
			Integer channelid) {
		ArrayList<ViewChannel>allChannels = new ArrayList<ViewChannel>();
		try {
			String qstring = "SELECT channel.*, vid.totalvideos FROM " +
					"picvik_video_channel as channel , " +
					"(SELECT video.channelid as id, count(*) as totalvideos FROM" +
					" picvik_video as video WHERE " +
					"video.uid = '" + uid + "' group by video.channelid) as vid WHERE " +
							"vid.id = channel.channelid AND channel.channelid = '" + channelid + "';"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
        		ViewChannel channel = new  ViewChannel();
                channel.setChannelid(resultSet.getInt("channelid"));
                channel.setChannelname(resultSet.getString("channelname"));
                channel.setDescription(resultSet.getString("description"));
                channel.setLocation(resultSet.getString("location"));
                channel.setDate(resultSet.getDate("date"));
                channel.setUid(resultSet.getInt("uid"));
                channel.setTotalvideos(resultSet.getInt("totalvideos"));
                allChannels.add(channel);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return allChannels;
	}
	public ArrayList getChannelPhotos(Integer uid, Integer channelid) {
		ArrayList<ViewVideo>videos = new ArrayList<ViewVideo>();
		try {
			String qstring = "SELECT * FROM picvik_video WHERE " +
					"uid = '" + uid + "' AND channelid = '" + channelid + "';"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewVideo allvideos = new ViewVideo();
            	allvideos.setVideoid(resultSet.getInt("videoid"));
            	allvideos.setTitle(resultSet.getString("title"));
            	allvideos.setDescription(resultSet.getString("description"));
            	allvideos.setVideourl(resultSet.getString("videourl"));
            	allvideos.setChannelid(resultSet.getInt("channelid"));
            	allvideos.setUid(resultSet.getInt("uid"));
                videos.add(allvideos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return videos;
	}
	public ArrayList getChannelComments(Integer channelid,
			Integer mediaChannel) {
		ArrayList<ViewComment>comments = new ArrayList<ViewComment>();
		
		try {
			String qstring = "SELECT comment. * , user.name, user.img FROM picvik_comment AS " +
					" comment, (SELECT name, img, uid FROM picvik_userprofile ) AS user " +
					"WHERE user.uid = comment.uid AND mediaid = '" + channelid + "' AND " +
							"mediatype = '" + mediaChannel + "';"; 
			
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
	public ArrayList getVideo(Integer uid, String vidid) {
		ArrayList<ViewVideo>videos = new ArrayList<ViewVideo>();
		try {
			String qstring = "SELECT videochannel. * , user.name, user.img " +
					"FROM ( SELECT video. * , channel.channelname, channel.description AS" +
					" 'channeldescription', channel.location, channel.date " +
					"FROM picvik_video AS video, picvik_video_channel AS channel " +
					"WHERE video.channelid = channel.channelid AND video.uid = '" + uid + "' AND " +
							"video.videoid = '" + vidid +
							"') AS videochannel, picvik_userprofile AS user WHERE" +
							" videochannel.uid = user.uid;"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewVideo allvideos = new ViewVideo();
            	allvideos.setVideoid(resultSet.getInt("videoid"));
            	allvideos.setTitle(resultSet.getString("title"));
            	allvideos.setDescription(resultSet.getString("description"));
            	allvideos.setVideourl(resultSet.getString("videourl"));
            	allvideos.setChannelid(resultSet.getInt("channelid"));
            	allvideos.setUid(resultSet.getInt("uid"));
            	allvideos.setChannelname(resultSet.getString("channelname"));
            	allvideos.setChanneldescription(resultSet.getString("channeldescription"));
            	allvideos.setChannellocation(resultSet.getString("location"));
            	allvideos.setChanneldate(resultSet.getDate("date"));
            	allvideos.setUsername(resultSet.getString("name"));
            	allvideos.setUserimgurl(resultSet.getString("img"));
                videos.add(allvideos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return videos;
	}
	public ArrayList getVideocomments(String vidid,
			Integer mediaVideo) {
		ArrayList<ViewComment>comments = new ArrayList<ViewComment>();
		
		try {
			String qstring = "SELECT comment. * , user.name, user.img FROM picvik_comment AS " +
					" comment, (SELECT name, img, uid FROM picvik_userprofile ) AS user " +
					"WHERE user.uid = comment.uid AND mediaid = '" + vidid + "' AND " +
							"mediatype = '" + mediaVideo + "';"; 
			
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
	public Integer getAllPublicChannelCount() {
		Integer totalchannel = 0;
		try {
			String qstring = "SELECT count(*) As totalchannel FROM picvik_video_channel WHERE " +
					"privacy = '" + RuntimeSettings.mediaPublic + "';";
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	totalchannel = resultSet.getInt("totalchannel");
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return totalchannel;
	}
	public Integer getAllPublicVideoCount() {
		Integer totalvideo = 0;
		try {
			String qstring = "SELECT count(*) As totalvideos FROM picvik_video WHERE " +
					"privacy = '" + RuntimeSettings.mediaPublic + "';";
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
            	totalvideo = resultSet.getInt("totalvideos");
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return totalvideo;
	}
	public ArrayList getAllPublicChannels() {
		ArrayList<ViewChannel>allChannels = new ArrayList<ViewChannel>();
		try {
			/*String qstring = "SELECT albumid, albumname, description, location," +
					" date, uid FROM picvik_picture_album WHERE " +
					"uid = '" + uid + "';";
					*/
			
			String qstring = "SELECT channel.*, vid.totalvideos FROM " +
					"picvik_video_channel as channel , " +
					"(SELECT video.channelid as id, count(*) as totalvideos FROM" +
					" picvik_video as video WHERE " +
					"video.privacy = '" + RuntimeSettings.mediaPublic + "' group by video.channelid) as vid WHERE " +
							"vid.id = channel.channelid AND channel.privacy = '" + RuntimeSettings.mediaPublic +"';"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
        		ViewChannel channel = new  ViewChannel();
                channel.setChannelid(resultSet.getInt("channelid"));
                channel.setChannelname(resultSet.getString("channelname"));
                channel.setDescription(resultSet.getString("description"));
                channel.setLocation(resultSet.getString("location"));
                channel.setDate(resultSet.getDate("date"));
                channel.setUid(resultSet.getInt("uid"));
                channel.setTotalvideos(resultSet.getInt("totalvideos"));
                allChannels.add(channel);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return allChannels;
	}
	public ArrayList getAllPublicVideos() {
ArrayList<ViewVideo>videos = new ArrayList<ViewVideo>();
		
		try {
			String qstring = "SELECT videochannel. * , user.name, user.img " +
					"FROM ( SELECT video. * , channel.channelname, channel.description AS" +
					" 'channeldescription', channel.location, channel.date " +
					"FROM picvik_video AS video, picvik_video_channel AS channel " +
					"WHERE video.channelid = channel.channelid AND video.privacy = '" + RuntimeSettings.mediaPublic + "' " +
							") AS videochannel, picvik_userprofile AS user WHERE" +
							" videochannel.uid = user.uid;"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewVideo allvideos = new ViewVideo();
            	allvideos.setVideoid(resultSet.getInt("videoid"));
            	allvideos.setTitle(resultSet.getString("title"));
            	allvideos.setDescription(resultSet.getString("description"));
            	allvideos.setVideourl(resultSet.getString("videourl"));
            	allvideos.setChannelid(resultSet.getInt("channelid"));
            	allvideos.setUid(resultSet.getInt("uid"));
            	allvideos.setChannelname(resultSet.getString("channelname"));
            	allvideos.setChanneldescription(resultSet.getString("channeldescription"));
            	allvideos.setChannellocation(resultSet.getString("location"));
            	allvideos.setChanneldate(resultSet.getDate("date"));
            	allvideos.setUsername(resultSet.getString("name"));
            	allvideos.setUserimgurl(resultSet.getString("img"));
                videos.add(allvideos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return videos;
	}
	public ArrayList getPublicVideo(String vidid) {
		ArrayList<ViewVideo>videos = new ArrayList<ViewVideo>();
		try {
			String qstring = "SELECT videochannel. * , user.name, user.img " +
					"FROM ( SELECT video. * , channel.channelname, channel.description AS" +
					" 'channeldescription', channel.location, channel.date " +
					"FROM picvik_video AS video, picvik_video_channel AS channel " +
					"WHERE video.channelid = channel.channelid AND video.privacy = '" + RuntimeSettings.mediaPublic + "' AND " +
							"video.videoid = '" + vidid +
							"') AS videochannel, picvik_userprofile AS user WHERE" +
							" videochannel.uid = user.uid;"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewVideo allvideos = new ViewVideo();
            	allvideos.setVideoid(resultSet.getInt("videoid"));
            	allvideos.setTitle(resultSet.getString("title"));
            	allvideos.setDescription(resultSet.getString("description"));
            	allvideos.setVideourl(resultSet.getString("videourl"));
            	allvideos.setChannelid(resultSet.getInt("channelid"));
            	allvideos.setUid(resultSet.getInt("uid"));
            	allvideos.setChannelname(resultSet.getString("channelname"));
            	allvideos.setChanneldescription(resultSet.getString("channeldescription"));
            	allvideos.setChannellocation(resultSet.getString("location"));
            	allvideos.setChanneldate(resultSet.getDate("date"));
            	allvideos.setUsername(resultSet.getString("name"));
            	allvideos.setUserimgurl(resultSet.getString("img"));
                videos.add(allvideos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return videos;
	}
	public ArrayList getPublicChannelDetails(Integer channelid) {
		ArrayList<ViewChannel>allChannels = new ArrayList<ViewChannel>();
		try {
			String qstring = "SELECT channel.*, vid.totalvideos FROM " +
					"picvik_video_channel as channel , " +
					"(SELECT video.channelid as id, count(*) as totalvideos FROM" +
					" picvik_video as video WHERE " +
					"video.privacy = '" + RuntimeSettings.mediaPublic + "' group by video.channelid) as vid WHERE " +
					"vid.id = channel.channelid AND channel.channelid = '" + channelid + "';"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
        		ViewChannel channel = new  ViewChannel();
                channel.setChannelid(resultSet.getInt("channelid"));
                channel.setChannelname(resultSet.getString("channelname"));
                channel.setDescription(resultSet.getString("description"));
                channel.setLocation(resultSet.getString("location"));
                channel.setDate(resultSet.getDate("date"));
                channel.setUid(resultSet.getInt("uid"));
                channel.setTotalvideos(resultSet.getInt("totalvideos"));
                allChannels.add(channel);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return allChannels;
	}
	public ArrayList getPublicChannelPhotos(Integer channelid) {
		ArrayList<ViewVideo>videos = new ArrayList<ViewVideo>();
		try {
			String qstring = "SELECT * FROM picvik_video WHERE " +
					"channelid = '" + channelid + "' AND privacy = '" + 
					RuntimeSettings.mediaPublic + "';"; 
			
			//System.out.println(qstring);
    		connection = com.picvik.util.MySqlConnection.getInstance().getConnection();
            ptmt = connection.prepareStatement(qstring);
            resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
            	ViewVideo allvideos = new ViewVideo();
            	allvideos.setVideoid(resultSet.getInt("videoid"));
            	allvideos.setTitle(resultSet.getString("title"));
            	allvideos.setDescription(resultSet.getString("description"));
            	allvideos.setVideourl(resultSet.getString("videourl"));
            	allvideos.setChannelid(resultSet.getInt("channelid"));
            	allvideos.setUid(resultSet.getInt("uid"));
                videos.add(allvideos);
            }
            
            resultSet.close();
            ptmt.close();
            connection.close();
        
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return videos;
	}
	
	public void deleteVideo(Integer mediaid) {
		try{
            String queryString = "DELETE FROM picvik_video " +
                                 "WHERE videoid = " + mediaid +" ;";
            
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
