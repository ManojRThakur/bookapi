<!doctype html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Qpeka | Books</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<link href="css/login-box.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="super-container">
  <div class="fheader">
	  <jsp:include page="common/header.jsp" />
  </div>
  <div class="container">
  <form class="tablemlogin" action="/QPEKA/AuthServlet" method="post">
  				<input  hidden="true" name="method" size=15 type="text" value="login"/>
                <table>
                    <tr>
                        <td> Username  : </td><td> <input name="username" size=15 type="text" /> </td> 
                    </tr>
                    <tr>
                        <td> Password  : </td><td> <input name="password" size=15 type="text" /> </td> 
                    </tr>
                    <tr>
                        <td> UserType  : </td>
                        <td> 
                        	<select name="type">
							  <option value="user">User</option>
							  <option value="writer">Writer</option>
							  <option value="admin">Admin</option>
							</select> 
                        </td> 
                    </tr>
                </table>
                <input type="submit" value="login" />
  </form>
  </div>
    
  <jsp:include page="common/footer.jsp" flush="true"/>
</div>
</body>
</html>
