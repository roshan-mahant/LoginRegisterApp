package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {
	static Connection con=null;
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/tempdb";
	static String username = "root";
	static String password = "admin";
	
	static {
		try {
			/*
			Properties prop = new Properties();
			prop.load(DbUtil.class.getResourceAsStream("application.properties"));		
			String driver = prop.getProperty("database.driver");
			String url = prop.getProperty("database.url");
			String username = prop.getProperty("database.username");
			String password = prop.getProperty("database.password");
			*/
			Class.forName(driver);		
			con = DriverManager.getConnection(url, username, password);
			System.out.println("database connected...");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static int register(String name, String password, String address, int age, String profession) {
		
		int rows = 0;
		
		try {
			PreparedStatement pst = con.prepareStatement("INSERT INTO User VALUES(?,?,?,?,?)");
			
			pst.setString(1,name);
			pst.setString(2,  password);
			pst.setString(3, address);
			pst.setInt(4, age);
			pst.setString(5, profession);
			
			rows = pst.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
    
    public static int login(String name, String password) {
		
		int rows = 0;
		
		try {
			PreparedStatement pst = con.prepareStatement("SELECT * FROM User WHERE name = ? AND password = ?");
			
			pst.setString(1,name);
			pst.setString(2,  password);
			
			
			ResultSet rs  = pst.executeQuery();
			
			if(rs.next())
				rows = 1;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return rows;
	}
}
