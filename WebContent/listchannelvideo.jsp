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
					<div class="span12"><center>
					<div>
						<s:iterator value="allChannels">
						<h2><s:property value="channelname"/></h2>
						<s:property value="date"/> ·<i class="icon-calendar">
						</i>· Recorded at <s:property value="location"/>
						</s:iterator>
					</div></center>	
    					<a class="button default"><i class="icon-plus-2"></i> Add Videos</a> 
    					<a class="button"><i class="icon-pencil"></i> Edit</a>
         			</div>
         		</div>
				<div class="row">
				<h3>Channel Videos</h3>
					<div class="span10">
					
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
         		<div class="span2">
         			<ul class="listview">
         			<s:iterator value="allcomments">
                       <li class="bg-color-red fg-color-white">
                            <div class="icon">
                                <img class = "place-right" src="<s:property value="imgurl" />" height="40px" width="40px">
                            </div>
                            <div class="data">
                            <h4 class="fg-color-white"><s:property value="name" /></h4>
                                <p>
                                    <s:property value="comment" />
                                </p>
                            </div>
                        </li>
                        </s:iterator> 
                        <li class="bg-color-blueDark fg-color-white">
                            <div class="icon">
                                <img class = "place-right" src="<s:property value="#session['imgurl']" />" height="40px" width="40px">
                            </div>
                            <div class="data">
                            <h4 class="fg-color-white"><s:property value="name" /></h4>
                                <p>
                                    <s:form action="commentonchannel.action" theme="simple" method ="post" >
					
					 				<div class="input-control text span3">
					 				
        							<s:textarea  name="comment" placeholder="Write a comment..." value=""/>
      				    			<span class="helper"></span>
   									</div>
   									<s:iterator value="allChannels">
   									<s:hidden name="mediaid" value="%{channelid}" />
					 				<s:hidden name="mediatype" value="3" />
   									</s:iterator>
   									<s:actionerror/>
   									
   									<s:submit value="comment"/>
   									</s:form>
                                </p>
                            </div>
                        </li>
                    </ul>
   					             
    			</div>
         	</div> 
		</div>
	</div>