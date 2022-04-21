<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="Details.AccountDetails"%>
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
<style>
div
{
float:right;
}
div.search_select_box
{
height:40px;
margin-left:50px;
}
</style>
</head>
<body>
<%
if(request.getParameter("moneyexchange").equals("addAccount"))
{
%>
<jsp:include page="Header.jsp" />
<%if(session.getAttribute("lastId")==null)
{
	RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
    rd.forward(request, response);
} %>
<h1><b>ADD ACCOUNT</b></h1>
<jsp:include page="sidebar.jsp" />
<div>
<form class="operation" style="text-align:center" action="Add" method="post" onsubmit="return negativeNeglect(id);" name="myform" id="myform">
<input type="hidden" name="action" value="Account">
<h1>Add Account</h1>
<label for="CustomerId">Customer Id: </label><br><br>
<div class="search_select_box">
<select name="cars" id="cars" data-live-search="true">
<option value="0">select</option>
<%List<Integer> obj=(List<Integer>) request.getAttribute("AllId"); 
  for(Integer i:obj)
  {
	  
%>
<option name="cusId" value="<%=i%>"><%=i%></option>
<%
}
%>
</select>
</div>
<br>
<br>
<br>
<label for="branch">Branch: </label><br><br>
<div class="search_select_box">
<select name="branch" id="branch" data-live-search="true">
<option value="0">select</option>
<%List<String> obj1=(List<String>) request.getAttribute("Branch"); 
  for(String branch:obj1)
  {
	  
%>
<option value="<%=branch%>"><%=branch%></option>
<%
}
%>
</select>
</div>
<br>
<br>
<br>
<label for="Balance">Balance: </label><br><br>
<input type="number" placeholder="Balance" name="Balance" id="Balance" required><br><br> 
<button type="submit">Add</button><br><br>
</form>
</div>
<%
}
else if(request.getParameter("moneyexchange").equals("update"))
{
%>
<h1><b>UPDATE ACCOUNT</b></h1>
<jsp:include page="sidebar.jsp" />
<div>
<form class="operation" name="myform" id="myform">
<%AccountDetails obj=(AccountDetails) session.getAttribute("Account"); %>
<h1><b>UPDATE</b><h1>
<label for="customerId">Customer ID</label>
<br>
<Input type="text" name="customerId" id="customerId" value="<%=request.getParameter("acc")%>" readonly="readonly">
<br>
<label for="accId">AccountId</label>
<br>
<Input type="text" name="accId" id="accId" value="<%=request.getParameter("accNo")%>" readonly="readonly">
<br>
<label for="branch">branch</label>
<br>
<div class="search_select_box">
<select name="branch" id="branch" value="<%=obj.getBranch()%>" data-live-search="true">
<option value="<%=obj.getBranch()%>"><%=obj.getBranch()%></option>
<%List<String> obj1=(List<String>) request.getAttribute("Branch"); 
  for(String branch:obj1)
  {
%>
<option value="<%=branch%>"><%=branch%></option>
<%
}
%>
</select>
</div>
<br>
<input type="hidden" name="customer" value="account">
<button type="submit" formaction="UpdateCustomer" formmethod="post">Update</button>
<br>
</form>
</div>
<%
}
else if(request.getParameter("moneyexchange").equals("status"))
{
%>
<h1><b>ACCOUNT DEACTIVE/ACTIVE</b></h1>
<jsp:include page="sidebar.jsp" />
<div>
<form class="operation">
<h1>Activate/Deactive</h1>
<Label for="cusId">CustomerId:</Label>
<br>
<br>
<input type="text" id="cusId" name="cusId" value="<%=request.getParameter("cusId")%>">
<br>
<br>
<Label for="accId">AccountId:</Label>
<br>
<br>
<input type="text" id="accId" name="accId" value="<%=request.getParameter("accId")%>">
<br>
<br>
<label for="status">Status:</label>
<br>
<br>
<select name="status" id="status">
<option value="0">Deactivate</option>
<option value="1">Activate</option>
</select>
<br>
<br>
<input type="hidden" name="status1" value="account">
<!-- <button type="submit" formaction="CustomerStatus?status1=account" formmethod="post">Submit</button> -->
<input type="button" id="statusButton" value="submit" />
<p id="result"></p>
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
function negativeNeglect(id)
{
	  var x = document.myform.Balance;
	  if(Math.sign(x.value)==(-1))
	  {
		  alert("Amount should not be negative");
		  return false;
	  }
	   var x=document.myform.cars.value;
	    var y=document.myform.branch.value;
		if(x==0)
			{
			   alert("Select valid Customer Id");
			   return false;
			}
		else if(y==0)
			{
			   alert("Select the branch");
			   return false;
			}
		else
			{
			return true;
			}
}
var set=
	{
		'cusId':$("#cusId").val(),
		'accId':$("#accId").val(),
		'status':$("#status").val()
	}
$("#statusButton").click(function()
		{
	     $.ajax
	     (
	      {
	    	  url:"CustomerStatus?status1=account",
	          type:"POST",
	          data:set,
	          success:function(data)
	          {
	        	  alert("Sucess");
	        	  $("#result").text(data).css(
	        			  {
	        				  'font-weight' : 'bold',
		 			   		  'color' : 'green'}
	        			  );
	          }
	      }		 
	     )
		});
/* $(document).ready(function()
		{
			$('search_select_box select').selectpicker();
		}); */
</script>
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script> -->
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"></script> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script> -->
</body>
</html>