package com.picvik.action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.picvik.model.ViewAlbumModel;
import com.picvik.model.ViewAlbum;
import com.picvik.model.ViewComment;
import com.picvik.model.ViewPhoto;
import com.picvik.util.MyLog;
import com.picvik.util.RuntimeSettings;
	
public class ViewAlbumAction extends ActionSupport {
	private Integer totalalbum;
	private Integer totalphoto;
	private ArrayList<ViewAlbum>allAlbums = new ArrayList<ViewAlbum>();
	private ArrayList<ViewPhoto>allPhotos = new ArrayList<ViewPhoto>();
	private ArrayList<ViewComment>allcomments = new ArrayList<ViewComment>();
	private Integer albumid;
	private String pictureid;

	public String getPictureid() {
		return pictureid;
	}

	public void setPictureid(String pictureid) {
		this.pictureid = pictureid;
	}

	public ArrayList<ViewAlbum> getAllAlbums() {
		return allAlbums;
	}

	public void setAllAlbums(ArrayList<ViewAlbum> allAlbums) {
		this.allAlbums = allAlbums;
	}

	public ArrayList<ViewPhoto> getAllPhotos() {
		return allPhotos;
	}

	public void setAllPhotos(ArrayList<ViewPhoto> allPhotos) {
		this.allPhotos = allPhotos;
	}

	public Integer getTotalphoto() {
		return totalphoto;
	}

	public void setTotalphoto(Integer totalphoto) {
		this.totalphoto = totalphoto;
	}

	public Integer getTotalalbum() {
		return totalalbum;
	}

	public void setTotalalbum(Integer totalalbum) {
		this.totalalbum = totalalbum;
	}

	public String listAlbum() {
		MyLog.log("Inside ViewAlbumAction listAlbum function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		//get albums count from db
		totalalbum = viewalbum.getAlbumCount(uid);
		//get photo count from db
		totalphoto = viewalbum.getPhotoCount(uid);
		
		//get all albums
		allAlbums = viewalbum.getAllAlbums(uid);
		
		//get photos from db
		allPhotos = viewalbum.getAllPhotos(uid);
		return "success";
		
	}
	
	public String exploreAlbum() {
		MyLog.log("Inside ViewAlbumAction exploreAlbum function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		//get albums count from db
		totalalbum = viewalbum.getAllPublicAlbumCount();
		//get photo count from db
		totalphoto = viewalbum.getAllPublicPhotoCount();
		
		//get all albums
		allAlbums = viewalbum.getAllPublicAlbums();
		
		//get photos from db
		allPhotos = viewalbum.getAllPublicPhotos();
		return "success";
		
	}
	
	public String listPhoto() {
		MyLog.log("Inside ViewAlbumAction listPhoto function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		//get albums count from db
		totalalbum = viewalbum.getAlbumCount(uid);
		//get photo count from db
		totalphoto = viewalbum.getPhotoCount(uid);
		//get all photos from db
		allPhotos = viewalbum.getAllPhotos(uid);
		//get all comments on album
		return "success";
		
	}
	
	public String explorePhoto() {
		MyLog.log("Inside ViewAlbumAction explorePhoto function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		//get albums count from db
		totalalbum = viewalbum.getAllPublicAlbumCount();
		//get photo count from db
		totalphoto = viewalbum.getAllPublicPhotoCount();
		//get all photos from db
		allPhotos = viewalbum.getAllPublicPhotos();
		//get all comments on album
		return "success";
		
	}
	
	public String viewPhoto() {
		MyLog.log("Inside ViewAlbumAction viewPhoto function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		//get albums count from db
		totalalbum = viewalbum.getAlbumCount(uid);
		//get photo count from db
		totalphoto = viewalbum.getPhotoCount(uid);
		//get all photos from db
		allPhotos = viewalbum.getPhoto(uid, pictureid);
		//get all comments on album
		allcomments = viewalbum.getPhotocomments(pictureid, RuntimeSettings.mediaPhoto);
		
		return "success";
		
	}
	
	public String viewPublicPhoto() {
		MyLog.log("Inside ViewAlbumAction viewPublicPhoto function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		//get albums count from db
		totalalbum = viewalbum.getAllPublicAlbumCount();
		//get photo count from db
		totalphoto = viewalbum.getAllPublicPhotoCount();
		//get all photos from db
		allPhotos = viewalbum.getPublicPhoto(pictureid);
		//get all comments on album
		allcomments = viewalbum.getPhotocomments(pictureid, RuntimeSettings.mediaPhoto);
		
		return "success";
		
	}
	
	public String listAlbumPhoto() {
		MyLog.log("Inside ViewAlbumAction listAlbumPhoto function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		//get albums count from db
		totalalbum = viewalbum.getAlbumCount(uid);
		//get photo count from db
		totalphoto = viewalbum.getPhotoCount(uid);
		//get all albums
		allAlbums = viewalbum.getAlbumsDetail(uid, albumid);
		//get all photos from db
		allPhotos = viewalbum.getAlbumPhotos(uid, albumid);
		//get all comments on album
		allcomments = viewalbum.getAlbumcomments(albumid, RuntimeSettings.mediaAlbum);
		return "success";
		
	}
	
	public String exploreAlbumPhoto() {
		MyLog.log("Inside ViewAlbumAction exploreAlbumPhoto function");
		//System.out.println("home");
		//get uid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		ViewAlbumModel viewalbum = new ViewAlbumModel();
		//get albums count from db
		totalalbum = viewalbum.getAllPublicAlbumCount();
		//get photo count from db
		totalphoto = viewalbum.getAllPublicPhotoCount();
		//get album details
		allAlbums = viewalbum.getPublicAlbumsDetail(albumid);
		//get all photos in that album from db
		allPhotos = viewalbum.getPublicAlbumPhotos(albumid);
		//get all comments on album
		allcomments = viewalbum.getAlbumcomments(albumid, RuntimeSettings.mediaAlbum);
		return "success";
		
	}

	public ArrayList<ViewComment> getAllcomments() {
		return allcomments;
	}

	public void setAllcomments(ArrayList<ViewComment> allcomments) {
		this.allcomments = allcomments;
	}

	public Integer getAlbumid() {
		return albumid;
	}

	public void setAlbumid(Integer albumid) {
		this.albumid = albumid;
	}
	
}