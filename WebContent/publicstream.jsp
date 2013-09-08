<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.opensymphony.xwork2.ActionContext;" %>

<div class="page-region">
<div class="page-region-content">
<div class="grid">
<div class="row">

        <div class="span3 bg-color-white" >
            	<p style="padding: 20px;"></p>
            </div>
            <div class="span9 bg-color-white">
            <h3>Activity Feed</h3>
            <hr />
            	<ul class="listview">
 					<s:iterator value="activityFeeds">
 						<li class="" style="width: 100%;">
                            <div class="icon">
                                <img src="<s:property value="userProfilePicUrl"/>" height="40px" width="40px">
                            </div>

                            <div class="data">
                           		<s:set name="mediaType" value="activityType"/>
                                <h4><s:property value="uname"/></h4> <br />
                                <s:if test="%{#mediaType == 2}">
                                <img src="<s:property value="activityData"/>" height="250px" width="400px">
                            	<h6><s:property value="uname"/> uploaded a new photo at <s:property value="activityDate"/></h6>
                            	</s:if>
                            	<s:elseif test="%{#mediaType == 1}">
                            	<h6><s:property value="uname"/> created a new album <s:property value="activityData"/> at <s:property value="activityDate"/></h6>
                            	</s:elseif>
                            	<s:elseif test="%{#mediaType == 3}">
                                <div class="flowplayer" style=" width: 400px">
  								<video> 
  								<source src="<s:property value="activityData"/>" type="video/mp4"></source>
  								</video>
  								</div>
                            	<h6><s:property value="uname"/> uploaded a new video at <s:property value="activityDate"/></h6>
                            	</s:elseif>
                            	<s:elseif test="%{#mediaType == 4}">
                            	<h6><s:property value="uname"/> created a new video channel <s:property value="activityData"/> at <s:property value="activityDate"/></h6>
                            	</s:elseif>
                            </div>
           				</li>
           				<hr/>
    				</s:iterator>
    			</ul>
            </div>
		</div>
    </div>
</div>
</div>
