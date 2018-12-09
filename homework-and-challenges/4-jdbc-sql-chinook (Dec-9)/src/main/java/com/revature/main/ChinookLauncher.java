package com.revature.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.util.ChinookUtilities;

public class ChinookLauncher {

	public static void main(String[] args) {
		
		try {
			Connection conn = ChinookUtilities.getConnection("connection.properties");
			System.out.println(conn);
		}
		catch (IOException | SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
