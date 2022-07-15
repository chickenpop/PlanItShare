package com.project.tour.city.tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.tour.DBUtil;
import com.project.tour.dto.TourDTO;

public class TourDAO {

	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;
	
	public TourDAO() {
		conn = DBUtil.open();
	}
	
	// 여행장소 목록을 가져오는 메소드
	public ArrayList<TourDTO> getTourlist(String seq) {

		try {
			
			String sql = "";
			
			
			
		} catch (Exception e) {
			System.out.println("TourDAO.getTourlist");
			e.printStackTrace();

		}
		
		return null;
	}

}











