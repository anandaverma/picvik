package com.picvik.model;

import java.util.Date;

public class ActivityModel {
	
	// Private Variables
	
		private int user_id;
		private int activity_type_id;
		private int activity_source_id;
		private int activity_source_type_id;
		private String data;
		private Date timeStamp; 
		
		// Getters and Setters
		
		public int get_User_id() {
			return user_id;
		}
		public void set_User_id(int user_id) {
			this.user_id = user_id;
		}
		public int get_Activity_type_id() {
			return activity_type_id;
		}
		public void set_Activity_type_id(int activity_type_id) {
			this.activity_type_id = activity_type_id;
		}
		public int get_Activity_source_id() {
			return activity_source_id;
		}
		public void set_Activity_source_id(int activity_source_id) {
			this.activity_source_id = activity_source_id;
		}
		public int get_Activity_source_type_id() {
			return activity_source_type_id;
		}
		public void set_Activity_source_type_id(int activity_source_type_id) {
			this.activity_source_type_id = activity_source_type_id;
		}
		public String get_Data() {
			return data;
		}
		public void set_Data(String data) {
			this.data = data;
		}
		public Date get_TimeStamp() {
			return timeStamp;
		}
		public void set_TimeStamp(Date timeStamp) {
			this.timeStamp = timeStamp;
		}		
}