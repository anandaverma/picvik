package com.picvik.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.picvik.model.CommentModel;
import com.picvik.model.ViewProfileModel;
import com.picvik.util.MyLog;

public class CommentAction extends ActionSupport {
	private String comment;
	private Integer mediaid;
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getMediaid() {
		return mediaid;
	}

	public void setMediaid(Integer mediaid) {
		this.mediaid = mediaid;
	}

	public Integer getMediatype() {
		return mediatype;
	}

	public void setMediatype(Integer mediatype) {
		this.mediatype = mediatype;
	}

	private Integer mediatype;
	
	public String commentOnAlbum() {
		MyLog.log("Inside commentOnAlbum function");
		CommentModel userComment = new CommentModel();
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		userComment.addComment(comment, mediaid, mediatype, uid);
		return SUCCESS;
	}
	
	public String commentOnPhoto() {
		MyLog.log("Inside commentOnPhoto function");
		CommentModel userComment = new CommentModel();
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		userComment.addComment(comment, mediaid, mediatype, uid);
		return SUCCESS;
	}
	
	public String commentOnChannel() {
		MyLog.log("Inside commentOnChannel function");
		CommentModel userComment = new CommentModel();
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		userComment.addComment(comment, mediaid, mediatype, uid);
		return SUCCESS;
	}
	
	public String commentOnVideo() {
		MyLog.log("Inside commentOnVideo function");
		CommentModel userComment = new CommentModel();
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		userComment.addComment(comment, mediaid, mediatype, uid);
		return SUCCESS;
	}
}
