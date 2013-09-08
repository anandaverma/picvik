package com.picvik.action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.picvik.model.ViewAlbumModel;
import com.picvik.model.ViewChannelModel;
import com.picvik.model.ViewPhoto;
import com.picvik.model.ViewVideo;
import com.picvik.util.MyLog;

public class ManageMediaAction extends ActionSupport{
	private ArrayList<ViewVideo>allVideos = new ArrayList<ViewVideo>();
	private ArrayList<ViewPhoto>allPhotos = new ArrayList<ViewPhoto>();
	private Integer mediaid;
	
	public ArrayList<ViewVideo> getAllVideos() {
		return allVideos;
	}

	public void setAllVideos(ArrayList<ViewVideo> allvideos) {
		this.allVideos = allvideos;
	}

	public ArrayList<ViewPhoto> getAllPhotos() {
		return allPhotos;
	}

	public void setAllPhotos(ArrayList<ViewPhoto> allPhotos) {
		this.allPhotos = allPhotos;
	}

	public Integer getMediaid() {
		return mediaid;
	}

	public void setMediaid(Integer mediaid) {
		this.mediaid = mediaid;
	}

	public String managePhoto() {
		MyLog.log("Inside ManageMediaAction managePhoto function");
		//System.out.println("home");
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		allPhotos = viewalbum.getAllPublicPhotos();
				//get all comments on album
		return SUCCESS;
	}
	public String deletePhoto() {
		MyLog.log("Inside ManageMediaAction deletePhoto function");
		//System.out.println("home");
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		viewalbum.deletePhoto(mediaid);
		return SUCCESS;
	}
	
	public String manageVideo() {
		MyLog.log("Inside ManageMediaAction manageVideo function");
		ViewChannelModel viewchannel = new ViewChannelModel();
		allVideos = viewchannel.getAllPublicVideos();
		return SUCCESS;
	}
	
	public String deleteVideo() {
		MyLog.log("Inside ManageMediaAction deleteVideo function");
		ViewChannelModel viewchannel = new ViewChannelModel();
		viewchannel.deleteVideo(mediaid);
		return SUCCESS;
	}
	
}
