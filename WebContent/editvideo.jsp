<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.opensymphony.xwork2.ActionContext;"%>

<div class="page-header">
	<div class="page-header-content">
	
	</div>
</div>

<div class="page-region">
	<div class="page-region-content">
		<div class="grid">
        <h3>Create Video Channel</h3>
        <s:form action = "savevideos.action" method="post" theme="simple" validate="true" >
        <div class="input-control text span4">
        		<s:textfield name="channelname" placeholder="Give channel name..."/>
      			<span class="helper"></span>
   		</div>
   		<div class="input-control text span4">
        		<s:textfield name="videodesc" placeholder="Tell something about the channel..."/>
      			<span class="helper"></span>
   		</div>
   		<div class="input-control text span3">
        		<s:textfield name="location" placeholder="where is it created?"/>
      			<span class="helper"></span>
      			
   		</div>
   		<div class="input-control text span3">
   				<s:textfield name="takendate" id="datepicker" placeholder="What date?"/>
      			<span class="helper"></span>
      	</div>
      	<div class="input-control text span3">
      	<select name="channelprivacy">
  							<option value="1">Public</option>
 							<option value="2">Private</option>
						</select>
      	</div><br /><br />
   		<div class="row">
            <div>
            	<s:iterator value="tmpvideo">
            		<div>
        				<div class="input-control text span3">
        				<s:textfield name="videotitle" placeholder="Give it a title..."/> 
      				    <span class="helper"></span>
      				    <div class="flowplayer">
   							<video src="<s:property />"></video>
						</div>
      				    <s:textfield name="videodesc" placeholder="Tell something about it..."/> 
      				    <span class="helper"></span><br /><br />
      				    <select name="videoprivacy">
  							<option value="1">Public</option>
 							<option value="2">Private</option>
						</select>
   					 	</div>
   					 	</div>
   		
   			 	</s:iterator>
   			 </div>
   			 </div>
   			 <s:fielderror />
   			 <s:actionerror/>
   					 <s:submit value="Save"/>
   		</s:form>
        </div>
    </div>
	</div>