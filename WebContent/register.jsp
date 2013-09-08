<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.opensymphony.xwork2.ActionContext;" %>

<div class="page-header">
	<div class="page-header-content">
	
	</div>
</div>

<div class="page-region">
	<div class="page-region-content">
		<div class="grid">
        <div class="row">
            <div class="span3"></div>
            <div class="span3">
            	<h3><i class="icon-user-3"></i> Register</h3>
				<s:form action="register.action" method ="post" validate="true">
					 <s:div cssClass="input-control text span3">
        				<s:textfield theme="simple" name="username" placeholder="Enter User Name" value="" />
      				    <span class="helper"></span>
   					 </s:div>
   					 <s:div cssClass="input-control text span3">
        				<s:textfield theme="simple" name="email" placeholder="Enter Email" value=""/>
      				    <span class="helper"></span>
   					 </s:div>
   					 <s:div cssClass="input-control text span3">
        				<s:password theme="simple" name="password" placeholder="Enter Password" value=""/>
      				    <span class="helper"></span>
   					 </s:div>
   					 <s:div cssClass="input-control text span3">
        				<s:password theme="simple" name="retypepassword" placeholder="Retype Password" value=""/>
      				    <span class="helper"></span>
   					 </s:div>
   					 <s:fielderror />
   					 <s:actionerror/>
   					 <s:submit theme="simple" value="Register"/>
   					 <s:reset theme="simple"  value="Reset"/>
				</s:form>
			</div>
            <div class="span3"></div>
        </div>
    </div>
	</div>
</div>