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
            	<h3><i class="icon-user-3"></i> Verify Email</h3>
				<s:form action="verify.action" method ="post" validate="true">
					 <s:div cssClass="input-control text span3">
        				<s:textfield theme="simple" name="username" placeholder="Enter User Name" value="" />
      				    <span class="helper"></span>
   					 </s:div>
   					 <s:div cssClass="input-control text span4">
        				<s:textfield theme="simple" name="verificationcode" placeholder="Enter Verification Code" value=""/>
      				    <span class="helper"></span>
   					 </s:div>
   					 <s:if test="hasActionErrors()">
   						<div class="errors">
      						<s:actionerror/>
   						</div>
					</s:if>
   					 <s:submit theme="simple" value="Verify"/>
				</s:form>
			</div>
            <div class="span3"></div>
        </div>
    </div>
	</div>
</div>