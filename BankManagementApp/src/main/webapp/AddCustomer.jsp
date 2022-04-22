<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Details.Customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Account</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css"> -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"> -->
<link href="commonstyle.css" type="text/css" rel="stylesheet">
<link href="label.css" type="text/css" rel="stylesheet">
<link href="button.css" type="text/css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
div
{
float:right;
}
</style>
</head>
<body>
<jsp:include page="Header.jsp" />
<%if(true)
{
%>
<h1><b>ADD CUSTOMER</b></h1>
<jsp:include page="sidebar.jsp" />
<div>
<!-- <form class="operation" style="text-align:center" action="Add?action=Customer" method="post" onsubmit="return negativeNeglect(id)" name="myForm" id="myForm"> -->
<form class="operation" id="myForm">
 <!-- <input type="hidden" name="action" value="Customer"> -->
<label for="name">Name: </label>
<br>
<br>
<input type="text" placeholder="name" onchange="myFunction()"name="name" id="name" required>
<br><br>
<label for="Dob">Date Of Birth: </label>
<br><br>
<input type="date" placeholder="DOB" name="dob" id="dob" required>
<br><br>
<label for="Address">Address: </label>
<br><br>
<input type="text" placeholder="Address" name="address" id="address" onchange="addressValidate(id)"required>
<br><br> 
<label for="phNo">PhoneNumber: </label>
<br><br>
<input type="number" placeholder="PhoneNumber" onchange="negativeNeglect(id)" name="phone" id="phone" min="1" maxlength="10" required>
<br><br> 
<input class="button" type="button" id="button" value="Add"/>
<br><br>
<p id="result"></p>
</form>
</div>
<%
}
else if(request.getParameter("class").equals("update"))
{
%>
<h1><b>UPDATE CUSTOMER</b></h1>
<jsp:include page="sidebar.jsp" />
<div>
<form class="operation" onsubmit="return negativeNeglect(id)" name="myForm" id="myForm">
<%
Customer obj=(Customer) session.getAttribute("Customer");
%>
<h1>Update</h1>
<label for="customerId">Customer ID</label>
<br>
<Input type="text" onchange="myFunction(id)" name="customerId" id="customerId" value="<%=request.getParameter("customerId")%>" readonly="readonly">
<br>
<label for=name>Name:</label>
<br>
<input type="text" name="name" id="name" onchange="myFunction(id)" value="<%=obj.getName()%>" required>
<br>
<label for="dob">Date of Birth:</label>
<br>
<input type="date" name="dob" id="dob" value="<%=obj.getDob()%>" required>
<br>
<label for="address">Address:</label>
<br>
<input type="text" name="address" id="address" onchange="addressValidate(id)" value="<%=obj.getAddress()%>" required>
<br>
<label for="phone">Phone number:</label>
<br>
<input type="number" name="phone" id="phone" value="<%=obj.getPhoneNumber()%>" min="1" maxlength="10" required>
<br>
<br>
<input type="hidden" name="customer" value="customer">
<button class="button" type="submit" formaction="UpdateCustomer" formmethod="post">Update</button>
<br>
<br>
</form>
</div>
<%
}
%>
<h4 class=warning><marquee><%String message=(String) request.getAttribute("message");
if(message==null)
{}
else
{
	out.println(message);
}
%></marquee>
</h4>
<script>
function myFunction()
{
	  var x=event.target;
	  x.value = x.value.replaceAll(/[^ a-zA-Z.]/g,"");
	  x.value = x.value.trim();
}
function addressValidate(id)
{
	  var x = document.getElementById(id);
	  x.value = x.value.replaceAll(/[^ /a-zA-Z.]/g,"");
	  x.value = x.value.trim();
}
function negativeNeglect(id)
{
	  var x = document.myForm.phone;
	  if(Math.sign(x.value)==(-1))
	  {
		  alert("You are tried to enter the negative value");
		  return false;
	  }
	  if(x.value.length>10 || x.value.length<10)
	  {
		  alert("Phone number should be 10 digits");
		  return false;
	  }
}
$("#button").click(function()
{
	console.log($("#name").val());
var set={
		'naaame':$("#name").val(),
        'dob':$("#dob").val(),
        'address':$("#address").val(),
        'phone':$("#phone").val(),
        };
// 	      $.ajax(
// 	    		  {
// 	    		 url:'/Add?action=Customer',
// 	    		 type:'POST',
// 	    	     data:set,
// 	    	     dataType:"json",
// 	    	     contentType:"application/json",
// 	    		 success:function(result)
// 	    		  {
// 	    			  confirm("The action you did is Success");
// 	    	          console.log(result);
// 	    		  },
// 	    		  error:function(data)
// 	    			  {
// 	    			  console.log($("#name").val());
// 	    			  console.log("Failed"+data.code);
// 	    			  console.log(data.code);
// 	    			     confirm("The action you did is fail");
// 	    			     $('#llo').text("Failed");
// 	    			  }
// 	    		  }
// 	    		 );
		$.post( "Add?action=customer", set ,function( data ) 
				{
			        console.log(data.responseText);
			        if(data=="Sucessfully inserted")
			        	{
				        	$( "#result" ).html( data ).css({
				 				'font-weight' : 'bold',
				 			   	'color' : 'green',
				 			 });
			        	}
			        else
			        	{
			        	console.log("Error");
			        	$( "#result" ).html( "ERROR" ).css({
			 				'font-weight' : 'bold',
			 			   	'color' : 'red',
			 			 });
			        	}
			 			 
				}).fail(function()
						{
							console.log("Error");
						});
	      event.preventDefault();
			return;
	      }
	      );
</script>
</body>
</html>