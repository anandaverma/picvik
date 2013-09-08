<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.opensymphony.xwork2.ActionContext;" %>

<div class="page-header">
	<div class="page-header-content"><center>
		<a class="shortcut" href="explorechannel.action">
       					<span class="icon">
            			<i class="icon-pictures"></i>
        				</span>
        				<span class="label">
            			All Channels
       					</span>
        				<span class="badge"></span>
    					</a> 
    					
    					<a class="shortcut" href="explorevideo.action">
       					<span class="icon">
            			<i class="icon-pictures"></i>
        				</span>
        				<span class="label">
            			All Videos
       					</span>
        				<span class="badge"></span>
    					</a>
    					
	</center></div>
</div>

<div class="page-region">
		<div class="page-region-content">
			<div class="grid">
				<div class="row">
					<div class="span12">
						
    					 
         			</div>
         		</div>
				<div class="row">
				<h3>List of Channels</h3>
					<div class="span12">
						<s:iterator var="channel" value="allChannels">	
						<s:url action="explorechannelvideo.action" var="urlTag" >
    					<s:param name="channelid"><s:property value = "channelid"/></s:param>
						</s:url>	
						<a href="<s:property value="#urlTag" />"><div class="tile double image-set">	
							<s:iterator var="video" value="allVideos.{? #this.channelid == #channel.channelid}">
			
                                    <div class="tile-content">
                                    	<div class="flowplayer">
  										 <video src="<s:property value="videourl"/>"></video>
										</div>
                                    </div>
                               		
         						
         					</s:iterator> 
         					<div class="brand">
                            <span class="name"><s:property value="channelname"/></span>
                            <div class="badge bg-color-orange"><s:property value="totalvideos"/> </div>
                        	</div>
         					</div></a>
         				</s:iterator>
         				 
         			</div>
         		</div>
         	</div> 
		</div>
	</div>
