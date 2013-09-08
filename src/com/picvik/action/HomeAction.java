/**
 * 
 */
package com.picvik.action;

import com.opensymphony.xwork2.ActionSupport;
import com.picvik.model.*;
import com.picvik.util.MyLog;

import java.util.*;

/**
 * @author Team404
 *
 */
public class HomeAction extends ActionSupport {
	
	public String execute() {
		MyLog.log("Inside HomeAction execute function");
		return SUCCESS;
	}
}
