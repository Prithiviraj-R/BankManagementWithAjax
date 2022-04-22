<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> -->
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
	<link href="commonstyle.css" type="text/css" rel="stylesheet">
<link href="label.css" type="text/css" rel="stylesheet">
<link href="button.css" type="text/css" rel="stylesheet">
<style>
div.addAccount
{
	margin-left:550px;
}
form.new
{
	margin-left:600px;
}
#account
{
	margin-left:auto;
	margin-right:auto;
}
</style>
</head>
<body>
<jsp:include page="Header.jsp" />
<h1><b>ACCOUNT</b></h1>
<jsp:include page="sidebar.jsp" />
<form class="new" method="post" id="myform">
<div class="addAccount">
<button name="moneyexchange" value="addAccount" type="submit" formaction="CustomerCount?moneyexchange=addAccount" id="add">Add Account</button>
</div>
<br>
<br>
<h3>Account details</h3>
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
  </form>
</body>
<script>
$(document).ready(function()
{
      $.get
      ("AccountServlet",function(data)
    	  {
    	      var parsedData=$.parseJSON(data);
    	      console.log(parsedData);
	    	  $("#checkHeader").html(data.value);
	    	  $.each(parsedData,function (key, val) {
	    		        var custId=key;
	    		        $.each(val,function(key,val)
	    		        		{
// 	    		        	       console.log("Second Loops"+key);
// 	    		        	       console.log(val);
// 	    		        	       console.log(val.balance);
// 	    		        	       console.log(val.branch);
								   $("#account").append("<tr><td>"+val.accountNumber+"</td><td>"+custId+"</td><td>"+val.branch+"</td><td>"+val.balance+"</td><td>"+val.status+"</td><td><button type=submit>Activate/Deactive</button></td></tr>");
	    		        		});
	    		    });
      	  }  
      );
}
);
</script>
</html>