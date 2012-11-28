<!doctype html>
<%@page import="java.util.Date"%>
<%@page import="com.qpeka.db.book.store.AuthorHandler"%>
<%@page import="com.qpeka.db.book.store.tuples.Author"%>
<%@page import="com.qpeka.managers.BookContentManager"%>
<%@page import="com.qpeka.db.book.store.tuples.Book"%>
<html lang="en">
<head>
<%!
	Book bk = BookContentManager.getInstance().getBookDetails("50560413c4e75df35a17fc9a");
	Author ath = AuthorHandler.getInstance().getAuthor(bk.getAuthorId());
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Qpeka | Books</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/slider.css">
<link rel="stylesheet" href="css/slider.css">
</head>
<body>
<div class="super-container">
  <div class="fheader">
     <%if(!session.isNew() && session.getAttribute("uName") != null ){ %>
	  <jsp:include page="common/header.jsp"> 	
	  	<jsp:param name="uName" value='<%=session.getAttribute("uName")%>'/>
	  	<jsp:param name="type" value='<%=session.getAttribute("type")%>'/>
	  </jsp:include>
	  <%} else {%>
	  <jsp:include page="common/header.jsp"/> 	
	  <%} %>
	  <jsp:include page="common/navigation.jsp" />
  </div>
  <div class="container">
    <div id="main" role="main">
      <div class="section">
        <div class="book-col clearfix">
          <div class="image-col">
            <div class=""><img src="images/b2.jpg" /></div>
            <br>
            <div class="meta-col">
            	<div class="buy"><h3>Tools</h3></div>
            	<hr>
	            <div class="title">Edit Profile</div>
	            <div class="title">My Reads</div>
	            <div class="title">My Friends</div>
          	</div>
          </div>
          
           <div class="meta-col">
           	  <div class="book-title">Bookmarks & Reads</div>
           	  <hr><br>
              <div class="featured-book clearfix">
                  <div class="book"> <img src="images/b1.jpg" width="50" height="50" /> </div>
				  <div class="book-info">
				    <div class="title"><a href="">Fast And Furious</a></div>
				    <div class="meta">Page No. 3 , Bookmarked on 18 Aug 2012 </div>
				    <div class="author">Dr. Ajay M</div>
				  </div>
			  </div>
			  <div class="featured-book clearfix">
			      <div class="book"> <img src="images/b1.jpg" width="50" height="50" /> </div>
				  <div class="book-info">
				    <div class="title"><a href="">Harry Potter</a></div>
				    <div class="meta">Page No. 4 , Bookmarked on 20 Dec 2012 </div>
				    <div class="author">Dr. Manoj Thakur</div>
				  </div>
			  </div>
           </div>
         <div class="meta-col">
         <br><br><br>
         </div>
          <div class="meta-col">
          <div class="book-title">Friend's Updates</div>
              <hr><br>
              <div class="update-list">
				  <div class="update-info">
				    <div class="meta">April 10, 2011 &nbsp;&nbsp;&nbsp;&nbsp;	Your friend Manoj is reading Harry Potter. </div>
				    <div class="meta"><a href="">Check it out...</a></div>
				  </div>
			  </div>
			  
			
              <div class="update-list">
				  <div class="update-info">
				    <div class="meta">April 10, 2012 &nbsp;&nbsp;&nbsp;&nbsp;	Your friend Abhishek is reading 5 Point Some One </div>
				  	<div class="meta"><a href="">Check it out...</a></div>
				  </div>
			  </div>
			  
			  
		  </div>
        </div>
      </div>
      <div class="aside">
        <jsp:include page="module/featuredBooks.jsp" flush="true"/>
        <jsp:include page="module/recommendedBooks.jsp" flush="true"/>
        <jsp:include page="module/updates.jsp" flush="true"/>
      </div>
    </div>
     <jsp:include page="common/footer.jsp" flush="true"/>
  </div>
</div>
</body>
</html>