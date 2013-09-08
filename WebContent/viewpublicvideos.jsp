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
        				<span class="badge"><s:property value="totalchannel"/></span>
    					</a> 
    					
    					<a class="shortcut" href="explorevideo.action">
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
					<div class="span12">
					<div>
						<s:iterator value="allVideos">
						<h2><s:property value="channelname"/></h2>
						<h5><s:property value="channeldescription"/></h5>
						<s:property value="channeldate"/> ·<i class="icon-calendar">
						</i>· Recorded at <s:property value="channellocation"/>
						<h4><s:property value="title"/></h2>
						<h6><s:property value="description"/></h6>
						
						</s:iterator>
					</div>
    					 
    					<a class="button"><i class="icon-pencil"></i> Edit</a>
         			</div>
         		</div>
				<div class="row">
					<div class="span12">
					
					<div class="">			
						<s:iterator value="allVideos">
                         <div>
                          <!-- <a href="<s:property value="videourl"/>" rel="prettyPhoto" title="<s:property value ="title" />">
                          	 -->
                          	<div class="flowplayer" style=" width: 800px">
  							<video> 
  							<source src="<s:property value="videourl"/>" type="video/mp4"></source>
  							</video>
							</div> 
							
						  <!-- </a>  -->                      
                		 </div>
                           
                        </s:iterator>
                         </div>
         			</div>
         		</div>
         		<div class="row">
         		<div class="span12"> 
         			<ul class="listview">
         			<s:iterator value="allcomments">
                       <li class="bg-color-red fg-color-white" style="width: 500px;">
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
                                    <s:form action="commentonvideo.action" theme="simple" method ="post" >
					
					 				<div class="input-control text span3">
					 				
        							<s:textarea  name="comment" placeholder="Write a comment..." value=""/>
      				    			<span class="helper"></span>
   									</div>
   									<s:iterator value="allVideos">
   									<s:hidden name="mediaid" value="%{videoid}" />
					 				<s:hidden name="mediatype" value="4" />
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