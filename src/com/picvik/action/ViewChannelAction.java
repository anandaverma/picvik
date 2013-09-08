package com.picvik.action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.picvik.model.ViewChannelModel;
import com.picvik.model.ViewChannel;
import com.picvik.model.ViewComment;
import com.picvik.model.ViewVideo;
import com.picvik.util.MyLog;
import com.picvik.util.RuntimeSettings;

public class ViewChannelAction extends ActionSupport {

	private Integer totalchannel;
	private Integer totalvideo;
	private ArrayList<ViewChannel>allChannels = new ArrayList<ViewChannel>();
	private ArrayList<ViewVideo>allVideos = new ArrayList<ViewVideo>();
	private ArrayList<ViewComment>allcomments = new ArrayList<ViewComment>();
	private Integer channelid;
	private String vidid;

	

	public Integer getTotalchannel() {
		return totalchannel;
	}

	public void setTotalchannel(Integer totalchannel) {
		this.totalchannel = totalchannel;
	}

	public Integer getTotalvideo() {
		return totalvideo;
	}

	public void setTotalvideo(Integer totalvideo) {
		this.totalvideo = totalvideo;
	}

	public ArrayList<ViewChannel> getAllChannels() {
		return allChannels;
	}

	public void setAllChannels(ArrayList<ViewChannel> allChannels) {
		this.allChannels = allChannels;
	}

	public ArrayList<ViewVideo> getAllVideos() {
		return allVideos;
	}

	public void setAllVideos(ArrayList<ViewVideo> allVideos) {
		this.allVideos = allVideos;
	}

	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	public String getVidid() {
		return vidid;
	}

	public void setVidid(String vidid) {
		this.vidid = vidid;
	}

	public String listChannel() {
		MyLog.log("Inside ViewChannelAction listchannel function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewChannelModel viewalbum = new ViewChannelModel();
		//get albums count from db
		totalchannel = viewalbum.getChannelCount(uid);
		//get photo count from db
		totalvideo = viewalbum.getVideoCount(uid);
		
		//get all albums
		allChannels = viewalbum.getAllChannels(uid);
		
		//get photos from db
		allVideos = viewalbum.getAllVideos(uid);
		return "success";
		
	}
	
	public String exploreChannel() {
		MyLog.log("Inside ViewChannelAction explorechannel function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewChannelModel viewalbum = new ViewChannelModel();
		//get albums count from db
		totalchannel = viewalbum.getAllPublicChannelCount();
		//get photo count from db
		totalvideo = viewalbum.getAllPublicVideoCount();
		
		//get all albums
		allChannels = viewalbum.getAllPublicChannels();
		
		//get photos from db
		allVideos = viewalbum.getAllPublicVideos();
		return "success";
		
	}
	
	public String listVideo() {
		MyLog.log("Inside ViewChannelAction listvideo function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewChannelModel viewchannel = new ViewChannelModel();
		//get albums count from db
		totalchannel = viewchannel.getChannelCount(uid);
		//get photo count from db
		totalvideo = viewchannel.getVideoCount(uid);
		//get all photos from db
		allVideos = viewchannel.getAllVideos(uid);
		//get all comments on album
		return "success";
		
	}
	
	public String exploreVideo() {
		MyLog.log("Inside ViewChannelAction explorevideo function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewChannelModel viewchannel = new ViewChannelModel();
		//get albums count from db
		totalchannel = viewchannel.getAllPublicChannelCount();
		//get photo count from db
		totalvideo = viewchannel.getAllPublicVideoCount();
		//get all photos from db
		allVideos = viewchannel.getAllPublicVideos();
		//get all comments on album
		return "success";
		
	}
	
	public String viewVideo() {
		MyLog.log("Inside ViewChannelAction viewvideo function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewChannelModel viewchannel = new ViewChannelModel();
		//get albums count from db
		totalchannel = viewchannel.getChannelCount(uid);
		//get photo count from db
		totalvideo = viewchannel.getVideoCount(uid);
		//get all photos from db
		allVideos = viewchannel.getVideo(uid, vidid);
		//get all comments on album
		allcomments = viewchannel.getVideocomments(vidid, RuntimeSettings.mediaVideo);
		
		return "success";
		
	}
	
	public String viewPublicVideo() {
		MyLog.log("Inside ViewChannelAction viewpublicvideo function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewChannelModel viewchannel = new ViewChannelModel();
		//get albums count from db
		totalchannel = viewchannel.getAllPublicChannelCount();
		//get photo count from db
		totalvideo = viewchannel.getAllPublicVideoCount();
		//get all photos from db
		allVideos = viewchannel.getPublicVideo(vidid);
		//get all comments on album
		allcomments = viewchannel.getVideocomments(vidid, RuntimeSettings.mediaVideo);
		
		return "success";
		
	}
	
	public String listChannelVideo() {
		MyLog.log("Inside ViewChannelAction listchannelvideo function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewChannelModel viewchannel = new ViewChannelModel();
		//get albums count from db
		totalchannel = viewchannel.getChannelCount(uid);
		//get photo count from db
		totalvideo = viewchannel.getVideoCount(uid);
		//get all albums
		allChannels = viewchannel.getChannelDetails(uid, channelid);
		//get all photos from db
		allVideos = viewchannel.getChannelPhotos(uid, channelid);
		//get all comments on album
		allcomments = viewchannel.getChannelComments(channelid, RuntimeSettings.mediaChannel);
		return "success";
		
	}
	
	public String exploreChannelVideo() {
		MyLog.log("Inside ViewChannelAction explorechannelvideo function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewChannelModel viewchannel = new ViewChannelModel();
		//get albums count from db
		totalchannel = viewchannel.getAllPublicChannelCount();
		//get photo count from db
		totalvideo = viewchannel.getAllPublicVideoCount();
		//get all albums
		allChannels = viewchannel.getPublicChannelDetails(channelid);
		//get all photos from db
		allVideos = viewchannel.getPublicChannelPhotos(channelid);
		//get all comments on album
		allcomments = viewchannel.getChannelComments(channelid, RuntimeSettings.mediaChannel);
		return "success";
		
	}

	public ArrayList<ViewComment> getAllcomments() {
		return allcomments;
	}

	public void setAllcomments(ArrayList<ViewComment> allcomments) {
		this.allcomments = allcomments;
	}
	
}