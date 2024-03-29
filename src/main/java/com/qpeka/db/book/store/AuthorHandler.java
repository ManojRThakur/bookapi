package com.qpeka.db.book.store;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.Author;
import com.qpeka.db.book.store.tuples.Name;
import com.qpeka.db.book.store.tuples.Publisher;


public class AuthorHandler {
	
	private static AuthorHandler instance = new AuthorHandler();
	private DB db = null;
	private DBCollection authors = null;
	
	private AuthorHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookStore");
		authors = db.getCollection("authors");
	}
	
	public static AuthorHandler getInstance()
	{
		return instance;
	}
	
	public String addAuthor(Author author)
	{
		BasicDBObject dObj = (BasicDBObject)author.toDBObject(true);
		WriteResult result = authors.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		return id.toString();
	}
	
	public void updateAuthor(Author author)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Author.ID, new ObjectId(author.get_id()));
		
		authors.update(q, new BasicDBObject("$set" , author.toDBObject(true)), true, false, WriteConcern.SAFE);
	}
	
	public Author getAuthor(String id)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(Author.ID, new ObjectId(id));
		
		
		DBCursor cursor = authors.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Author author = Author.getAuthorfromDBObject(dObj);
                
                return author;
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
	
	
	public List<Author> getAuthorsByLikelyName(String query)
	{
		List<Author> listToReturn = new ArrayList<Author>();
		
		BasicDBList list = new BasicDBList();
		list.add(new BasicDBObject(Author.NAME+"."+Name.FIRSTNAME, "/"+query+"/"));
		list.add(new BasicDBObject(Author.NAME+"."+Name.MIDDLENAME, "/"+query+"/"));
		list.add(new BasicDBObject(Author.NAME+"."+Name.LASTNAME, "/"+query+"/"));
		
		BasicDBObject q = new BasicDBObject();
		q.put("$or", list);
		
		
		DBCursor cursor = authors.find(q);
		
        try 
        {
            while(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                Author author = Author.getAuthorfromDBObject(dObj);
                listToReturn.add(author);
            
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
	public static void main(String[] args) {
		
		/*Author a = null;
		
		for(int i = 0 ;i < 50 ; i ++)
		{
			a= new Author();
			a.setName(new Name("manoj"+ i, "ramesh" +i, "thakur" + i));
			a.setDob(new Date());
			a.setGender(GENDER.MALE);
			a.setInfoLink("http://google.com"+i);
			a.setImageFile("/tmp/img"+i+".jpg");
			JSONArray j = new JSONArray();
			j.put(CATEGORY.FICTION);
			j.put(CATEGORY.SUSPENSE);
			a.setGenre(j);
			a.setShortBio("BIO "+i);
			
			AuthorHandler.getInstance().addAuthor(a);
		}*/
		
		for(int i = 0;i<10;i++)
		{
			PublisherHandler.getInstance().addPublisher(new Publisher("TMH " + i, ""));
		}
		
		
		
		
	}
	
}
