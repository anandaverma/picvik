package com.picvik.model;

import java.sql.Date;

public class ViewVideo {

	private Integer videoid;
	private String title;
	private String description;
	private String videourl;
	private Integer channelid;
	
	private Integer uid;
	
	private String channelname;
	private String channeldescription;
	private String channellocation;
	private Date channeldate;
	
	private String username;
	private String userimgurl; 
	
	public Integer getVideoid() {
		return videoid;
	}
	public void setVideoid(Integer videoid) {
		this.videoid = videoid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	public Integer getChannelid() {
		return channelid;
	}
	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getChannelname() {
		return channelname;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	public String getChanneldescription() {
		return channeldescription;
	}
	public void setChanneldescription(String channeldescription) {
		this.channeldescription = channeldescription;
	}
	public String getChannellocation() {
		return channellocation;
	}
	public void setChannellocation(String channellocation) {
		this.channellocation = channellocation;
	}
	public Date getChanneldate() {
		return channeldate;
	}
	public void setChanneldate(Date channeldate) {
		this.channeldate = channeldate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserimgurl() {
		return userimgurl;
	}
	public void setUserimgurl(String userimgurl) {
		this.userimgurl = userimgurl;
	}
	
}
