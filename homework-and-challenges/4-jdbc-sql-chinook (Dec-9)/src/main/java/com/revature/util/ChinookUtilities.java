package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ChinookUtilities {

	public ChinookUtilities() {
		super();
	}

	public static Connection getConnection(String fileName) throws IOException, SQLException {
		
		Properties prop = new Properties();
		InputStream credentials = new FileInputStream(fileName);
		prop.load(credentials);
		
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
}
