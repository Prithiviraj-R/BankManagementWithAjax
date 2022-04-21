<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="Details.AccountDetails" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="commonstyle.css" type="text/css" rel="stylesheet">
<link href="label.css" type="text/css" rel="stylesheet">
<link href="button.css" type="text/css" rel="stylesheet">
<title>Transaction</title>
<style>
div
{
float:right;
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
<h1>Transaction</h1>
<jsp:include page="usersidebar.jsp" />
<div>
<form class="operation" onsubmit="return validAccNo()" name="myForm" id="myForm">
<h3>Money Transfer</h3>
<label for="AccNum">From:</label><br><br>
<input type="number" name="AccNum" id="AccNum" value=<%=request.getParameter("fromAccNum")%> readonly="readonly">
<br><br>
<input type="hidden" id="Customer" name="Customer" value="Customer">
<label for="ToAccNum">to:</label><br><br>
<select name="AccNo" id="cars" required>
<option value="0">select</option>
<%Map<Integer,Map<Long,AccountDetails>> obj2=(Map<Integer,Map<Long,AccountDetails>>) request.getAttribute("AccountMap"); 
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
%>
</select>
<br><br>
<label for="Amount">Amount:</label><br><br>
<input type="text" placeholder="Amount" name="Amount" id="Amount">
<br>
<br>
<button type="submit" formaction="TransactionServlet" formmethod="post">Send</button>
<h4 class=warning><b><%if(request.getAttribute("text")!=null)
	{
	     out.println("**"+(String) request.getAttribute("text"));
	}
%></b></h4>
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