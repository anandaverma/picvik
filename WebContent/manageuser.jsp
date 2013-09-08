<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.opensymphony.xwork2.ActionContext;" %>

<div class="page-region">
<div class="page-region-content">
<div class="grid">
<div class="row">
            <div class="span12 bg-color-white">
            <p style="padding: 20px;">Manage Users</p>
            <table class="striped">
                    <thead>
                        <tr>
                            <th>Profile Picture</th>
                            <th class="right">User Name</th>
                            <th class="right">DOB</th>
                            <th class="right">Email</th>
                            <th class="right">Status</th>
                            <th class="right">Action</th>
                        </tr>
                    </thead>

                    <tbody>
                    <s:iterator value="allUserProfiles">
                        <tr>
                        <td><img src="<s:property value="img"/>" height = "100px" width="100px"></td>
                        <td><s:property value="uname"/></td>
                        <td><s:property value="dob"/></td>
                        <td><s:property value="email"/></td>
                        <td>
                        <s:set name="userstatus" value="status"/>
                        <s:if test="%{#userstatus == 1}">
                        <center><p class="bg-color-green fg-color-white">Active</p></center>
                        </s:if> 
                        <s:elseif test="%{#userstatus == 0}">
                        <center><p class="bg-color-blueLight">Inactive</p></center>
                        </s:elseif>  
                        <s:elseif test="%{#userstatus == 2}">
                        <center><p class="bg-color-red fg-color-white">Banned</p></center>
                        </s:elseif> 
                        <s:else>
                        Unknown
                        </s:else>             
                        </td>
                        <td>
                        <s:set name="userstatus" value="status"/>
                        <s:if test="%{#userstatus == 1}">
                        <s:url action="banuser.action" var="urlTag" >
    					<s:param name="uid"><s:property value = "uid"/></s:param>
						</s:url>
						<a href="<s:property value="#urlTag" />" class="button "><i class="icon-blocked"></i>Block</a>
                        
                        </s:if> 
                        <s:elseif test="%{#userstatus == 0}">
                        <s:url action="activateuser.action" var="urlTag" >
    					<s:param name="uid"><s:property value = "uid"/></s:param>
						</s:url>
						<a href="<s:property value="#urlTag" />" class="button "><i class="icon-plus-2"></i>Activate</a>
                        
                        </s:elseif>  
                        <s:elseif test="%{#userstatus == 2}">
                        <s:url action="allowuser.action" var="urlTag" >
    					<s:param name="uid"><s:property value = "uid"/></s:param>
						</s:url>
						<a href="<s:property value="#urlTag" />" class="button "><i class="icon-checkmark"></i>Allow</a>
                        
                        </s:elseif> 
                        <s:else>
                        Unknown
                        </s:else>  
                        <s:url action="deleteuser.action" var="urlTag" >
    					<s:param name="uid"><s:property value = "uid"/></s:param>
						</s:url>
						<a href="<s:property value="#urlTag" />" class="button "><i class="icon-cancel"></i>Delete</a>
                        </td>
                        </tr>
                    </s:iterator>
                    </tbody>

                    <tfoot></tfoot>
                </table>
            	
            </div>
		</div>
    </div>
</div>
</div>
