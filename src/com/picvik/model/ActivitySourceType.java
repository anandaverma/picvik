package com.picvik.model;

public class ActivitySourceType {
	
	// Private Variables
	// TODO: Sandeep -> change names after jsp demo. 
	// Change it similar to the new names given to new video and new video channel activity
	private static int picture_album_type_id = 1;
	private static int picture_type_id = 2;	
	
	private static int new_video = 3;
	private static int new_video_channel = 4;
	
	// Getters and Setters
	
	public static int get_Picture_album_type_id() {
		return picture_album_type_id;
	}
	
	public static int get_Picture_type_id() {
		return picture_type_id;
	}

	public static int getNew_video() {
		return new_video;
	}

	public static void setNew_video(int new_video) {
		ActivitySourceType.new_video = new_video;
	}

	public static int getNew_video_channel() {
		return new_video_channel;
	}

	public static void setNew_video_channel(int new_video_channel) {
		ActivitySourceType.new_video_channel = new_video_channel;
	}
}