<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.opensymphony.xwork2.ActionContext;"%>

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
            <h3>Upload Photos</h3>
				<s:form action="uploadphoto" method="post" theme="simple" enctype="multipart/form-data">
    				<s:file label="File 1" name="fileUpload" size="40" /><br /><br />
					<s:file label="File 2" name="fileUpload" size="40" /><br /><br />
					<s:file label="FIle 3" name="fileUpload" size="40" /><br /><br />
					<s:file label="File 4" name="fileUpload" size="40" /><br /><br />
					<s:file label="File 5" name="fileUpload" size="40" /><br /><br />
    				<s:submit value="Upload" align="center" />
				</s:form>
            </div>
            <s:fielderror />
   			<s:actionerror/>	
            <div class="span3"></div>
        </div>
    </div>
	</div>
</div>