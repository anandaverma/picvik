package com.picvik.action;

import java.io.File;
import java.util.Map;

import com.picvik.model.ViewProfileModel;
import com.picvik.util.*;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
 
public class ChangeProfilePhotoAction extends ActionSupport implements
        ServletRequestAware {
    private File userImage; //This will store actual uploaded File
    private String userImageContentType; //This string will contain the Content Type of uploaded file.
    private String userImageFileName; //This string will contain the file name of uploaded file.
 
    private HttpServletRequest servletRequest;
 
    public String execute(){
    	return SUCCESS;
    }
    
    public String uploadProfilePhoto() {
    	MyLog.log("Inside uploadProfilePhoto function");
        try {
        	//get path to upload photo
            String filePath = servletRequest.getSession().
            		getServletContext().getRealPath("/uploads/profilepics");
            
            //tmp
            //String filePath = "/uploads/profilepics";
            
        	System.out.println("Server path:" + filePath);
        	
        	//creating unique picture name
        	Map sess = (Map) ActionContext.getContext().get("session");
    		Integer uid = (Integer) sess.get("uid");
        	String profilePictureName = uid + "-" + 
        			MyUtilityFunctions.createVerificationUrl() + "-" +
        			this.userImageFileName;
        	
        	//update user record
        	//tobe done	
        	String imgUrl = "uploads/profilepics/" + profilePictureName;
        	ViewProfileModel pofilePictureUpdate = new ViewProfileModel();
        	pofilePictureUpdate.updateUserPhotoUrl(imgUrl, uid);
        	
        	//create new File with new path and name
            File fileToCreate = new File(filePath, profilePictureName);
            
            //copy file to given location and with given name
            FileUtils.copyFile(this.userImage, fileToCreate);
            
          //get new photo into session
            sess.put("imgurl", imgUrl);
            
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            return INPUT;
        }
        
        
        
        return SUCCESS;
    }
 
    public File getUserImage() {
        return userImage;
    }
 
    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }
 
    public String getUserImageContentType() {
        return userImageContentType;
    }
 
    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }
 
    public String getUserImageFileName() {
        return userImageFileName;
    }
 
    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }
 
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
 
    }
}
