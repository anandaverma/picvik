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
import com.picvik.model.UserModel;
import com.picvik.model.ViewProfileModel;
import com.picvik.util.MyLog;
import com.picvik.util.MyUtilityFunctions;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class UploadPhotoAction extends ActionSupport implements ServletRequestAware{

	private List<File> fileUpload = new ArrayList<File>();
	private List<String> fileUploadContentType = new ArrayList<String>();
	private List<String> fileUploadFileName = new ArrayList<String>();
	private ArrayList<String> tmppics = new ArrayList<String>();
	
	//for saving photo
	//album attributes
	private String albumname;
	private String albumdesc;
	private String location;
	private Date takendate;
	private Integer albumprivacy;
	
	//picture attributes
	private String[] phototitle;
	private String[] photodesc;
	private Integer[] photoprivacy;
	
	public Integer getAlbumprivacy() {
		return albumprivacy;
	}

	public void setAlbumprivacy(Integer albumprivacy) {
		this.albumprivacy = albumprivacy;
	}

	public Integer[] getPhotoprivacy() {
		return photoprivacy;
	}

	public void setPhotoprivacy(Integer[] photoprivacy) {
		this.photoprivacy = photoprivacy;
	}

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

	public String getAlbumdesc() {
		return albumdesc;
	}

	public void setAlbumdesc(String albumdesc) {
		this.albumdesc = albumdesc;
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

	public String[] getPhototitle() {
		return phototitle;
	}

	public void setPhototitle(String[] phototitle) {
		this.phototitle = phototitle;
	}

	public String[] getPhotodesc() {
		return photodesc;
	}

	public void setPhotodesc(String[] photodesc) {
		this.photodesc = photodesc;
	}


	public ArrayList<String> getTmppics() {
		return tmppics;
	}

	public void setTmppics(ArrayList<String> tmppics) {
		this.tmppics = tmppics;
	}

	private HttpServletRequest servletRequest;
 
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
 
	public String uploadPhoto() throws Exception{
		MyLog.log("Inside UploadPhotoAction uploadPhoto function");
		try {
			//get path to upload photo
            String filePath = servletRequest.getSession().
            		getServletContext().getRealPath("/uploads/pictures");
            
		//debugging
	    for (int i=0; i < fileUpload.size(); i++) {
	        System.out.println("File :" + fileUpload.get(i));
	      //creating unique picture name
        	Map sess = (Map) ActionContext.getContext().get("session");
    		Integer uid = (Integer) sess.get("uid");
        	String photoName = uid + "-" + 
        			MyUtilityFunctions.createVerificationUrl() + "-" + fileUploadFileName.get(i);
        	
        	//photo url	
        	String photoUrl = "uploads/pictures/" + photoName;        	
        	
        	//create new File with new path and name
            File fileToCreate = new File(filePath, photoName);
            
            //copy file to given location and with given name
            FileUtils.copyFile(fileUpload.get(i), fileToCreate);
            
            tmppics.add(photoUrl);
           
	      
	    }
	    
	    Map sess = (Map) ActionContext.getContext().get("session");
	    sess.put("tmppics", tmppics);
	    
 
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
	
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public String editPhoto(){
		MyLog.log("Inside UploadPhotoAction editPhoto function");
		Map sess = (Map) ActionContext.getContext().get("session");
	    tmppics = (ArrayList<String>) sess.get("tmppics");
    	return INPUT;
    }
	//Ananda function starts here
	/*public String savePhoto(){
		
		//get userid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		//
		UploadPhotoModel uploadphotos = new UploadPhotoModel();
		
		//create photo album
        uploadphotos.createPhotoAlbum(albumname, albumdesc,location, takendate, albumprivacy, uid);
        
        //get newly created albumid
        Integer albumid = uploadphotos.getPhotoAlbumID(albumname, uid);
        
        
        //inserting photos to created album
        List<String> phototilelist = Arrays.asList(phototitle);
        List<String> photodesclist = Arrays.asList(photodesc);
        List<Integer> photoprivacylist = Arrays.asList(photoprivacy);
        List<String> photourllist = (List<String>) sess.get("tmppics");
        for (int i=0; i< photourllist.size(); i++){
        	uploadphotos.insertPhotos(phototilelist.get(i), photodesclist.get(i),
        			photourllist.get(i),photoprivacylist.get(i),albumid, uid);	
        }*/
        //Ananda's function ends here
	
		//sandeep's functions starts here
	public String savePhoto(){
		// objects required for logging the activity.
		MyLog.log("Inside UploadPhotoAction savePhoto function");
		Activity activityModel = new Activity();
		
		//get userid from session
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		String userName = UserModel.getUserNameFromID(uid);
		
		//
		UploadPhotoModel uploadphotos = new UploadPhotoModel();
		
		//create photo album
        uploadphotos.createPhotoAlbum(albumname, albumdesc,location, takendate, albumprivacy, uid);
        

        //get newly created albumid
        Integer albumid = uploadphotos.getPhotoAlbumID(albumname, uid);
        
        
        // Create a activity feed for new album creation.
       	// TODO: SANDEEP : actually source should be from media table, verify with ANANDA once.
    	activityModel.setActivity_source_id(albumid);
    	activityModel.setActivity_source_type_id(ActivitySourceType.get_Picture_album_type_id());
    	activityModel.setActivity_type_id(ActivityType.get_Create_album_activity_type_id());
    	activityModel.setUser_id(uid);
    	activityModel.setData(userName + " Created a New Picture Album.");
    	
    	activityModel.insert_activity();   
        
        //inserting photos to created album
        List<String> phototilelist = Arrays.asList(phototitle);
        List<String> photodesclist = Arrays.asList(photodesc);
        List<Integer> photoprivacylist = Arrays.asList(photoprivacy);
        List<String> photourllist = (List<String>) sess.get("tmppics");
        for (int i=0; i< photourllist.size(); i++){
        	// 1. Saving photo
        	
        	uploadphotos.insertPhotos(phototilelist.get(i), photodesclist.get(i),
        			photourllist.get(i),photoprivacylist.get(i),albumid, uid);	
        	
        	// Get newly inserted photo id.
        	int photoid = uploadphotos.getPhotoID(phototilelist.get(i), uid);
        	
        	// 2. Logging Activity for activity feeds.
        	
        	// TODO: SANDEEP : actually source should be from media table, verify with ANANDA once.
        	activityModel.setActivity_source_id(photoid);
        	activityModel.setActivity_source_type_id(ActivitySourceType.get_Picture_type_id());
        	activityModel.setActivity_type_id(ActivityType.get_Add_picture_activity_type_id());
        	activityModel.setUser_id(uid);
        	
        	activityModel.setData(userName + " Uploaded a New Picture.");
        	
        	activityModel.insert_activity();        	
        }
        //sandeep functions end here
        
        //clear session object tmppics
        sess.remove("tmppics");
		return SUCCESS;
	}
	
}