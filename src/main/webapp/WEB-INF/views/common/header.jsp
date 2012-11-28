<div class="header clearfix">
  <div class="logo-search clearfix prelative">
    <div class="logo Fleft">Qpeka</div>
    <div class="global-search Fright">
      <div class="quick-links">
        <ul>
          <li><a href="">Register</a></li>
          <li> | </li>
          <%if(request.getParameter("uName") != null && ((String)request.getParameter("uName")).trim().length() > 0)
        	{%>
       		<li>Logged in as <%=request.getParameter("uName") %></li>
       		<li> | </li>
       		<li> <a href="/QPEKA/AuthServlet?method=logout">Logout</a> </li>
       		<%}
             else
             {%>
             <li><a href="/QPEKA/login.jsp">Login</a></li>
             <%} %>
          <li> | </li>
          <li><a href="">Help</a></li>
        </ul>
      </div>
      <form method="GET" action="">
        <input id="suggest-keyword" class="search-top Fleft" type="text" placeholder="Search">
        <button id="header-search-submit" class="" onClick="checkHeaderSearch();" name="submit">Search</button>
      </form>
    </div>
  </div>
</div>
