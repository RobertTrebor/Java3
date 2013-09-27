package de.cimdata.kundenreg.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnect {
	
	private static final String DB = "java3";
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String USR = "root"; 
	private static final String PWD = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private Connection con = null;
	
	private static DBConnect instance = null;
	
	private DBConnect(){
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL+DB,USR,PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DBConnect getInstance(){
		if(instance == null){
			instance = new DBConnect();
		}
		return instance;
	}
	
	public Connection getConnection(){
		return con;
	}
	
	public void closeConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
