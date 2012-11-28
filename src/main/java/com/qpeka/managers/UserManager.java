package com.qpeka.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.qpeka.db.book.store.UserHandler;
import com.qpeka.db.book.store.tuples.Address;
import com.qpeka.db.book.store.tuples.Constants.GENDER;
import com.qpeka.db.book.store.tuples.Constants.USERTYPE;
import com.qpeka.db.book.store.tuples.BookMark;
import com.qpeka.db.book.store.tuples.Name;
import com.qpeka.db.book.store.tuples.User;
import com.qpeka.db.book.store.tuples.Constants.CATEGORY;

public class UserManager {

private static UserManager instance = null;
	
	private UserManager()
	{
		
	}
	
	public static UserManager getInstance()
	{
		if(instance == null)
		{	
			synchronized (UserManager.class) {
			
				instance = new UserManager();
			}
			
		}
		
		return instance;
	}
	
	public String addUser(String firstName, String middleName, String lastName, String gender, String email, String city, String state, String addressLine1,
			String addressLine2, String addressLine3, String pincode , String userType, String[] preferences, int age , Date dob, String nationality, String phone)
	{
		Address addr = new Address(city, state, addressLine1, addressLine2, addressLine3, pincode);
		Set<CATEGORY> interests = new HashSet<CATEGORY>();
		for(String cat : preferences)
		{
			try
			{
				interests.add(CATEGORY.valueOf(cat));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		User u = new User("", new Name(firstName, middleName, lastName), GENDER.valueOf(gender), email, addr, interests, USERTYPE.valueOf(userType), new ArrayList<BookMark>(), age, dob, nationality, "", phone);
		return UserHandler.getInstance().addUser(u);
	}
	
	public void updateUser(String firstName, String middleName, String lastName, String gender, String email, String city, String state, String addressLine1,
			String addressLine2, String addressLine3, String pincode , String userType, String[] preferences, int age , Date dob, String nationality, String phone)
	{
		Address addr = new Address(city, state, addressLine1, addressLine2, addressLine3, pincode);
		Set<CATEGORY> interests = new HashSet<CATEGORY>();
		for(String cat : preferences)
		{
			try
			{
				interests.add(CATEGORY.valueOf(cat));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		User u = new User("", new Name(firstName, middleName, lastName), GENDER.valueOf(gender), email, addr, interests, USERTYPE.valueOf(userType), new ArrayList<BookMark>(), age, dob, nationality, "", phone);
		UserHandler.getInstance().updateUser(u);
	}
	
	public String getUser(String id)
	{
		return UserHandler.getInstance().getUser(id).toDBObject(false).toString();
	}
}
