package com.picvik.action;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.picvik.model.Activity;
import com.picvik.model.ActivitySourceType;
import com.picvik.model.ActivityType;
import com.picvik.model.UploadPhotoModel;
import com.picvik.model.UploadVideoModel;
import com.picvik.model.UserModel;
import com.picvik.model.ViewProfileModel;
import com.picvik.util.MyLog;
import com.picvik.util.MyUtilityFunctions;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class UploadVideoAction extends ActionSupport implements ServletRequestAware{
	
	private List<File> fileUpload = new ArrayList<File>();
	private List<String> fileUploadContentType = new ArrayList<String>();
	private List<String> fileUploadFileName = new ArrayList<String>();
	private ArrayList<String> tmpvideo = new ArrayList<String>();
	
	//for saving photo
	//album attributes
	private String channelname;
	private String channeldesc;
	private String location;
	private Date takendate;
	private Integer channelprivacy;
	
	//picture attributes
	private String[] videotitle;
	private String[] videodesc;
	private Integer[] videoprivacy;
	private HttpServletRequest servletRequest;
	
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public List<File> getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}

	public List<String> getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(List<String> fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public ArrayList<String> getTmpvideo() {
		return tmpvideo;
	}

	public void setTmpvideo(ArrayList<String> tmpvideo) {
		this.tmpvideo = tmpvideo;
	}

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	public String getChanneldesc() {
		return channeldesc;
	}

	public void setChanneldesc(String channeldesc) {
		this.channeldesc = channeldesc;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getTakendate() {
		return takendate;
	}

	public void setTakendate(Date takendate) {
		this.takendate = takendate;
	}

	public Integer getChannelprivacy() {
		return channelprivacy;
	}

	public void setChannelprivacy(Integer channelprivacy) {
		this.channelprivacy = channelprivacy;
	}

	public String[] getVideotitle() {
		return videotitle;
	}

	public void setVideotitle(String[] videotitle) {
		this.videotitle = videotitle;
	}

	public String[] getVideodesc() {
		return videodesc;
	}

	public void setVideodesc(String[] videodesc) {
		this.videodesc = videodesc;
	}

	public Integer[] getVideoprivacy() {
		return videoprivacy;
	}

	public void setVideoprivacy(Integer[] videoprivacy) {
		this.videoprivacy = videoprivacy;
	}

	public String execute(){
    	return SUCCESS;
    }
	
public String uploadVideo() throws Exception{
	MyLog.log("Inside UploadVideoAction uploadVideo function");
		try {
			//get path to upload photo
            String filePath = servletRequest.getSession().
            		getServletContext().getRealPath("/uploads/videos");
            
		//debugging
	    for (int i=0; i < fileUpload.size(); i++) {
	        System.out.println("File :" + fileUpload.get(i));
	      //creating unique picture name
        	Map sess = (Map) ActionContext.getContext().get("session");
    		Integer uid = (Integer) sess.get("uid");
        	String videoName = uid + "-" + 
        			MyUtilityFunctions.createVerificationUrl() + "-" + fileUploadFileName.get(i);
        	
        	//photo url	
        	String videoUrl = "uploads/videos/" + videoName;        	
        	
        	//create new File with new path and name
            File fileToCreate = new File(filePath, videoName);
            
            //copy file to given location and with given name
            FileUtils.copyFile(fileUpload.get(i), fileToCreate);
            
            tmpvideo.add(videoUrl);
           
	      
	    }
	    
	    Map sess = (Map) ActionContext.getContext().get("session");
	    sess.put("tmpvideo", tmpvideo);
	    
 
	    for (String fileName: fileUploadFileName) {
	        System.out.println("Filename : " + fileName);
	    }
 
	    for (String fileContentType: fileUploadContentType) {
	        System.out.println("File type : " + fileContentType);
	    }
	    //end here
        return SUCCESS;
		} catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return INPUT;
        }
    }
	
	public String editVideo(){
		MyLog.log("Inside UploadVideoAction editVideo function");
		Map sess = (Map) ActionContext.getContext().get("session");
		tmpvideo = (ArrayList<String>) sess.get("tmpvideo");
		return INPUT;
	}
	
	/*
	 * Creates a video channel if required. 
	 * Saves all videos.
	 * Logs activity feeds.
	 * TODO: sandeep -> Activity feeds should only be logged for public videos and video channel. Make necessary changes.
	 */
	public String saveVideo(){
		MyLog.log("Inside UploadVideoAction saveVideo function");
		// objects required for logging the activity.
		Activity activityModel = new Activity();
		
		//get userid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		String userName = UserModel.getUserNameFromID(uid);
		
		UploadVideoModel uploadvideos = new UploadVideoModel();
		
		//create photo album
        uploadvideos.createVideoChannel(channelname, channeldesc,location, takendate, channelprivacy, uid);
        
        //get newly created channel id
        Integer channelid = uploadvideos.getVideoChannelID(channelname, uid);
        
        // Create a activity feed for new video channel creation.
       	// TODO: SANDEEP : actually source should be from media table, verify with ANANDA once.
    	activityModel.setActivity_source_id(channelid);
    	activityModel.setActivity_source_type_id(ActivitySourceType.getNew_video_channel());
    	activityModel.setActivity_type_id(ActivityType.getNew_video_channel());
    	activityModel.setUser_id(uid);
    	activityModel.setData(userName + " Created a New Video channel.");
    	
    	activityModel.insert_activity();   
        
        //inserting photos to created album
        List<String> videotilelist = Arrays.asList(videotitle);
        List<String> videodesclist = Arrays.asList(videodesc);
        List<Integer> videoprivacylist = Arrays.asList(videoprivacy);
        List<String> videourllist = (List<String>) sess.get("tmpvideo");
        for (int i=0; i< videourllist.size(); i++){
        	// 1. Saving video
        	uploadvideos.insertVideos(videotilelist.get(i), videodesclist.get(i),
        			videourllist.get(i),videoprivacylist.get(i),channelid, uid);
        	
        	// Get newly inserted video id.
        	int videoid = uploadvideos.getVideoID(videotilelist.get(i), uid);
        	
        	// 2. Logging Activity for activity feeds.
        	
        	// TODO: SANDEEP : actually source should be from media table, verify with ANANDA once.
        	activityModel.setActivity_source_id(videoid);
        	activityModel.setActivity_source_type_id(ActivitySourceType.getNew_video());
        	activityModel.setActivity_type_id(ActivityType.getNew_video());
        	activityModel.setUser_id(uid);
        	
        	activityModel.setData(userName + " Uploaded a New Video.");
        	
        	activityModel.insert_activity();      
        }
        
        //clear session object tmppics
        sess.remove("tmpvideo");
		return SUCCESS;
	}

}
