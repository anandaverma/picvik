/**
 * 
 */
package com.picvik.action;

import com.picvik.model.RegisterModel;
import com.picvik.model.ViewProfileModel;
import com.picvik.util.MyLog;
import com.picvik.util.MyUtilityFunctions;
import com.picvik.util.RuntimeSettings;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Team404
 *
 */
public class RegisterAction extends ActionSupport {

	/**
	 * @param args
	 */
	
	private String username;
	private String email;
	private String password;
	private String retypepassword;
	private String verificationcode;
	
	
	public String getVerificationcode() {
		return verificationcode;
	}


	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRetypepassword() {
		return retypepassword;
	}


	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

	//this action is called by default 
	public String execute() {
		//System.out.println("register");
		return "success";
		
	}
	
	//this action is called when user fills and submit registration form
	//RegisterAction-register-validation.xml is used for validation of form
	public String register() {
		MyLog.log("Inside RegisterAction register function");
		//System.out.println("register");
		String ret;
		RegisterModel createUser = new RegisterModel();
		ViewProfileModel userProfile = new ViewProfileModel();
		EmailAction sendverificationmail = new EmailAction();
		
		//username and email validation from database
		if(!createUser.checkUsername(username).equals("")){
			addActionError("Username already taken, try a different one.");
			return "error";
		}
		if(!createUser.checkEmail(email).equals("")){
			addActionError("Email already exists.");
			return "error";
		}
		
		//create user and insert into user table
		ret = createUser.registerUser(username, email, password);
		//System.out.println("user created");
		
		//create dummy profile
		userProfile.createDummyProfile(createUser.getUID(username));
		
		//get uid from user table
		int uid = createUser.getUID(username);
		//System.out.println("getID done");
		
		//create random key
		String verificationurl = MyUtilityFunctions.createVerificationUrl();
		
		//insert activation code to user activation table
		ret = createUser.userActivation(verificationurl, uid);
		System.out.println(ret);
		
		//send user verification email
		ret = sendverificationmail.sendverification(username, email,verificationurl);
		System.out.println(ret);
		return ret;
		
	}
	public String verify(){
		MyLog.log("Inside RegisterAction verify function");

		if(username == null || verificationcode == null ) {
			return "error";
		}
		
		//validating verification form
		if(username.equals("") || verificationcode.equals("") ) {
			addActionError("Username/Verification code required");
			return "error";
		} 
		else {
		RegisterModel verifyUser = new RegisterModel();
		EmailAction sendActivationMail = new EmailAction();
		
		//get uid from user table
		int uid = verifyUser.getUID(username);
		
		//check whether user exists or not
		if (uid == -1) {
			addActionError("Invalid Username");
			return "error";
		}
		else {
			//System.out.println("here");
			
			//compare verification keys
			String keymatch = verifyUser.getKey(uid);
			
			//get email from user table
			String mail= verifyUser.getEmail(uid);
			
			//System.out.println(keymatch);
			if (keymatch.equals(verificationcode)){
				verifyUser.updateUserStatus(uid, RuntimeSettings.statusActive);
				sendActivationMail.sendActivation(username, mail);
				verifyUser.deleteUserActivationEntry(uid);
				//addActionError("Valid Username");
			}
			else if(keymatch.equals("")){
				addActionError("Already Activated");
				return "error";
			}
			else {
				addActionError("Invalid Activation key");
				return "error";
			}
		}
		}
		return "success";
	}

}

