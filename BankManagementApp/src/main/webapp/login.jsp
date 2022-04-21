<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Login</title>
<link href="login.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="left">
 <form class="left">
 <input type="image" id="image" alt="login.jsp" style="height:150px;width:150px;"
       src="https://sp-ao.shortpixel.ai/client/to_webp,q_lossy,ret_img/https://elasq.com/wp-content/uploads/elementor/thumbs/bank-icon-vector6-p7gccijtbllua29j34qi6n6f2mjma51nph4uf3z4h4.png">
 <Header>
 <h1>Ashoka Bank</h1>
 <center><h2>(Under Indian Bank)</h2></center>
 <br>
 <center><h2 style="color:White">Your Perfect Banking Partner</h2></center>
 </Header>
 </form>
 </div>
<div class="right" style="margin-left:34%;margin-top:5%;">
<form class="right" action="LoginServlet" method="post" id="myForm">
    <h1>Login</h1><br>
    <label for="uname"><b>Username:<br><br></b></label>
    <input type="text" placeholder="Enter Username" id="uname" name="uname" required><br><br>
    <label for="psw"><b>Password:<br><br></b></label>
    <input type="password" placeholder="Enter Password" id="psw" name="psw" required><br><br>
    <button type="submit">SignIn</button><br><br>
    <button type="reset">Reset</button><br><br>
         <h3><b><%
if(request.getAttribute("text")==null)
{}
else
{
out.println("*"+(String)request.getAttribute("text"));
}
%></b></h3>
 </form>
  </div>
</body>
</html>