package com.picvik.model;

public class ActivityType {
	
	private static int add_picture_activity_type_id = 1;
	private static int create_album_activity_type_id = 2;
	
	// TODO: Sandeep -> change names after jsp demo. 
	// Change it similar to the new names given to new video and new video channel activity
	private static int new_video = 3;
	private static int new_video_channel = 4;
	
	// Getters and Setters
	public static int get_Add_picture_activity_type_id() {
		return add_picture_activity_type_id;
	}
	
	public static int get_Create_album_activity_type_id() {
		return create_album_activity_type_id;
	}

	public static int getNew_video() {
		return new_video;
	}

	public static void setNew_video(int new_video) {
		ActivityType.new_video = new_video;
	}

	public static int getNew_video_channel() {
		return new_video_channel;
	}

	public static void setNew_video_channel(int new_video_channel) {
		ActivityType.new_video_channel = new_video_channel;
	}	
}