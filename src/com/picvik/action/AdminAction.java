package com.picvik.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.picvik.model.UserInformation;
import com.picvik.model.UserModel;
import com.picvik.util.MyLog;

public class AdminAction extends ActionSupport{

	ArrayList<UserInformation> allUserProfiles = new ArrayList<UserInformation>();
	private Integer uid;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public ArrayList<UserInformation> getAllUserProfiles() {
		return allUserProfiles;
	}
	public void setAllUserProfiles(ArrayList<UserInformation> allUserProfiles) {
		this.allUserProfiles = allUserProfiles;
	}
	
	
	
	public String manageUser() {
		MyLog.log("Inside manageUser function");
		UserModel users= new UserModel();
		allUserProfiles = users.GetAllUserInfo();
        return SUCCESS;
    }
	
	public String banUser() {
		MyLog.log("Inside banUser function");
		UserModel users= new UserModel();
		users.BanUser(uid);
        return SUCCESS;
    }
	
	public String deleteUser() {
		MyLog.log("Inside deleteUser function");
		UserModel users= new UserModel();
		users.RemoveUser(uid);
        return SUCCESS;
    }
	
	public String allowUser() {
		MyLog.log("Inside allowUser function");
		UserModel users= new UserModel();
		users.AllowUser(uid);
        return SUCCESS;
    }
	
	public String activateUser() {
		MyLog.log("Inside activateUser function");
		UserModel users= new UserModel();
		users.ActivateUser(uid);
        return SUCCESS;
    }
	
	public String managePhoto() {
        return SUCCESS;
    }
	public String manageVideo() {
        return SUCCESS;
    }
}
