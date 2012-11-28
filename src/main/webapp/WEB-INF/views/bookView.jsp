<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!
String dir = "";
String filePrefix = "";
int pageNo = 0;
int numPagesPerFile = 50;
%>
<%
pageNo = Integer.parseInt(request.getParameter("pageNo"));

dir = request.getParameter("fileDir");
filePrefix = request.getParameter("filePrefix");


int fileIndex = 0;
if(pageNo/numPagesPerFile < 1)
	fileIndex = 1 ;
else if(pageNo%numPagesPerFile == 0)
	fileIndex = pageNo/numPagesPerFile;
else
	fileIndex = ((int)pageNo/numPagesPerFile) + 1;

String fileName = dir + filePrefix + "-" + fileIndex;
FileReader fr = null;
JSONObject returnObj = new JSONObject();
JSONObject j = null;
try
{
	fr = new FileReader(new File(fileName));
	
	char[] cbuf = new char[1000000];
	fr.read(cbuf);
	String s = new String(cbuf);
	s = s.trim();
	
	j = new JSONObject(s);
	
}
catch (Exception e) {
	e.printStackTrace();
}
finally{
	try {
		fr.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<td>
<%if(pageNo > 1){%>
<a href="<%=request.getContextPath()%>/bookView.jsp?fileDir=<%=dir%>&filePrefix=<%=filePrefix%>&pageNo=<%=pageNo-1%>">Prev &nbsp;&nbsp;</a>
<%}%>
</td>
<td style="background-color:#EEEEEE;height:400px;width:800px;text-align:top;">
<%=j.get(pageNo+"")%>
</td>
<td>
<a href="<%=request.getContextPath()%>/bookView.jsp?fileDir=<%=dir%>&filePrefix=<%=filePrefix%>&pageNo=<%=pageNo+1%>"> &nbsp; &nbsp;Next</a>
</td>
</tr>
</table>
</body>
</html>