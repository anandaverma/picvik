package com.picvik.util;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;


/**
 * filter to optionally print request parameters and compress output
 * copied from "Head first servlets and jsp" by Bryan Basham, Kathy Sierra, 
 *     and Bert Bates
 * @author abbas
 */
public class MyFilter implements Filter {

    private FilterConfig fc;
    private ServletContext ctx;

    public void init(FilterConfig config) {
        this.fc = config;
        ctx = fc.getServletContext();
        ctx.log(fc.getFilterName() + " initialized.");
        MyLog.log(fc.getFilterName() + " initialized.");
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        MyLog.log("in MyFilter.doFilter with " +
                request.getPathInfo()); // + " @ " + Util1.getStackTrace());
        MyLog.log("requestURI:" + request.getRequestURI());
        if (RuntimeSettings.IS_IN_DEBUG_MODE) {
            displayRequestParameters(request);
            MyLog.log("will NOT compress the response");
            chain.doFilter(req, resp);
            MyLog.log("response:" + resp);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
        fc = null;
        ctx = null;
    }

    private void displayRequestParameters(HttpServletRequest request) {
        Map requestParameters = request.getParameterMap();
        Set requestKeys = requestParameters.keySet();
        Object[] requestArray = requestKeys.toArray();
        for (int i = 0; i < requestArray.length; i++) {
            String keyValue = (String) requestArray[i];
            String paramValues = "Parameter: " + keyValue + ", value:";
            String[] formValues = (String[]) requestParameters.get(keyValue);
            for (int j = 0; j < formValues.length; j++) {
                paramValues += " - " + formValues[j] + ", ";
            }
            MyLog.log(paramValues);
        }
        System.out.println("will display values of cookies");
        MyLog.log("will display values of cookies");
//        Cookie[] cookies = request.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            Cookie cookie = cookies[i];
//            MyLog.log("Cookie " + cookie.getName() + " = " + cookie.getValue());
//        }
        MyLog.log("will display values of session variables");
        HttpSession session = request.getSession();
        Enumeration sessionKeys = session.getAttributeNames();
        while (sessionKeys.hasMoreElements()) {
            String sessionKey = (String)sessionKeys.nextElement();
            Object value = session.getAttribute(sessionKey);
            MyLog.log("Session attribute:" + sessionKey + " = " + value);
        }
        

    }
}
