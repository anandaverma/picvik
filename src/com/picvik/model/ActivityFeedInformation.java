package com.picvik.model;

import java.sql.Timestamp;
import java.util.Date;

/*
 * Class having all information of a activity feed required to render in activity stream.
 */
public class ActivityFeedInformation {
	private int uid;
	private String uname;
	private String activityData;
	private int activityType;
	private Date activityDate;
	private String userProfilePicUrl;
	
	/*
	 * Getters and Setters
	 */
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getActivityData() {
		return activityData;
	}
	public void setActivityData(String activityData) {
		this.activityData = activityData;
	}
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	
	
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	public String getUserProfilePicUrl() {
		return userProfilePicUrl;
	}
	public void setUserProfilePicUrl(String userProfilePicUrl) {
		this.userProfilePicUrl = userProfilePicUrl;
	}	
}
