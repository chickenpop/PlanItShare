package com.project.tour;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	private static Connection conn = null; //밑에 close클래스에서도 사용하기 위해 여기로 뺌
	
	public static Connection open() {
		
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "planAcc";
		String pw = "java1234";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
			
		} catch (Exception e) {
			System.out.println("DBUtil.open");
			e.printStackTrace();
		}
		
		return null;
		
	} //open
	
	//오버로딩도 해보자
	public static Connection open(String server, String id, String pw) {
		
		
		
		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, id, pw);
			
			return conn;
			
			
		} catch (Exception e) {
			System.out.println("DBUtil.open");
			e.printStackTrace();
		}
		
		return null;
		
	} //open(오버로딩)
	
	
	
	
	public static void close() {
		
		try {

			conn.close();
			
		} catch (Exception e) {
			System.out.println("DBUtil.close");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
} //DBUtil

