<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="Details.Customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer details</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css"> -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"> -->
<link href="commonstyle.css" type="text/css" rel="stylesheet">
<link href="label.css" type="text/css" rel="stylesheet">
<link href="button.css" type="text/css" rel="stylesheet">
</head>
<body>
<%-- <%if(session.getAttribute("lastId")==null)
{
	RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
    rd.forward(request, response);
} %> --%>
<jsp:include page="Header.jsp" />
<h1>Customer details</h1>
<jsp:include page="usersidebar.jsp" />
<div>
<form class="new">
<p id="answer"></p>
<%-- <%Customer obj=(Customer) session.getAttribute("Customer");%>
<br>
<label><b>Name:</b></label>
<h3><%out.println(obj.getName());%></h3>
<br>
<label><b>Date Of Birth:</b></label>
<h3><%out.println(obj.getDob());%></h3>
<br>
<label><b>Address:</b></label>
<h3><%out.println(obj.getAddress());%></h3>
<br>
<label><b>Phone:</b></label>
<h3><%out.println(obj.getPhoneNumber());%></h3>
<br>
<label><b>Status:</b></label>
<h3><%out.println(obj.isStatus());%></h3> --%>
<!-- <center><button onclick="history.back()">Go Back</button></center> -->
</form>
</div>
</body>
</html>