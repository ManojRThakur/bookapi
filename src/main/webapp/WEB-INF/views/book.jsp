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
</head>
<body>
<div class="super-container">
  <div class="fheader">
	  <jsp:include page="common/header.jsp" />
	  <jsp:include page="common/navigation.jsp" />
  </div>
  <div class="container">
    <div id="main" role="main">
      <div class="section">
        <div class="book-col clearfix">
          <div class="image-col">
            <div class=""><img src="images/b2.jpg" /></div>
            <div class="buy">Buy</div>
            <div class="read">Read</div>
          </div>
          <div class="meta-col">
            <div class="book-title"><%=bk.getTitle() %></div>
            <div class="author"><%= ath.getName().getFirstName()%> <%= ath.getName().getMiddleName()%> <%= ath.getName().getLastName()%></div>
            <div class="description"><%=bk.getDescription() %></div>
            <div class="book-details">
              <table width="100%">
                <tr>
                  <td width="30%"><div class="strong">ISBN</div></td>
                  <td width="70%">0375507256 (ISBN13: 9780375507250)</td>
                </tr>
                <tr>
                  <td><div class="strong">Edition language</div></td>
                  <td>English</td>
                </tr>
                <tr>
                  <td><div class="strong">Original title</div></td>
                  <td><%=bk.getTitle() %></td>
                </tr>
                <tr>
                  <td><div class="strong">Literary awards</div></td>
                  <td>Man Booker Prize Nominee for Shortlist (2004), Nebula Award Nominee (2005)</td>
                </tr>
              </table>
            </div>
          </div>
        </div>
        <div class="rating-col clearfix">
          <div class="ir">Info and Rating</div>
          <div class="ir-content">
            <table width="100%">
              <tr>
                <td width="20%">Category:</td>
                <td width="80%"><%=bk.getCategory().toString()%></td>
              </tr>
              <tr>
                <td>Rating:</td>
                <td><%=bk.getAvgRating()%>/5</td><!-- <img src="images/rating1.png"> -->
              </tr>
              <tr>
                <td>Upload Date:</td>
                <td><%=new Date().toLocaleString() %></td>
              </tr>
              <tr>
                <td>Copyright:</td>
                <td>Traditional Copyright: All rights reserved</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
      <div class="aside">
        <jsp:include page="module/featuredBooks.jsp" flush="true"/>
        <jsp:include page="module/recommendedBooks.jsp" flush="true"/>
      </div>
    </div>
     <jsp:include page="common/footer.jsp" flush="true"/>
  </div>
</div>
</body>
</html>