package com.picvik.action;

import java.util.ArrayList;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
import com.picvik.model.Activity;
import com.picvik.model.ActivityFeedInformation;
import com.picvik.model.ActivityModel;
import com.picvik.util.MyLog;

public class StreamAction extends ActionSupport{

		private ArrayList<ActivityFeedInformation> activityFeeds;
		private Activity activity = new Activity();
		
		/*
		public ArrayList<ActivityModel> getActivityFeeds() {
			return activityFeeds;
		}

		public void setActivityFeeds(ArrayList<ActivityModel> activityFeeds) {
			this.activityFeeds = activityFeeds;
		}*/

		public Activity getActivity() {
			return activity;
		}

		public void setActivity(Activity activity) {
			this.activity = activity;
		}

		public String execute() {
			//System.out.println("home");
			MyLog.log("Inside StreamAction execute function");
			activityFeeds = activity.getActivityFeeds();
			for (int i=0; i<activityFeeds.size(); i++) {
			System.out.println(activityFeeds.get(i).getActivityData());
			System.out.println(activityFeeds.get(i).getActivityDate());
			}
			return "success";		
		}

		public ArrayList<ActivityFeedInformation> getActivityFeeds() {
			return activityFeeds;
		}

		public void setActivityFeeds(ArrayList<ActivityFeedInformation> activityFeeds) {
			this.activityFeeds = activityFeeds;
		}

}
