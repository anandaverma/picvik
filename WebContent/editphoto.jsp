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
        <h3>Create Photo Album</h3>
        <s:form action = "savephotos.action" method="post" theme="simple" validate="true">
        <div class="input-control text span4">
        		<s:textfield name="albumname" placeholder="Give album name..."/>
      			<span class="helper"></span>
   		</div>
   		<div class="input-control text span4">
        		<s:textfield name="albumdesc" placeholder="Tell something about the album..."/>
      			<span class="helper"></span>
   		</div>
   		<div class="input-control text span3">
        		<s:textfield name="location" placeholder="where is it taken?"/>
      			<span class="helper"></span>
      			
   		</div>
   		<div class="input-control text span3">
   				<s:textfield name="takendate" id="datepicker" placeholder="What date?"/>
      			<span class="helper"></span>
      	</div>
      	<div class="input-control text span3">
      	<select name="albumprivacy">
  							<option value="1">Public</option>
 							<option value="2">Private</option>
						</select>
      	</div><br /><br />
   		<div class="row">
            <div>
            	<s:iterator value="tmppics">
            		<div>
        				<div class="input-control text span3">
        				<s:textfield name="phototitle" placeholder="Give it a title..."/> 
      				    <span class="helper"></span>
      				    <img src="<s:property />" height="180" width="180"></img>
      				    <s:textfield name="photodesc" placeholder="Tell something about it..."/> 
      				    <span class="helper"></span><br /><br />
      				    <select name="photoprivacy">
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