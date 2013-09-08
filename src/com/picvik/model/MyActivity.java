package com.picvik.model;


public class MyActivity {
	private String username;
	private String userpicture;
	private String activitystring;
	private int activitytype;
	private String acitvityurl;
	private String activitytimestamp;
	
	public String getActivitytimestamp() {
		return activitytimestamp;
	}
	public void setActivitytimestamp(String activitytimestamp) {
		this.activitytimestamp = activitytimestamp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpicture() {
		return userpicture;
	}
	public void setUserpicture(String userpicture) {
		this.userpicture = userpicture;
	}
	public String getActivitystring() {
		return activitystring;
	}
	public void setActivitystring(String activitystring) {
		this.activitystring = activitystring;
	}
	public int getActivitytype() {
		return activitytype;
	}
	public void setActivitytype(int activitytype) {
		this.activitytype = activitytype;
	}
	public String getAcitvityurl() {
		return acitvityurl;
	}
	public void setAcitvityurl(String acitvityurl) {
		this.acitvityurl = acitvityurl;
	}
}
