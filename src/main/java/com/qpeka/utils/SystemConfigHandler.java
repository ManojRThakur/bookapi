package com.qpeka.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SystemConfigHandler 
{
	private static SystemConfigHandler instance = null;
	private Properties props = new Properties();
	
	private SystemConfigHandler()
	{
		try 
		{
			FileInputStream fis = new FileInputStream(new File("/props/system.properties"));
			props.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SystemConfigHandler getInstance()
	{
		if(instance == null)
			instance = new SystemConfigHandler();
		
		return instance;
	}
	
	public String getBookContentFolder()
	{
		return props.getProperty(SystemConstants.BOOKCONTENTFOLDER);
	}
	
	public String getBaseTinyURL()
	{
		return props.getProperty(SystemConstants.BASETINYURL);
	}
}
