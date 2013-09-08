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
					<div class="span10"> 					 
         			</div>
         		</div>
				<div class="row">
				<h3>List of All Photos</h3>
					<div class="span12">
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
         		</div>
         	</div> 
		</div>
	</div>
