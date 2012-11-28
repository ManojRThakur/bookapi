package com.qpeka.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import com.qpeka.book.converter.BookConverterUtils;
import com.qpeka.db.book.store.tuples.Constants.AUTHORTYPE;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Constants.GENDER;
import com.qpeka.managers.BookContentManager;

/**
 * Servlet implementation class UploadHandlerServlet
 */
public class UploadHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean create = false;
		//file upload part 
	    String filePath = "";
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String title = "";
		String authorFName = "";
		String authorMName = "";
		String authorLName = "";
	    String bookDesc =  "";
	    String authorDesc = "";
	    String bookEdition = "";
	    CATEGORY bookCategory = CATEGORY.Children;
	    CATEGORY authorGenre = CATEGORY.Children;
	    String fileName = "";
	    String publisherName = "";
	    GENDER authorGender = GENDER.MALE;
	    String nationality = "";
	    
	    int day = 0;
	    int month = 0;
	    int year = 0;
	    
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
 
            try {
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    
                    if(item != null)
                    {
	                    if (!item.isFormField()) {
	                        fileName = item.getName();
	 
	                        String root = "/var/tmp";
	                        File path = new File(root + "/uploads");
	                        if (!path.exists()) {
	                            boolean status = path.mkdirs();
	                        }
	                        filePath = path + "/" + fileName;
	                        
	                        File uploadedFile = new File(path + "/" + fileName);
	                        System.out.println(uploadedFile.getAbsolutePath());
	                        item.write(uploadedFile);
	                    }
	                    else
	                    {
	                    	
	                    	String name = item.getFieldName().trim();
	                    	String value = item.getString().trim();
	                    	if(name.equalsIgnoreCase("title"))
	                    		title = value;
	                    	if(name.equalsIgnoreCase("category"))
	                    	try
	                    	{	
	                    		bookCategory = CATEGORY.valueOf(value);
	                    	}
	                    	catch (Exception e) {
								// TODO: handle exception
							}
	                    	if(name.equalsIgnoreCase("genre"))
	                    	try
	                    	{
	                    		authorGenre = CATEGORY.valueOf(value);
	                    	}
	                    	catch (Exception e) {
								// TODO: handle exception
							}
	                    	if(name.equalsIgnoreCase("description"))
	                    		bookDesc = value;
	                    	if(name.equalsIgnoreCase("about"))
	                    		authorDesc = value;
	                    	if(name.equalsIgnoreCase("aFName"))//
	                    		authorFName = value;
	                    	if(name.equalsIgnoreCase("aMName"))//
	                    		authorMName = value;
	                    	if(name.equalsIgnoreCase("aLName"))//
	                    		authorLName = value;
	                    	if(name.equalsIgnoreCase("edition"))//
	                    		bookEdition = value;
	                    	if(name.equalsIgnoreCase("pubName"))
	                    		publisherName = value;
	                    	if(name.equalsIgnoreCase("day"))
	                    		day = Integer.parseInt(value);
	                    	if(name.equalsIgnoreCase("month"))
	                    		month = Integer.parseInt(value);
	                    	if(name.equalsIgnoreCase("year"))
	                    		year = Integer.parseInt(value);
	                    	if(name.equalsIgnoreCase("gender"))
	                    		authorGender = GENDER.valueOf(value);
	                    	if(name.equalsIgnoreCase("nationality"))
	                    		nationality = value;
	                    	
	                    }
                    }
                }
                
                
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try
        {
        	JSONObject publisherDetails = new JSONObject();
        	publisherDetails.put("name", publisherName);
        	
        	JSONObject authorDetails = new JSONObject();
        	authorDetails.put("firstName", authorFName);
        	authorDetails.put("middleName", authorMName);
        	authorDetails.put("lastName",authorLName);
        	authorDetails.put("gender",authorGender.name());
        	
        	Date dt = new Date(year, month, day); 
        	authorDetails.put("dob",dt.getTime());
        	authorDetails.put("nationality",nationality);
        	authorDetails.put("imageFile", "/test");
        	authorDetails.put("shortBio", authorDesc); 
        	authorDetails.put("infoLink", "http://google.com"); 
        	JSONArray genre = new JSONArray();
        	genre.put(authorGenre.name());
        	authorDetails.put("genre",genre.toString());
        	authorDetails.put("type",AUTHORTYPE.LEVEL1.name());
        	int numPages = 0;			
        	if(fileName.endsWith(".doc"))
        		numPages = BookConverterUtils.convertDOCToQPEKA("/books/content/", "/var/tmp/uploads/"+fileName, title);
			else if(fileName.endsWith(".docx"))
				BookConverterUtils.convertFromDOCXToQPEKA("/books/content/", "/var/tmp/uploads/"+fileName, title);
        	
        	String id = BookContentManager.getInstance().addBook(title, publisherDetails, "/book/cover/"+title+".jpg", Integer.parseInt(bookEdition),
        			3.5f, "", bookCategory.name(), numPages, bookDesc, authorDetails);
			
			response.sendRedirect(request.getContextPath()+"/bookViewer.jsp?pageNo=0&book="+id);
        }
        catch (Exception e) {
			// TODO: handle exception
		}
	}
}
