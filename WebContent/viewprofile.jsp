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
            <div class="span10">
            <s:iterator value="userprofile">
            	<div class="image-container light place-right">
        			<img src="<s:property value="img"/>" height="100" width="80"></img>
       		 		 <div class="overlay">  <a href="changeprofilephoto.action" class="button bg-color-red fg-color-white">Change Photo</a>  </div> 
   			 	</div>
   			
            <strong>Name: </strong><s:property value="name"/><br />
            <strong>Gender: </strong><s:property value="gender"/><br />
            <strong>Date Of Birth: </strong><s:property value="dob"/><br />
            <strong>Address Line1: </strong><s:property value="address1"/> <br />
            <strong>Address Line2: </strong><s:property value="address2"/> <br />
            <strong>City: </strong><s:property value="city"/> <br />
            <strong>State: </strong><s:property value="state"/> <br />
            <strong>Country: </strong><s:property value="country"/> <br />
            <strong>Zip: </strong><s:property value="zip"/><br /><br />
            
            <a href="editprofile.action" class="button default fg-color-white">Edit</a>
            </s:iterator>
            </div>
        </div>
    </div>
	</div>
</div>