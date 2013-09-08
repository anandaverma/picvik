<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.opensymphony.xwork2.ActionContext;" %>

<div class="page-header">
	<div class="page-header-content"><center>
		<a class="shortcut" href="listchannel.action">
       					<span class="icon">
            			<i class="icon-pictures"></i>
        				</span>
        				<span class="label">
            			All Channels
       					</span>
        				<span class="badge"><s:property value="totalchannel"/></span>
    					</a> 
    					
    					<a class="shortcut" href="listvideo.action">
       					<span class="icon">
            			<i class="icon-pictures"></i>
        				</span>
        				<span class="label">
            			All Videos
       					</span>
        				<span class="badge"><s:property value="totalvideo"/></span>
    					</a>
    					
	</center></div>
</div>

<div class="page-region">
		<div class="page-region-content">
			<div class="grid">
				<div class="row">
					<div class="span10"> 					 
         			</div>
         		</div>
				<div class="row">
				<h3>List of All Videos</h3>
					<div class="span12">
					<div class="image-collection ">			
						<s:iterator value="allVideos">
						<div>
						<s:url action="viewvideo.action" var="urlTag" >
    					<s:param name="vidid"><s:property value = "videoid"/></s:param>
						</s:url>
						<a href="<s:property value="#urlTag" />" >
						<div class="flowplayer">
  							<video src="<s:property value="videourl"/>"></video>
						</div> 
						</a>
						<div class="overlay"><s:property value ="title" /></div>
						</div>
                        </s:iterator>
                         </div>
         			</div>
         		</div>
         	</div> 
		</div>
	</div>
