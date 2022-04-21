<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Map" %>
    <%@page import="Details.AccountDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction</title>
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
  height:20px;
  margin-left:50px; 
 }

</style>
</head>
<body>
<%if(session.getAttribute("lastId")==null)
{
	RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
    rd.forward(request, response);
} %>
<jsp:include page="Header.jsp" />
<h1><b>TRANSFER</b></h1>
<jsp:include page="sidebar.jsp" />
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");%>
<div>
<form class="operation" onsubmit="return validAccNo();" id="myForm" name="myForm">
<label for="AccNum"><b>From:</b></label>
<br>
<br>
<div class="search_select_box">
<select name="AccNum" id="AccNum" required data-live-search="true">
<option value="0">select</option>
<%
if(request.getAttribute("AccountMap")==null)
{
	
}
else
{
	Map<Integer,Map<Long,AccountDetails>> obj1=(Map<Integer,Map<Long,AccountDetails>>) request.getAttribute("AccountMap"); 
  for(Integer i:obj1.keySet())
  {
	  for(Long k:obj1.get(i).keySet())
	  {
	     if(obj1.get(i).get(k).isStatus()==true)
	     {
%>
<option value="<%=k%>"><%=k%></option>
<%
}
	  }
  }
}
%>
</select>
</div>
<br><br><br>
<label for="ToAccNum"><b>to:</b></label><br><br>
<div class="search_select_box">
<select name="AccNo" id="AccNo">
<option value="0">select</option>
<%
if(request.getAttribute("AccountMap")==null){}
else{
Map<Integer,Map<Long,AccountDetails>> obj2=(Map<Integer,Map<Long,AccountDetails>>) request.getAttribute("AccountMap"); 
  for(Integer i:obj2.keySet())
  {
	  for(Long k:obj2.get(i).keySet())
	  {
	     if(obj2.get(i).get(k).isStatus()==true)
	     {
%>
<option value="<%=k%>"><%=k%></option>
<%
}
	  }
  }
}
%>
</select>
</div>
<br><br><br>
<input type="hidden" id="Customer" name="Customer" value="admin">
<label for="Amount"><b>Amount:</b></label><br><br>
<input type="number" placeholder="Amount" name="Amount" id="Amount" max="50000" required>
<br>
<br>
<button type="submit" name="Customer" value="admin" formaction="TransactionServlet" formmethod="post">Send</button>
<h4 class=warning><%
if(request.getAttribute("text")!=null)
{
out.print("**"+request.getAttribute("text"));
}
%></h4>
</form>
</div>
<script>
function validAccNo()
{
	     var x = document.myForm.Amount;
	     if(Math.sign(x.value)==(-1))
	     {
		  alert("You are tried to enter the negative value");
		  return false;
	     }
		var x=document.myForm.AccNum.value;
	    var y=document.myForm.AccNo.value;
		if(x==0 || y==0)
			{
			   alert("Enter valid Account Number");
			   return false;
			}
		else
			{
			return true;
			}
}
$(document).ready(function()
		{
			$('search_select_box select').selectpicker();
		});
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>