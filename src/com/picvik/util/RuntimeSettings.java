package com.picvik.util;

public class RuntimeSettings {
	public static final String APPLICATION_NAME = "picvik";
	public static final String VERSION_ID = " version 0.1"
			+ " dated October 28, 2012";
	public static final String SERVER_IP = "localhost";
	public static String databaseName = "picvik";
	public static String dbUserID = "root";
	public static String dbPassword = "password"; 
	public static String RUN_MODE = "Test"; // "Production"; //
	public static boolean IS_IN_DEBUG_MODE = true; // false; //
	public static int portNo = 3306;// 5432; //
	public static String driverClass = "com.mysql.jdbc.Driver";
	public static String smtpFrom = "picvik.iiitb@gmail.com";
	public static String smtpPassword = "iiitb.password";
	public static Integer statusActive = 1;
	public static Integer statusInactive = 0;
	public static Integer statusBanned = 2;
	public static Integer mediaAlbum = 1;
	public static Integer mediaPhoto = 2;
	public static Integer mediaChannel = 3;
	public static Integer mediaVideo = 4;
	
	//privacy constants
	public static Integer mediaPublic = 1;
	public static Integer mediaPrivate = 2;
	public static Integer mediaFriend = 3;
	
	//activity
	public static Integer ActivityTypeAlbum = 0;
	public static Integer ActivityTypePhoto = 1;
	public static Integer ActivityTypeChannel = 2;
	public static Integer ActivityTypeVideo = 3;
}
