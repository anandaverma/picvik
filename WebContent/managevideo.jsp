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
                            <th>Video</th>
                            <th class="right">Title</th>
                            <th class="right">Description</th>
                            <th class="right">Action</th>
                        </tr>
                    </thead>

                    <tbody>
                    <s:iterator value="allVideos">
                        <tr>
                        <td>
                        <div class="flowplayer" style=" hieght: 200px; width: 200px">
  							<video> 
  							<source src="<s:property value="videourl"/>" type="video/mp4"></source>
  							</video>
							</div>
                        </td>
                        <td><s:property value="title"/></td>
                        <td><s:property value="description"/></td>
                        <td>
                        <s:url action="deletevideo.action" var="urlTag" >
    					<s:param name="mediaid"><s:property value = "videoid"/></s:param>
						</s:url>
						<a href="<s:property value="#urlTag" />" class="button "><i class="icon-remove"></i>Delete</a>
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
