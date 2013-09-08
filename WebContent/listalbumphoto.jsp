<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.opensymphony.xwork2.ActionContext;" %>

<div class="page-header">
	<div class="page-header-content"><center>
		<a class="shortcut" href="listalbum.action">
       					<span class="icon">
            			<i class="icon-pictures"></i>
        				</span>
        				<span class="label">
            			All Albums
       					</span>
        				<span class="badge"><s:property value="totalalbum"/></span>
    					</a> 
    					
    					<a class="shortcut" href="listphoto.action">
       					<span class="icon">
            			<i class="icon-pictures"></i>
        				</span>
        				<span class="label">
            			All Photos
       					</span>
        				<span class="badge"><s:property value="totalphoto"/></span>
    					</a>
    					
	</center></div>
</div>

<div class="page-region">
		<div class="page-region-content">
			<div class="grid">
				<div class="row">
					<div class="span12"><center>
					<div>
						<s:iterator value="allAlbums">
						<h2><s:property value="albumname"/></h2>
						<s:property value="date"/> ·<i class="icon-calendar">
						</i>· Taken at <s:property value="location"/>
						</s:iterator>
					</div></center>	
    					<a class="button default"><i class="icon-plus-2"></i> Add Photos</a> 
    					<a class="button"><i class="icon-pencil"></i> Edit</a>
         			</div>
         		</div>
				<div class="row">
				<h3>Album Photos</h3>
					<div class="span10">
					<div class="image-collection ">			
						<s:iterator value="allPhotos">
						<div>
						<s:url action="viewphoto.action" var="urlTag" >
    					<s:param name="pictureid"><s:property value = "pictureid"/></s:param>
						</s:url>
						<a href="<s:property value="#urlTag" />" >
						<img src="<s:property value="photourl"/>" alt=""> 
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
                                    <s:form action="commentonalbum.action" theme="simple" method ="post" >
					
					 				<div class="input-control text span3">
					 				
        							<s:textarea  name="comment" placeholder="Write a comment..." value=""/>
      				    			<span class="helper"></span>
   									</div>
   									<s:iterator value="allAlbums">
   									<s:hidden name="mediaid" value="%{albumid}" />
					 				<s:hidden name="mediatype" value="1" />
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