<!doctype html>
<%@page import="com.qpeka.managers.BookContentManager"%>
<%@page import="com.qpeka.db.book.store.tuples.Book"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.FileReader"%>
<html>
<head>
<meta charset="utf-8">
<title>Qpeka</title>
<link href="css/STYLE2.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/gallerystyle.css" />
      
<!-- Do not edit IE conditional style below -->
<!--[if gte IE 5.5]>
<style type="text/css">
#motioncontainer {
width:expression(Math.min(this.offsetWidth, maxwidth)+'px');
}
</style>
<![endif]-->
<!-- End Conditional Style -->

<script type="text/javascript"  src="js/motiongallery.js">
/***********************************************
* CMotion Image Gallery- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* Visit http://www.dynamicDrive.com for hundreds of DHTML scripts
* This notice must stay intact for legal use
* Modified by Jscheuer1 for autowidth and optional starting positions
***********************************************/
</script>
<%!
String dir = "";
String filePrefix = "";
int pageNo = 0;
int numPagesPerFile = 100;
int totalPages = 0;
%>
<%
String bookId = request.getParameter("book");
Book bk = BookContentManager.getInstance().getBookDetails(bookId);
pageNo = Integer.parseInt(request.getParameter("pageNo"));
totalPages = bk.getNumPages();
//dir = request.getParameter("fileDir");
dir = "/books/content/";
//filePrefix = request.getParameter("filePrefix");
filePrefix = bk.getTitle();

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
<script type="text/javascript">
var currentPage = <%=Integer.parseInt(request.getParameter("pageNo"))%>;
function insertContent(pageNum,base,flag){
	
	if(flag == 'n')
	{
		pageNum = currentPage +1;
	}
	else if(flag == 'p')
		pageNum = currentPage -1;
	else if(flag == 'f')
		pageNum = 1;
	else if(flag == 'l')
		pageNum = <%=totalPages%>;
	
	var num = pageNum;
	
	if(num == 0)
		document.getElementById('pageContent').innerHTML = '<img alt="" class="coverpage" src="http://localhost:8080/ImageServer/image?book="<%=bk.get_id()%>>';
	
	if(num >= base + 10)
	{
		window.location = '<%=request.getContextPath()%>/bookViewer.jsp?book=<%=bookId%>&pageNo='+num;
	}
	else if(num < base - 1)
	{
		if(num > 1)
			window.location = '<%=request.getContextPath()%>/bookViewer.jsp?book=<%=bookId%>&pageNo='+(base-1);
		else
			window.location = '<%=request.getContextPath()%>/bookViewer.jsp?book=<%=bookId%>&pageNo='+1;
	}
	else
	{
		currentPage = pageNum;
		
		var text = document.getElementById('content-'+pageNum).innerHTML;
		document.getElementById('pageContent').innerHTML = text;
	}
}
</script>
</head>

<body >
	<div class="pagewrap" >
    	<div class="maincont">
        
        	<div class="lft-cont">
        		<div class="ad-top"> 
            		<img src="images/dynamicbook1.jpg" border=1>
            	</div><!--ad-top-->
           		<div class="ad-bottom"> 
            		<img src="images/dynamicbook1.jpg" border=1>
            	</div><!--ad-top-->
            </div><!--lft-cont-->
            <div class="middl-cont">
            	<div id="disply">
            		<div id="inner-disply" bgcolor="#FFFFFF">
            		<!--  <p id="pageNum" style="margin-left: 10px;margin-top: 10px;margin-right: 10px;margin-bottom: 10px;">
            			Page 1/213
            		</p> -->
            		<p id="pageContent" style="margin-left: 10px;margin-top: 10px;margin-right: 10px;margin-bottom: 10px;font-size:18px;">
            		<%if(pageNo == 0) {%>
            			
            			<img alt="" align="middle" src="http://localhost:8080/ImageServer/image?book="<%=bk.get_id()%>>
            		
            		<%} else {%>
            		
            		<%=j.get(pageNo+"")%>	
            		
            		<%} %>
            		</p>
            		</div>
                </div><!--disply-->
                <%for(int i = pageNo ; i < pageNo+10 ; i++ )
                {
                	if(j.has(i+"")){%>
                
                	<div id="content-<%=i%>" style="display: none;">
                		<%=j.get(i+"")%>
                	</div>
               		<%}
                } %>
               <a href="javascript:insertContent(0,<%=pageNo%>,'f')">First</a>|
               <a href="javascript:insertContent(0,<%=pageNo%>,'p')">Prev</a> |
               <a href="javascript:insertContent(0,<%=pageNo%>,'n')">Next</a> |
               <a href="javascript:insertContent(0,<%=pageNo%>,'l')">Last</a> 
                <div id="thumb">
               	  <div id="motioncontainer" style="position:relative; overflow:hidden;">
                    <div id="motiongallery" style="position:absolute;left:0;top:0;white-space: nowrap;">
                    <nobr id="trueContainer">
                    <%for(int i = pageNo ; i < pageNo+10 ; i++ )
                	{%>
                    <a href="javascript:insertContent(<%=i%>,<%=pageNo%>)"><img src="images/dynamicbook1.jpg" border=1></a> 
                    <%} %>
                    </nobr>
                    
                    </div>
                    </div>
                </div>
            	
            </div>
            <div class="rht-cont">
            	<div class="ad-top"> 
            		<img src="images/dynamicbook1.jpg" border=1>
            	</div><!--ad-top-->
           		<div class="ad-bottom"> 
            		<img src="images/dynamicbook1.jpg" border=1>
            	</div><!--ad-top-->
            </div><!--rht-cont-->
        
        </div><!--maincont-->
        
    </div><!--pagewrap-->
</body>
</html>
