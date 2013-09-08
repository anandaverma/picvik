package com.picvik.model;

import java.sql.Timestamp;

public class ViewComment {
	private Integer commentid;
	private String comment;
	private Timestamp datetime;
	private Integer mediaid;
	private Integer uid;
	private Integer mediatype;
	private String name;
	private String imgurl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public Integer getMediatype() {
		return mediatype;
	}
	public void setMediatype(Integer mediatype) {
		this.mediatype = mediatype;
	}
	public Integer getCommentid() {
		return commentid;
	}
	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	public Integer getMediaid() {
		return mediaid;
	}
	public void setMediaid(Integer mediaid) {
		this.mediaid = mediaid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
}
