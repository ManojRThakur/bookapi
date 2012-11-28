package com.qpeka.db.book.store.tuples;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;
import com.qpeka.db.book.store.tuples.Constants.GENDER;
import com.qpeka.db.book.store.tuples.Constants.USERTYPE;

/*
 * 
 * user
	{
		_id : "",
		name : {
					firstName : "manoj",
					middleName : "R"
					lastName : "thakur"
				},
		gender : M ,
		email : {
					emailid1 : e1@gmail.com,
					emailid2 : e2@gmail.com,
					emailid3 : e3@gmail.com,
				}
		address : "something something"
		preferences : [fiction , scifi , comedy]
		userType : premium,
		bookmarks :{
					bookId : "123123",
					pages : [12,32,34,45,54,32]
					}
	
	}
 */

public class User {
	
	public static final String ID = "_id";
	public static final String NAME = "name";
	public static final String GENDER = "gender";
	public static final String DOB = "dob";
	public static final String NATIONALITY = "nationality";
	public static final String IMAGEFILE = "imageFile";
	public static final String ADDRESS = "address";
	public static final String INTERESTS = "interests";
	public static final String EMAIL = "email";
	public static final String AGE = "age";
	public static final String BOOKMARKS = "bookMarks";
	public static final String TYPE = "type";
	public static final String PHONE = "phone";
	
	private String _id = "";
	private Name name = null;
	private GENDER gender = com.qpeka.db.book.store.tuples.Constants.GENDER.MALE;
	private Address address;
	private Set<CATEGORY> interests;
	private USERTYPE type;
	private List<BookMark> bookMarks;
	private int age;
	private Date dob;
	private String nationality;
	private String imageFile;
	private String email;
	private String phone;
	
	public User()
	{
		
	}
	
	public User(String _id, Name name,
			com.qpeka.db.book.store.tuples.Constants.GENDER gender,
			String email, Address address,
			Set<CATEGORY> interests, USERTYPE type, List<BookMark> bookMarks,
			int age, Date dob, String nationality, String imageFile, String phone) {
		super();
		this._id = _id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.interests = interests;
		this.type = type;
		this.bookMarks = bookMarks;
		this.age = age;
		this.dob = dob;
		this.nationality = nationality;
		this.imageFile = imageFile;
		this.phone = phone;
	}

	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public GENDER getGender() {
		return gender;
	}
	public void setGender(GENDER gender) {
		this.gender = gender;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<CATEGORY> getInterests() {
		return interests;
	}
	public void setInterests(Set<CATEGORY> interests) {
		this.interests = interests;
	}
	public USERTYPE getType() {
		return type;
	}
	public void setType(USERTYPE type) {
		this.type = type;
	}
	public List<BookMark> getBookMarks() {
		return bookMarks;
	}
	public void setBookMarks(List<BookMark> bookMarks) {
		this.bookMarks = bookMarks;
	}
	
	public DBObject toDBObject(boolean insert)
	{
		BasicDBObject dbObj = new BasicDBObject();
		
		if(!insert)
			dbObj.put(ID, new ObjectId(_id));
		
		dbObj.put(NAME, name.toDBObject());
		dbObj.put(DOB, dob.getTime());
		dbObj.put(TYPE, type.toString());
		dbObj.put(GENDER, gender.toString());
		dbObj.put(NATIONALITY, nationality);
		dbObj.put(IMAGEFILE, imageFile);
		dbObj.put(ADDRESS, address.toDBObject());
		dbObj.put(INTERESTS, interests.toString());
		dbObj.put(EMAIL, email);
		dbObj.put(AGE, age);
		
		Set<BasicDBObject> bookMarkSet = new HashSet<BasicDBObject>();
		for(BookMark mark : bookMarks)
		{
			bookMarkSet.add((BasicDBObject)mark.toDBObject());
		}
		
		dbObj.put(BOOKMARKS, bookMarkSet);
		dbObj.put(PHONE, phone);
		
		return dbObj;
	}
	
	public static User getUserfromDBObject(BasicDBObject obj)
	{
		//return new User(obj.getString(ID), Name.getNamefromDBObject((BasicDBObject)obj.get(NAME)), com.qpeka.db.book.store.tuples.Constants.GENDER.valueOf(obj.getString(GENDER)), identities, address, interests, type, bookMarks, age, dob, nationality, imageFile)
		return new User();
	}
}
