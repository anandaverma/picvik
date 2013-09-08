/**
 * 
 */
package com.picvik.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.picvik.model.AdminModel;
import com.picvik.model.LoginModel;
import com.picvik.model.RegisterModel;
import com.picvik.model.ViewProfileModel;
import com.picvik.util.MyLog;
import com.picvik.util.MyUtilityFunctions;
import com.picvik.util.RuntimeSettings;

import java.util.Map;

public class LoginAction extends ActionSupport
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String execute() {
        return INPUT;
    }
    
    public String login() {
    	MyLog.log("Inside LoginAction login function");
    	
    	LoginModel loginUser = new LoginModel();
    	
    	//validating login form
    	if(username.equals("") || password.equals("") ) {
			addActionError("Username/password required");
			return INPUT;
		}
    	else {
    		//checking whether user exist or not
    		if(loginUser.checkUsername(username)){
    			addActionError("Username does not exist.");
    			return INPUT;
    		}
    		//check user status
    		else if(loginUser.getUserStatus(username) == RuntimeSettings.statusInactive){
    			addActionError("Account not activated, Please your account.");
    			return INPUT;
    		}
    		else if(loginUser.getUserStatus(username) == RuntimeSettings.statusBanned){
    			addActionError("Account banned, Please contact Administrator.");
    			return INPUT;
    		}
    		//if user exists and account is active then get the password, 
    		//generate md5 hash and match with entered password
    		else if(loginUser.getPassword(username).equals(MyUtilityFunctions.generateMD5(password))) {
    			Map sess=ActionContext.getContext().getSession();
    			RegisterModel user = new RegisterModel();
    			ViewProfileModel profile = new ViewProfileModel();
    			sess.put("login", true);
    			sess.put("uname",username);
    			sess.put("uid",user.getUID(username));
    			//System.out.println(profile.getProfilePic(user.getUID(username)));
    			sess.put("imgurl", profile.getProfilePic(user.getUID(username)));
    			return SUCCESS;
    		}
    		else {
    		addActionError("Invalid username/password.");
    		return INPUT;
    		//errorMsg="Invalid username/password.";
    		}
    	}
    }
    
    public String logout() {
    	MyLog.log("Inside LoginAction logout function");
        Map sess=ActionContext.getContext().getSession();
        sess.remove("login");
        sess.remove("uname");
        sess.remove("uid");
        sess.remove("imgurl");
        return SUCCESS;
    }
    //admnistration
    public String adminLogin() {
        return INPUT;
    }
    public String doAdminLogin() {
    	MyLog.log("Inside LoginAction dologin function");
    	AdminModel loginAdmin = new AdminModel();
    	
    	//validating login form
    	if(username.equals("") || password.equals("") ) {
			addActionError("Username/password required");
			return INPUT;
		}
    	else {
    		//checking whether user exist or not
    		if(loginAdmin.getAdminName(username).equals("")){
    			addActionError("Username does not exist.");
    			return INPUT;
    		}

    		//generate md5 hash and match with entered password
    		else if(loginAdmin.getAdminPassword(username).equals(MyUtilityFunctions.generateMD5(password))) {
    			Map sess=ActionContext.getContext().getSession();
    			sess.put("login", true);
    			sess.put("uname",username);
    			return SUCCESS;
    		}
    		else {
    		addActionError("Invalid username/password.");
    		return INPUT;
    		//errorMsg="Invalid username/password.";
    		}
    	}
    }
    public String adminLogout() {
    	MyLog.log("Inside LoginAction adminLogout function");
    	Map sess=ActionContext.getContext().getSession();
        sess.remove("login");
        sess.remove("uname");
        return SUCCESS;
    }

}

