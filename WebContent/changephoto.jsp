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
            	<s:actionerror />
				<s:form action="uploadprofilephoto" method="post" theme="simple" enctype="multipart/form-data">
    				<s:file name="userImage" label="User Image" /><br /><br />
    				<s:submit value="Upload" align="center" />
				</s:form>
				<s:fielderror />
   				<s:actionerror/>
			</div>
            <div class="span3"></div>
        </div>
    </div>
	</div>
</div>