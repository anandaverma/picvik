<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="nav-bar bg-color-darken">
	<div class="nav-bar-inner padding10">
		<span class="element"><a class = "fg-color-white" href="#">PICVIK</a></span>
		<span class="divider"></span>
		<ul class="menu">
        <li>
        	
        </li>
        
       	<s:if test="#session['login']!=null">
        		
                	<li data-role="dropdown">
                		<a href="#"><i class="icon-cog"></i> Manage</a>
        				<ul class="dropdown-menu">
                        	<li><a href="manageuser.action">Manage Users</a></li>
                        	<li><a href="managephoto.action">Manage Photos</a></li>
                        	<li><a href="managevideo.action">Manage Videos</a></li>
                    	</ul>
               		</li>
         </s:if>

        	<s:if test="#session['login']!=null">
        		<li class="place-right">
        			<s:url id="logout" action="adminlogout.action"/>
        			<s:a href="%{logout}"><i class="icon-unlocked"></i> Logout</s:a>
        		</li>
        	</s:if>
        	
        
        	<s:if test="#session['login']!=null">
        		<li class="place-right">
        			<a><i class="icon-user-2"></i>
        			<s:property value="#session['uname']" /></a>  
        			<!--<img class = "place-right" src="<s:property value="#session['imgurl']" />" height="20" width="20" ></img>
        			 -->
        		</li>
        	
        	</s:if>	
        </ul>  				
	</div>
</div>