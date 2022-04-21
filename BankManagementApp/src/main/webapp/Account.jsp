<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AccountOptions</title>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css"> -->
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"> -->
<link href="commonstyle.css" type="text/css" rel="stylesheet">
<link href="label.css" type="text/css" rel="stylesheet">
<link href="button.css" type="text/css" rel="stylesheet">
<style>
div.addAccount
{
float:right;
}
</style>
</head>
<body>
<jsp:include page="Header.jsp" />
<h1><b>ACCOUNT</b></h1>
<h4><b><%if(request.getAttribute("text")!=null)
	{
	     out.println("**"+(String) request.getAttribute("text"));
	}
%></b></h4>
<jsp:include page="sidebar.jsp" />
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");%>
<div>
<form class="new" method="post" id="myform">
<div class="addAccount">
<button name="moneyexchange" value="addAccount" type="submit" formaction="CustomerCount?moneyexchange=addAccount">Add Account</button>
</div>
<br>
<br>
<h3>Account details</h3>
<table class="details">
  <tr>
    <th>Account Id</th>
    <th>Customer Id</th>
    <th>Branch</th>
    <th>Balance</th>
    <th>Status</th>
    <th>Edit</th>
  </tr>
  <c:forEach items="${AccountMap}" var="map1">
 <c:forEach items="${map1.value}" var="map2">
  <tr>
  <td><c:out value="${map2.key}" /></td>
  <td><c:out value="${map2.value.getCustomerId()}" /></td>
  <td><c:out value="${map2.value.getBranch()}" /></td>
  <td><c:out value="${map2.value.getBalance()}" /></td>
  <td><c:out value="${map2.value.isStatus()}" /></td>
  <td><button class="button" type="submit" formaction="Profile?accNo=<c:out value="${map2.key}" />&acc=<c:out value="${map2.value.getCustomerId()}" />&moneyexchange=update" formmethod="post">Edit</button> <button type="submit" style="width:150px" formaction="AddAccount.jsp?accId=<c:out value="${map2.key}" />&cusId=<c:out value="${map2.value.getCustomerId()}" />&moneyexchange=status">Activate/Deactive</button></td>
<!--   <td><input type="button" value="Status" id=status /></td> -->
  <input type="hidden" name="update" value="account">
  </tr>
  </c:forEach>
 </c:forEach>
 
</table>
<br>
</form>
</div>
</body>
</html>