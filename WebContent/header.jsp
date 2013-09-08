<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="nav-bar bg-color-darken">
	<div class="nav-bar-inner padding10">
		<span class="element"><a class = "fg-color-white" href="home.action">PICVIK</a></span>
		<span class="divider"></span>
		<ul class="menu">
        <li>
        	<a href="home.action"><i class="icon-home"></i> Home</a>
        </li>
        
       	<s:if test="#session['login']!=null">
        		
                	<li data-role="dropdown">
                		<a href="#"><i class="icon-upload-2"></i> Upload</a>
        				<ul class="dropdown-menu">
                        	<li><a href="photouploader.action">Create photo album</a></li>
                        	<li><a href="videouploader.action">Create video channel</a></li>
                        	<li><a href="uploadphototoalbum.action">Upload photos to album</a></li>
                        	<li><a href="uploadvideotochannel.action">Upload videos to channel</a></li>
                    	</ul>
               		</li>
               		
               		<li data-role="dropdown">
                		<a href="#"><i class="icon-grid-view"></i> You</a>
        				<ul class="dropdown-menu">
        					<li><a href="publicstream.action">Stream</a></li>
                        	<li><a href="listalbum.action">Your photo albums</a></li>
                        	<li><a href="listchannel.action">Your video channels</a></li>
                    	</ul>
               		</li>
               		
               		<li data-role="dropdown">
                		<a href="#"><i class="icon-search"></i> Explore</a>
        				<ul class="dropdown-menu">
                        	<li><a href="explorealbum.action">Public photo albums</a></li>
                        	<li><a href="explorechannel.action">Public video channels</a></li>
                    	</ul>
               		</li>
 
         </s:if>
         
         			
        
       		 <s:if test="#session['login']==null">
       		 	<li class="place-right">
       				 <a href="registration.action">
       			 	<i class="icon-user-3"></i> Register</a>
       			</li>
       		 </s:if>
        
        
        
        	<s:if test="#session['login']!=null">
        		<li class="place-right">
        			<s:url id="logout" action="logout.action"/>
        			<s:a href="%{logout}"><i class="icon-unlocked"></i> Logout</s:a>
        		</li>
        	</s:if>
        	
        	<s:else>
        		<li class="place-right">
        			<s:url id="login" action="login.action"/>
        			<s:a href="%{login}"><i class="icon-locked"></i> Login</s:a>
        		</li>
       		</s:else>
        
        	<s:if test="#session['login']!=null">
        		<li class="place-right">
        			<s:url id="profile" action="viewprofile.action"/>
        			<s:a href="%{profile}"> <i class="icon-user-2"></i>
        			<s:property value="#session['uname']" /></s:a>   
        			<!--<img class = "place-right" src="<s:property value="#session['imgurl']" />" height="20" width="20" ></img>
        			 -->
        		</li>
        	
        	</s:if>	
        </ul>  				
	</div>
</div>