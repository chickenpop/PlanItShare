package com.project.tour.city.food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.tour.DBUtil;

public class FoodDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public FoodDAO() {
		conn = DBUtil.open();
	}
	
	

}
