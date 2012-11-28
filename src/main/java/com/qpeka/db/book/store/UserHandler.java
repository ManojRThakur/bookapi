package com.qpeka.db.book.store;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.qpeka.db.book.store.tuples.User;

public class UserHandler {

	private static UserHandler instance = new UserHandler();
	private DB db = null;
	private DBCollection users = null;
	
	private UserHandler()
	{
		db = MongoAccessor.getInstance().getMongo().getDB("bookStore");
		users = db.getCollection("users");
	}
	
	public static UserHandler getInstance()
	{
		return instance;
	}
	
	public String addUser(User user)
	{
		
		BasicDBObject dObj = (BasicDBObject)user.toDBObject(true);
		WriteResult result = users.insert(dObj, WriteConcern.SAFE);
		ObjectId id =  dObj.getObjectId("_id");
		return id.toString();
	}
	
	public void updateUser(User user)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(User.ID, new ObjectId(user.get_id()));
		
		users.update(q, new BasicDBObject("$set" , user.toDBObject(true)), true, false, WriteConcern.SAFE);
	}
	
	public User getUser(String userId)
	{
		BasicDBObject q = new BasicDBObject();
		q.put(User.ID, new ObjectId(userId));
		
		DBCursor cursor = users.find(q);
		
        try 
        {
            if(cursor.hasNext()) 
            {
                BasicDBObject dObj = (BasicDBObject)cursor.next();
                User user = User.getUserfromDBObject(dObj);
                return user;
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
	
	
}
