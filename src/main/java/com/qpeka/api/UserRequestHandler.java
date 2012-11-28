package com.qpeka.api;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpMessage;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;
import org.json.JSONObject;

import com.qpeka.db.book.store.AuthorHandler;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.Book;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.User;
import com.qpeka.managers.BookContentManager;
import com.qpeka.managers.UserManager;

public class UserRequestHandler extends SimpleChannelUpstreamHandler {

	private static final Logger logger = Logger.getLogger(UserRequestHandler.class.getName());

	private final AtomicLong transferredBytes = new AtomicLong();

	public long getTransferredBytes() {
		return transferredBytes.get();
	}

	 @Override
     public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
   	  
   	  Map<String, String> requestParams = new HashMap<String, String>();
   	  
   	  HttpRequest request =  (HttpRequest) e.getMessage();
   	  
   	  System.out.println(request.getContent().toString(Charset.defaultCharset()));
   	  /*
   	  QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.getUri());
   	  Map<String, List<String>> params = queryStringDecoder.getParameters();
   	  if (!params.isEmpty()) {
   		  for (Entry<String, List<String>> p: params.entrySet()) {
   			  String key = p.getKey();
   			  requestParams.put(key, p.getValue().get(0));
   		  }
   	  }*/
   	  try
   	  {
	   	  JSONObject requestJson = new JSONObject(request.getContent().toString("UTF-8"));
	   	  
	   	  logger.log(Level.INFO, "[UserRequestHandler-"+Thread.currentThread().getId()+"] Handling request with params " + requestParams.toString());
	   	  
	   	  invokeApi(requestJson , e);
   	  }
   	  catch (Exception ex) {
		// TODO: handle exception
   		logger.log(Level.INFO, "[UserRequestHandler-"+Thread.currentThread().getId()+"] Exception  while handling request " + ex.getMessage());
	   	  
	}
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				e.getCause());
		e.getChannel().close();
	}
	
	private void invokeApi(JSONObject requestJson, MessageEvent e) 
	{
		
		JSONObject response = new JSONObject();
		String res = "";
		
		try
		{
			if(!requestJson.has("method"))
			{
				writeResponse(e, "{ error : Request should contain method parameter }");
				return;
			}
			
			
			String method = (String)requestJson.get("method");
				
			if(method.equals(APIConstants.REGISTERUSER))
			{
				if(!requestJson.has("userinfo"))
				{
					writeResponse(e, "{ error : Request should contain userinfo parameter }");
					return;
				}
				JSONObject userInfo = requestJson.getJSONObject("userinfo");
				
				int age = new Date().getYear() - new Date(userInfo.getLong(APIConstants.DOB)).getYear();
				
				String id = UserManager.getInstance().addUser(userInfo.getString(APIConstants.FIRSTNAME), userInfo.getString(APIConstants.MIDDLENAME), userInfo.getString(APIConstants.LASTNAME),
						userInfo.getString(APIConstants.GENDER), userInfo.getString(APIConstants.EMAIL), userInfo.getJSONObject(APIConstants.ADDRESS).getString(APIConstants.CITY), userInfo.getJSONObject(APIConstants.ADDRESS).getString(APIConstants.STATE),
						userInfo.getJSONObject(APIConstants.ADDRESS).getString(APIConstants.ADDRESSLINE1), userInfo.getJSONObject(APIConstants.ADDRESS).getString(APIConstants.ADDRESSLINE2), userInfo.getJSONObject(APIConstants.ADDRESS).getString(APIConstants.ADDRESSLINE3), userInfo.getJSONObject(APIConstants.ADDRESS).getString(APIConstants.PINCODE), 
						userInfo.getString(APIConstants.USERTYPE), userInfo.getString(APIConstants.PREFERENCES).split(","), age, new Date(userInfo.getLong(APIConstants.DOB)), userInfo.getString(APIConstants.NATIONALITY), userInfo.getString(APIConstants.PHONE));
				
				response.put("id", id);
				response.put("status", "SUCCESS");
				writeResponse(e,response.toString());
			}
			else if(method.equals(APIConstants.GETBOOKGIVENID))
			{
				if(!requestJson.has("bookid"))
				{
					writeResponse(e, "{ error : Request should contain bookid parameter }");
					return;
				}
					
				Book b = BookContentManager.getInstance().getBookDetails((String)requestJson.get("bookid"));
				
				res =  b.toDBObject(false).toString();
				response.put("result", res);
				response.put("status", "SUCCESS");
				writeResponse(e,response.toString());
				
			}
			else if(method.equals(APIConstants.GETUSERBYID))
			{
				if(!requestJson.has("userid"))
				{
					writeResponse(e, "{ error : Request should contain userid parameter }");
					return;
				}
					
				String user = UserManager.getInstance().getUser((String)requestJson.get("userid"));
				
				response.put("result", user);
				response.put("status", "SUCCESS");
				writeResponse(e,response.toString());
				
			}
			else if(method.equals(APIConstants.GETBOOKSGIVENCRITERIA))
			{
				if(!requestJson.has("criteria"))
				{
					writeResponse(e, "{ error : Request should contain criteria parameter }");
					return;
				}
					
				List<Book> books = BookContentManager.getInstance().getBooksByCriteria((String)requestJson.get("criteria"));
				List<String> resp = new ArrayList<String>();
				
				for(Book b : books)
					resp.add(b.toDBObject(false).toString());
				
				res = resp.toString();
				response.put("result", res);
				response.put("status", "SUCCESS");
				
				writeResponse(e, response.toString());
				
			}
			else if(method.equals(APIConstants.GETBOOKSGIVENCATEGORY))
			{
				if(!requestJson.has("category"))
				{
					writeResponse(e, "{ error : Request should contain category parameter }");
					return;
				}
					
				List<Book> books = BookContentManager.getInstance().getBookDetailsByCategory(CATEGORY.valueOf((String)requestJson.get("category")));
				List<String> resp = new ArrayList<String>();
					
				for(Book b : books)
					resp.add(b.toDBObject(false).toString());
					
				res = resp.toString();
				response.put("result", res);
				response.put("status", "SUCCESS");
				
				writeResponse(e, response.toString());
				
			}
			else if(method.equals(APIConstants.GETBOOKSGIVENTITLE))
			{
				if(!requestJson.has("title"))
				{
					writeResponse(e, "{ error : Request should contain title parameter }");
					return;
				}
					
				List<Book> books = BookContentManager.getInstance().getBookDetailsByTitle((String)requestJson.get("title"));
				List<String> resp = new ArrayList<String>();
					
				for(Book b : books)
					resp.add(b.toDBObject(false).toString());
					
				res = resp.toString();
				response.put("result", res);
				response.put("status", "SUCCESS");
				
				writeResponse(e, response.toString());
			
			}
			else if(method.equals(APIConstants.GETBOOKSGIVENAUTHORID))
			{
				if(!requestJson.has("authorid"))
				{
					
					writeResponse(e, "{ error : Request should contain authorid parameter }");
					return;
				}
					
				List<Book> books = BookContentManager.getInstance().getBookDetailsByAuthor((String)requestJson.get("authorid"));
				List<String> resp = new ArrayList<String>();
				
				for(Book b : books)
					resp.add(b.toDBObject(false).toString());
				
				res = resp.toString();
				response.put("result", res);
				response.put("status", "SUCCESS");
				
				writeResponse(e, response.toString());
			}
			else if(method.equals(APIConstants.GETBOOKSGIVENAUTHORNAME))
			{
				if(!requestJson.has("authorname"))
				{
					
					writeResponse(e, "{ error : Request should contain authorname parameter }");
					return;
				}
					
				List<Book> books = BookContentManager.getInstance().getBookDetailsByAuthorName((String)requestJson.get("authorname"));
				List<String> resp = new ArrayList<String>();
				
				for(Book b : books)
					resp.add(b.toDBObject(false).toString());
				
				res = resp.toString();
				response.put("result", res);
				response.put("status", "SUCCESS");
				
				writeResponse(e, response.toString());
			}
			else if(method.equals(APIConstants.GETAUTHORGIVENID))
			{
				if(!requestJson.has("authorid"))
				{
					writeResponse(e, "{ error : Request should contain authorId parameter }");
					return;
				}
					
				Author author = AuthorHandler.getInstance().getAuthor((String)requestJson.get("authorId"));
				
				res = author.toDBObject(false).toString();
				
				response.put("result", res);
				response.put("status", "SUCCESS");
				
				writeResponse(e, response.toString());
				
			}
			else if(method.equals(APIConstants.GETAUTHORSGIVENCATEGORY))
			{
				
			}
			else if(method.equals(APIConstants.GETAUTHORSGIVENNAME))
			{
				if(!requestJson.has("authorName"))
				{
					writeResponse(e, "{ error : Request should contain authorName parameter }");
					return;
				}
				
				List<Author> authors = AuthorHandler.getInstance().getAuthorsByLikelyName((String)requestJson.get("authorName"));
				List<String> resp = new ArrayList<String>();
				
				for(Author a : authors)
					resp.add(a.toString());
				
				res = resp.toString();
				response.put("result", res);
				response.put("status", "SUCCESS");
				
				writeResponse(e, response.toString());
			}
			else if(method.equals(APIConstants.GETBOOKPAGECONTENT))
			{
				if(!requestJson.has("bookId") || !requestJson.has("pageId"))
				{
					writeResponse(e, "{ error : invalid request }");
					return;
				}
				
				JSONObject jObj = BookContentManager.getInstance().getContentGivenBookPageId((String)requestJson.get("bookId"), Integer.parseInt((String)requestJson.get("pageId")));
				writeResponse(e, jObj.getString("content"));
				
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			writeResponse(e, "{ error : "+ex.getMessage()+" }");
		}
	}

	private void writeResponse(MessageEvent e, String resp) {
        
		logger.log(Level.INFO, "[UserRequestHandler-"+Thread.currentThread().getId()+"] Writing Response:  " + resp);
	   	  
        // Build the response object.
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
        response.setContent(ChannelBuffers.copiedBuffer(resp, CharsetUtil.UTF_8));
        response.setHeader(CONTENT_TYPE, "text/html; charset=UTF-8");

        // Write the response.
        ChannelFuture future = e.getChannel().write(response);

        // Close the non-keep-alive connection after the write operation is done.
        future.addListener(ChannelFutureListener.CLOSE);
        
    }

}
