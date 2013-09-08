package com.picvik.action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.picvik.model.RegisterModel;
import com.picvik.model.ViewProfile;
import com.picvik.model.ViewProfileModel;
import com.picvik.util.MyLog;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViewProfileAction extends ActionSupport {
	
	private String name;
	private String gender;
	private Date dateofbirth;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String zip;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	private ArrayList<ViewProfile> userprofile = new ArrayList<ViewProfile>();  
	
	public ArrayList<ViewProfile> getUserprofile() {
		return userprofile;
	}

	public void setUserprofile(ArrayList<ViewProfile> userprofile) {
		this.userprofile = userprofile;
	}

	public String execute() {
		MyLog.log("Inside ViewProfileAction execute function");
		ViewProfileModel userProfile = new ViewProfileModel();
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		userprofile = userProfile.getProfile(uid);
        return SUCCESS;
    }
	
	public String saveProfile() {	
		MyLog.log("Inside ViewProfileAction saveprofile function");
		ViewProfileModel userProfile = new ViewProfileModel();
		Map sess = (Map) ActionContext.getContext().get("session");
		Integer uid = (Integer) sess.get("uid");
		
		userProfile.updateProfile(name, gender, dateofbirth, address1, 
				address2, city, state, country, zip, uid);
		
		return SUCCESS;
	}

}
