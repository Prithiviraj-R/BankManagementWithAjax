<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SideBar</title>
<link href="sidebar.css" type="text/css" rel="stylesheet">
<link href="label.css" type="text/css" rel="stylesheet">
<link href="button.css" type="text/css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div>
<form class="sidebar">
<br>
<input type="hidden" name="update" value="profile">
<button type="submit" id="profileButton">My Profile</button>
<br><br>
<button type="submit" formaction="LogOutServlet" name="moneyexchange" value="logout" formmethod="post">LogOut</button>
<p id="answer"></p>
<br>
</form>
</div>
</body>
<script>
$("#profileButton").click(function()
		{
	     $.ajax(
	    		 {
	    			 url:"Profile?update=profile",
		    		 type:'GET',
		    		 success:function(result)
		    		      {		 
			    			  $("#answer").html("Name:"+result.customerName+"<br> DOB:"+result.DOB+"<br> Address:"+result.address+"<br> PhoneNumber:"+result.phoneNumber);
		    		      },
		    		 error:function(data)
		    			  {
		    			     alert(data.responseText);
		    			  }
	    		 });
// 	     event.preventDefault();
	     return;
		}
		);
</script>
</html>