package com.picvik.model;

import java.util.Date;

public class ViewChannel {

	private Integer channelid;
	private String channelname;
	private String description;
	private String location;
	private Date date;
	private Integer uid;
	private Integer totalvideos;
	
	public Integer getChannelid() {
		return channelid;
	}
	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}
	public String getChannelname() {
		return channelname;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getTotalvideos() {
		return totalvideos;
	}
	public void setTotalvideos(Integer totalvideos) {
		this.totalvideos = totalvideos;
	}
	
	
}
