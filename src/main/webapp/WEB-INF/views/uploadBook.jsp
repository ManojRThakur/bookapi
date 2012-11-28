<%@page import="com.qpeka.db.book.store.tuples.Constants.CATEGORY"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Upload</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/UploadHandlerServlet" method="POST"
		enctype="multipart/form-data">
		<table class="sortable">
			<tbody>
				<tr>
					<td><b>Book Details</b></td>
					<td></td>
				</tr>
				<tr>
					<td>Book title:</td>
					<td><input type="text" name="title" value=""></input></td>
				</tr>
				<tr>
					<td>Category:</td>
					<td>
					<select name="category">
					 <%for(CATEGORY c : CATEGORY.values()){ %>
					 
					  <option value="<%=c.name() %>"><%=c.name() %></option>
					 
					 <%} %>
					</select> 
					</td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><textarea type="text" rows="10" cols="50" name="description" value=""></textarea></td>
				</tr>
				<tr>
					<td>Edition:</td>
					<td><input type="text" name="edition" value=""></input></td>
				</tr>
				<tr>
					<td><b>Author Details</b></td>
					<td></td>
				</tr>
				<tr>
					<td>Author FirstName </td>
					<td><input type="text" name="aFName"></input>
					</td>
				</tr>
				<tr>
					<td>Author Middle Name </td>
					<td><input type="text" name="aMName"></input>
					</td>
				</tr>
				<tr>
					<td>Author Last Name </td>
					<td><input type="text" name="aLName"></input>
					</td>
				</tr>
				<tr>
					<td>Date of Birth:</td>
					<td>Day : <input type="text" name="day"></input> &nbsp;&nbsp; Month : <input type="text" name="month"></input> Year : <input type="text" name="year"></input></td>
				</tr>
				<tr>
					<td>Author Nationality </td>
					<td><input type="text" name="nationality"></input>
					</td>
				</tr>
				<tr>
					<td>Gender </td>
					<td>
					<select name="gender">
					  <option value="MALE">MALE</option>
					  <option value="FEMALE">FEMALE</option>
					</select> 
					</td>
				</tr>
				<tr>
					<td>About Author:</td>
					<td><textarea type="text" rows="10" cols="50" name="about" value=""></textarea></td>
				</tr>
				<tr>
					<td>Author genre:</td>
					<td>
					<select name="genre">
					 <%for(CATEGORY c : CATEGORY.values()){ %>
					 
					  <option value="<%=c.name() %>"><%=c.name() %></option>
					 
					 <%} %>
					</select> 
					</td>
				</tr>
				<tr>
					<td><b>Publisher Details</b></td>
					<td></td>
				</tr>
				<tr>
					<td>Publisher name:</td>
					<td><input type="text" name="pubName" value=""></input></td>
				</tr>
				<tr>
					<td>Doc/Docx File:</td>
					<td><input type="file" name="file" value=""></input></td>
				</tr>
				<tr>
					<td><input type="submit" name="action" value="Upload">
					</td>
				</tr>

			</tbody>
		</table>
	</form>
</body>
</html>