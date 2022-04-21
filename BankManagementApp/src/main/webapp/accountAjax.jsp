<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<table id="account" class="details">
  <tr>
    <th>Account Id</th>
    <th>Customer Id</th>
    <th>Branch</th>
    <th>Balance</th>
    <th>Status</th>
    <th>Edit</th>
  </tr>
  </table>
  <h1 id="checkHeader"></h1>
</body>
<script>
$(document).ready(function()
{
      $.get
      ("AccountServlet",function(data)
    	  {
    	      console.log(data);
	    	  $("#checkHeader").text();
      	  }  
      );
}
);
</script>
</html>