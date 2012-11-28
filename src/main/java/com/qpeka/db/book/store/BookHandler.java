package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.Book;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;

/*
 * 1) Primary key testing
 * 2) Index testing 
 * 3) Author testing
 * 4) Testing dependencies
 *
 */

public class BookHandler {
	
	private static BookHandler instance = new BookHandler();
	private DB db = null;
	private DBCollection books = null;
	private static final Logger logger = Logger.getLogger(BookHandler.class.getName());

	private BookHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookStore");
		books = db.getCollection("books");
		
		books.createIndex(new BasicDBObject(Book.CATEGORY, 1));
		books.createIndex(new BasicDBObject(Book.AUTHORID, 1));
		books.createIndex(new BasicDBObject(Book.TITLE, 1));
	}
	
	public static BookHandler getInstance()
	{
		return instance;
	}
	
	public String addBook(Book book)
	{
		long start = System.currentTimeMillis();
		BasicDBObject dObj = (BasicDBObject)book.toDBObject(true);
		WriteResult result = books.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		logger.log(Level.INFO, "time(addBook) = " + (System.currentTimeMillis() - start));
		return id.toString();
	}
	
	public void updateBook(Book book)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Book.ID, new ObjectId(book.get_id()));
		
		books.update(q, new BasicDBObject("$set" , book.toDBObject(true)), true, false, WriteConcern.SAFE);
	}
	
	
	public Book getBook(String id)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Book.ID, new ObjectId(id));
		
		
		DBCursor cursor = books.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Book book = Book.getBookfromDBObject(dObj);
                return book;
            }
            else
            	return null;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return null;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getBooksByCategory(CATEGORY category)
	{
		List<Book> listToReturn = new ArrayList<Book>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Book.CATEGORY, category.toString());
		
		DBCursor cursor = books.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Book book = Book.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getBooksByTitle(String title)
	{
		List<Book> listToReturn = new ArrayList<Book>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Book.TITLE, "/"+title+"/");
		
		DBCursor cursor = books.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Book book = Book.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getBooksByCriteria(String criteria)
	{
		List<Book> listToReturn = new ArrayList<Book>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Book.CATEGORY, "/"+criteria+"/");
		
		DBCursor cursor = books.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Book book = Book.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getBooksByDescription(String description)
	{
		List<Book> listToReturn = new ArrayList<Book>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Book.DESCRIPTION, "/"+description+"/");
		
		DBCursor cursor = books.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Book book = Book.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getBooksByMetaKey(String query)
	{
		List<Book> listToReturn = new ArrayList<Book>();
		
		BasicDBObject q = new BasicDBObject();
		q.put(Book.METADATA+"."+Book.SEARCHKEY, "/"+query+"/");
		
		DBCursor cursor = books.find(q);
		
        try 
        {
        	 while(cursor.hasNext()) 
             {
                 BasicDBObject dObj = (BasicDBObject)cursor.next();
                 Book book = Book.getBookfromDBObject(dObj);
                 listToReturn.add(book);
             }
             return listToReturn;
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getBooksByAuthorId(String authorId)
	{
		List<Book> listToReturn = new ArrayList<Book>();
	
		BasicDBObject q = new BasicDBObject();
		q.put(Book.AUTHORID, new ObjectId(authorId));
		
		DBCursor cursor = books.find(q);
		
        try 
        {
            while(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Book book = Book.getBookfromDBObject(dObj);
                listToReturn.add(book);
            }
            return listToReturn;
           
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return listToReturn;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getBooksByAuthorIds(List<String> aurthorIds)
	{
		List<Book> books = new ArrayList<Book>();
		
		for(String authorId : aurthorIds)
		{
			List<Book> bk = getBooksByAuthorId(authorId); // can add $in query here
			if(bk != null && bk.size() > 0)
				books.addAll(bk);
		}
		
		return books;
		
	}
	
	public List<Book> getAllBooks()
	{
		List<Book> listToReturn = new ArrayList<Book>();
		DBCursor cursor = books.find();
        try 
        {
            while(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Book book = Book.getBookfromDBObject(dObj);
                listToReturn.add(book);
            }
            return listToReturn;
           
        } 
        catch (Exception e)
        {
			e.printStackTrace();
			return null;
		}
        finally {
            cursor.close();
        }

	}
	
	public List<Book> getBooksSpecificCriteriaHierarchy(String criteria)
	{
		List<Book> books = new ArrayList<Book>();
		
		books.addAll(getBooksByTitle(criteria));
		books.addAll(getBooksByCriteria(criteria));
		books.addAll(getBooksByDescription(criteria));
		books.addAll(getBooksByMetaKey(criteria));
		
		return books;
	}
	
	
	public static void main(String[] args) {
		
		String[] titles = {"Harry Potter" , "Pride & Prejudice" , "Gone with the wind"};
		String[] authorIds = {"5055fa47c4e7aaf93796c90a" , "5055fa47c4e7aaf93796c90d" , "5055fa47c4e7aaf93796c90f" , "5055fa47c4e7aaf93796c907" , "5055fa47c4e7aaf93796c906"};
		String[] publisherIds = {"50560049c4e7a9cad3686ed1","50560049c4e7a9cad3686ed4","50560049c4e7a9cad3686ed6"};
		
		Book book = null;
		for(int i = 0; i<20;i++)
		{
			book = new Book();
			book.set_id("");
			book.setTitle(titles[i%titles.length]);
			book.setAuthorId(authorIds[i%authorIds.length]);
			book.setEdition(i);
			book.setCoverPageFile("/tmp/img"+i+".jpg");
			book.setNumPages(200 + i*10);
			book.setPublisher(publisherIds[i%publisherIds.length]);
			book.setAvgRating(3.6f);
			book.setCategory(CATEGORY.values()[i%CATEGORY.values().length]);
			
			BookHandler.getInstance().addBook(book);
		}

//		
//		List<UserRating> ratings = new ArrayList<UserRating>();
//		ratings.add(new UserRating(3, new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(4, new UserInfoIdentifier(513, "danger anna2", new Name("manoj2", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(6, new UserInfoIdentifier(514, "danger anna3", new Name("manoj3", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(2, new UserInfoIdentifier(515, "danger anna4", new Name("manoj4", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(1, new UserInfoIdentifier(516, "danger anna5", new Name("manoj5", "rameshchandra", "Thakur"))));
//		ratings.add(new UserRating(0, new UserInfoIdentifier(517, "danger anna6", new Name("manoj6", "rameshchandra", "Thakur"))));
//		
//		List<UserComments> comments = new ArrayList<UserComments>();
//		comments.add(new UserComments("Hello Comment1", new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
//		comments.add(new UserComments("Hello Comment2", new UserInfoIdentifier(512, "danger anna2", new Name("manoj2", "rameshchandra", "Thakur"))));
////		comments.add(new UserComments(123457, "Hello Comment3", new UserInfoIdentifier(512, "danger anna6", new Name("manoj6", "rameshchandra", "Thakur"))));
////		comments.add(new UserComments(123458, "Hello Comment4", new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
////		comments.add(new UserComments(123459, "Hello Comment5", new UserInfoIdentifier(512, "danger anna1", new Name("manoj1", "rameshchandra", "Thakur"))));
////		
//	//	book.setRatings(ratings);
	//	book.setComments(comments);
		
		//String x = BookHandler.getInstance().addBook(book);
	//	BookHandler.getInstance().addBookRating("504c83b6364509aed091c801", new UserRating(300, new UserInfoIdentifier(516, "danger anna4", new Name("manoj4", "rameshchandra", "Thakur"))));
		
		
		//System.out.println(BookHandler.getInstance().getBookByAuthorId("504b7a67e0c1b0da3bcafc16"));
	}
	
}
