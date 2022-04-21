<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Options</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css"> -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"> -->
<link href="commonstyle.css" type="text/css" rel="stylesheet">
<link href="label.css" type="text/css" rel="stylesheet">
<link href="button.css" type="text/css" rel="stylesheet">
<style>
form.inactive
{
   margin-left:30px;
    width:1020px;
    box-shadow:0 8px 32px 0 rgba(31,38,135,.37);
	border-radius:30px;
	background:rgb(246, 240, 220);
	border-left:1px solid rgba(255,255,255,.4);
	border-right:1px solid rgba(255,255,255,.4);
	border-top:1px solid rgba(255,255,255,.4);
	border-bottom:1px solid rgba(255,255,255,.4);
	text-align:center;
}
/* label.label */
/* { */
/* 	font-size:20px; */
/* 	color:black; */
/* 	opacity:.8; */
/* 	margin-top:50px; */
/* 	font-weight:bold; */
/* } */
.buttonDiv
{
	float:left;
}
.buttonDiv2
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
<h1><b>CUSTOMER</b></h1>
<div id="tempCheck"></div>
<h4><b><%if(request.getAttribute("text")!=null)
	{
	     out.println("**"+(String) request.getAttribute("text"));
	}
%></b></h4>
<jsp:include page="sidebar.jsp" />
	<div>
		<form class="new" method="post" id="Customer">
		<div class="buttonDiv2">
	    <button name="class" value="add" type="submit" formaction="AddCustomer.jsp?class=add">Add Customer</button>
		</div>
		<div class=buttonDiv>
		<button name="class" value="inactive" type="submit" formaction="CustomerServlet">Inactive Customer</button>
		</div>
		<input type="hidden" name="class" value="active">
		<%if((String) request.getAttribute("class")==null) 
		{
		%>
		<br><br>
		<h3>Active Customer</h3>
			<table class="customer">
				<tr class="customer">
					<th>Customer Id</th>
					<th>Customer Name</th>
					<th>Date of Birth</th>
					<th>Address</th>
					<th>Phone Number</th>
					<th>Status</th>
					<th>Update</th>
				</tr>
				<c:forEach items="${temp}" var="map1">
					<tr>
						<td>
								<c:out value="${map1.key}" />
							</td>
						<td><c:out value="${map1.value.getName()}" /></td>
						<td><c:out value="${map1.value.getDob()}" /></td>
						<td><c:out value="${map1.value.getAddress()}" /></td>
						<td><c:out value="${map1.value.getPhoneNumber()}" /></td>
						<td><c:out value="${map1.value.isStatus()}" /></td>
						<td><button class="button" type="submit"
								formaction="Profile?customerId=<c:out value="${map1.key}"/>&class=update" formmethod="post">Edit</button> <button class="button" type="submit" name="status1" value="customer"
								formaction="CustomerStatus?customerId=<c:out value="${map1.key}" />"
								onclick="return confirmAction()">
								Deactivate
							</button></td>
					    <input type="hidden" name="update" value="active">
					</tr>
				</c:forEach>
			</table>
			<br><br>
			<% 
			}
			else
			{
			%>
<!-- 			</form> -->
<!-- 		<form class="inactive" method="post"> -->
		<br>
		<br>
		<h3>InActive Customer</h3>
		<table>
				<tr>
					<th>Customer Id</th>
					<th>Customer Name</th>
					<th>Date of birth</th>
					<th>Address</th>
					<th>Phone Number</th>
					<th>status</th>
					<th>Activate</th>
					
				</tr>
				<c:forEach items="${inactive}" var="map1">
					<tr>
						<td>
								<c:out value="${map1.key}" />
					   </td>
						<td><c:out value="${map1.value.getName()}" /></td>
						<td><c:out value="${map1.value.getDob()}" /></td>
						<td><c:out value="${map1.value.getAddress()}" /></td>
						<td><c:out value="${map1.value.getPhoneNumber()}" /></td>
						<td><c:out value="${map1.value.isStatus()}" /></td>
						<td><button class="button" type="submit" name="status1" value="customer"
								formaction="CustomerStatus?customerId=<c:out value="${map1.key}" />"
								onclick="return confirmAction()">
								Activate
							</button></td>
					    <input type="hidden" name="update" value="inactive">
					</tr>
				</c:forEach>
			</table>
			<br>
		</form>
		<%
		}
		%>
	</div>
	<script>
	function confirmAction() {
		let confirmAction = confirm("do you want to do this action?");

		return confirmAction;
	}
</script>
</body>
</html>