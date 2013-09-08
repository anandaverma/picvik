<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.opensymphony.xwork2.ActionContext;" %>

<div class="page-header">
	<div class="page-header-content">
	</div>
</div>

<div class="page-region">
	<div class="page-region-content">
		<div class="grid">
            <s:iterator value="userprofile">
           		<div class="row">
            	<div class="span3">
            	   				
   				<s:form action="saveprofile.action" method ="post" theme="simple" validate="true">
				
					 <div class="input-control text span3">
        				<s:textfield theme="simple" name="name" placeholder="Enter Name" value="%{name}"/>
      				    <span class="helper"></span>
   					 </div>
   
   					 	 <label class="radiobox" onclick="">
                                    <input type="radio" name="gender" value="male" <s:if test="gender=='male'">checked</s:if>>
                                    <span>Male</span>
                                </label>
                                <label class="radiobox" onclick="">
                                    <input type="radio" name="gender" value="female" <s:if test="gender=='female'">checked</s:if>>
                                    <span>Female</span>
                      	</label>
   					 <br /><br />
   					 <div class="input-control text span3">
   		
        				<s:textfield theme="simple" id="datepicker" name="dateofbirth" value="%{dob}"/>
        				<span class="helper"></span>
   					 </div>
   					 
   					 <div class="input-control text span3">
        				<s:textfield theme="simple" name="address1" placeholder="Enter Address Line 1" value="%{address1}"/>
      				    <span class="helper"></span>
   					 </div>
   					 
   					 <div class="input-control text span3">
        				<s:textfield theme="simple" name="address2" placeholder="Enter Address Line 2" value="%{address2}"/>
      				    <span class="helper"></span>
   					 </div>
   					 
   					 <div class="input-control text span3">
        				<s:textfield theme="simple" name="city" placeholder="Enter City" value="%{city}"/>
      				    <span class="helper"></span>
   					 </div>
   					 
   					 <div class="input-control text span3">
        				<s:textfield theme="simple" name="state" placeholder="Enter State" value="%{state}"/>
      				    <span class="helper"></span>
   					 </div>
   					 
   					 <div class="input-control text span3">
        				<s:textfield theme="simple" name="country" placeholder="Enter Country" value="%{country}"/>
      				    <span class="helper"></span>
   					 </div>
   					 
   					 <div class="input-control text span3">
        				<s:textfield theme="simple" name="zip" placeholder="Enter Zipcode" value="%{zip}"/>
      				    <span class="helper"></span>
   					 </div>
   					 
   					 <s:actionerror/>
   					 <s:submit theme="simple" value="Save"/>
				</s:form>
   				</div>
            	<div class="span3"></div>
            	<div class="span3">
            		<div class="image-container light place-right">
        			<img src="<s:property value="img"/>" height="100" width="80"></img>
       		 		<div class="overlay"> <a href="changeprofilephoto.action" class="button bg-color-red fg-color-white">Change Photo</a> </div>  
   			 	</div>
            	</div>
        	</div>
            </s:iterator>
            <s:actionerror/>
            <s:fielderror />
    </div>
	</div>
</div>