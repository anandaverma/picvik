package com.picvik.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.picvik.util.MyLog;

import java.util.Map;

public class LoginInterceptor implements Interceptor {

    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void init() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String intercept(ActionInvocation ai) throws Exception {
    	MyLog.log("Inside LoginInterceptor");
        ai.getInvocationContext();
		Map sess = ActionContext.getContext().getSession();
        if (sess.containsKey("login")) {
            return ai.invoke();
        }
        Object action = ai.getAction();
        if (action instanceof ValidationAware) {
            ((ValidationAware) action).addActionError("Unauthorized access. " +
            		"Please Login first.");
        }
        return "login";
    }
}