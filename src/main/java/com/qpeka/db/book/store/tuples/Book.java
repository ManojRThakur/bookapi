package com.qpeka.db.book.store.tuples;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;

/*
 * 
 * books
	{
	"_id" : "12345677",
	"title" : "Harry Potter",
	"authorId" : "13254",
	"coverPageFile" : "/tmp/file/img.jpg",
	"edition" : "1",
	"category" : "Fiction",
	"numPages" : "200",
	"publisherId" : "1345",
	"rating" : "3",
	"metaData" : "",
	"description" : "",
	"comments" : [{
				commentText : "bla bla...",
				commenter : "321",
				timeOfComment : "123123124124"
				}.......],
	"ratings" : [{
				userId : "123",
				rating : "3"
				}....]
	}
 */

public class Book {
	
	public static final String ID ="_id";
	public static final String TITLE ="title";
	public static final String AUTHORID ="authorId";
	public static final String COVERPAGEFILE ="coverPageFile";
	public static final String EDITION ="edition";
	public static final String CATEGORY ="category";
	public static final String NUMPAGES ="numPages,";
	public static final String PUBLISHERID ="publisherId";
	public static final String RATING ="avgRating";
	public static final String METADATA ="metaData";
	public static final String COMMENTS ="comments";
	public static final String RATINGS ="ratings"; 
	public static final String DESCRIPTION ="description"; 
	
	//meta Keys
	public static final String SEARCHKEY ="searchKey";
	public static final String NUMWORDSPERPAGE ="nwpp"; 
	public static final String NUMPAGESPERFILE ="nwpf"; 
	
	private String _id = "";
	private String title = "";
	private String authorId = "";
	private String coverPageFile = "";
	private int edition = 0;
	private CATEGORY category = com.qpeka.db.book.store.tuples.Constants.CATEGORY.Facts;
	private int numPages = 0;
	private String publisherId = "";
	private float avgRating = 0.0f;
	private JSONObject metaData = new JSONObject();
	private String description = "";

	public Book()
	{
		
	}
	
	public Book(String _id, String title, String authorId,
			String coverPageFile, int edition,
			com.qpeka.db.book.store.tuples.Constants.CATEGORY category,
			int numPages, String publisherId, float avgRating,
			JSONObject metaData, String description) {
		super();
		this._id = _id;
		this.title = title;
		this.authorId = authorId;
		this.coverPageFile = coverPageFile;
		this.edition = edition;
		this.category = category;
		this.numPages = numPages;
		this.publisherId = publisherId;
		this.avgRating = avgRating;
		this.metaData = metaData;
		this.description = description;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getCoverPageFile() {
		return coverPageFile;
	}

	public void setCoverPageFile(String coverPageFile) {
		this.coverPageFile = coverPageFile;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public CATEGORY getCategory() {
		return category;
	}

	public void setCategory(CATEGORY category) {
		this.category = category;
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public String getPublisher() {
		return publisherId;
	}

	public void setPublisher(String publisher) {
		this.publisherId = publisher;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public JSONObject getMetaData() {
		return metaData;
	}

	public void setMetaData(JSONObject metaData) {
		this.metaData = metaData;
	}
	
	public DBObject toDBObject(boolean insert)
	{
		BasicDBObject dbObj = new BasicDBObject();
		if(!insert)
			dbObj.put(ID, new ObjectId(_id));
		
		dbObj.put(TITLE, title);
		dbObj.put(AUTHORID, new ObjectId(authorId));
		dbObj.put(COVERPAGEFILE, coverPageFile);
		dbObj.put(EDITION, edition);
		dbObj.put(CATEGORY, category.toString());
		dbObj.put(NUMPAGES, numPages);
		dbObj.put(PUBLISHERID, publisherId);
		dbObj.put(RATING, avgRating);
		dbObj.put(DESCRIPTION, description);
		dbObj.put(METADATA, metaData.toString());
		
		return dbObj;
	}
	
	public static Book getBookfromDBObject(BasicDBObject obj)
	{
		try {
			return new  Book(obj.getString(ID), obj.getString(TITLE), obj.getString(AUTHORID), obj.getString(COVERPAGEFILE), obj.getInt(EDITION), com.qpeka.db.book.store.tuples.Constants.CATEGORY.valueOf(obj.getString(CATEGORY)),
					obj.getInt(NUMPAGES), obj.getString(PUBLISHERID), (float)obj.getDouble(RATING), new JSONObject(obj.getString(METADATA)), obj.getString(DESCRIPTION));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) throws JSONException {
		JSONObject k = new JSONObject();
		k.put("5", "uyeiweuiweuwiey\"wierywiey\"riwey");
		System.out.println(k.get("5"));
	}
}
