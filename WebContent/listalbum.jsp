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
					<div class="span12">
						
    					 
         			</div>
         		</div>
				<div class="row">
				<h3>List of All Albums</h3>
					<div class="span12">
						<s:iterator var="album" value="allAlbums">	
						<s:url action="listalbumphoto.action" var="urlTag" >
    					<s:param name="albumid"><s:property value = "albumid"/></s:param>
						</s:url>	
						<a href="<s:property value="#urlTag" />"><div class="tile double image-set">	
							<s:iterator var="photo" value="allPhotos.{? #this.albumid == #album.albumid}">
			
                                    <div class="tile-content">
                                        <img src="<s:property value="photourl"/>" alt="">
                                    </div>
                               		
         						
         					</s:iterator> 
         					<div class="brand">
                            <span class="name"><s:property value="albumname"/></span>
                            <div class="badge bg-color-orange"><s:property value="totalphotos"/> </div>
                        	</div>
         					</div></a>
         				</s:iterator>
         				 
         			</div>
         		</div>
         	</div> 
		</div>
	</div>
