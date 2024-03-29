package com.qpeka.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qpeka.db.book.store.tuples.Book;
import com.qpeka.managers.BookContentManager;

/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * returns the cover image given a book. It assumes the following dir structure /coverimages/books/<bookId>/<imagefile>.jpg  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		//FileInputStream fs = new FileInputStream(new File("/home/manoj/pride.LZZZZZZZ.jpg"));
		
		String bookId = request.getParameter("book");
		if(bookId != null && bookId.length() > 0)
		{
			response.setContentType("image/jpeg");
			OutputStream out = null;
			try
			{
				Book bk = BookContentManager.getInstance().getBookDetails(bookId);
				
				File f = new File(bk.getCoverPageFile());
				BufferedImage bi = ImageIO.read(f);
				out = response.getOutputStream();
				ImageIO.write(bi, "jpg", out);
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				try 
				{
					out.close();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
}
