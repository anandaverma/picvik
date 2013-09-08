package com.picvik.model;

import java.util.Date;

public class ViewAlbum {

	private Integer albumid;
	private String albumname;
	private String description;
	private String location;
	private Date date;
	private Integer uid;
	private Integer totalphotos;
	
	public Integer getTotalphotos() {
		return totalphotos;
	}
	public void setTotalphotos(Integer totalphotos) {
		this.totalphotos = totalphotos;
	}
	public Integer getAlbumid() {
		return albumid;
	}
	public void setAlbumid(Integer albumid) {
		this.albumid = albumid;
	}
	public String getAlbumname() {
		return albumname;
	}
	public void setAlbumname(String albumname) {
		this.albumname = albumname;
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
	
}
