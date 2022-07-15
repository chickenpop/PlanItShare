package com.project.tour.city;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.tour.DBUtil;
import com.project.tour.dto.CityDTO;

public class CityDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public CityDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<CityDTO> findMainCity() {
		try {
			String sql = "select * from tblCity where distrinct = \'주요도시\'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			ArrayList<CityDTO> list = new ArrayList<CityDTO>();
			
			while (rs.next()) {
				
				CityDTO dto = new CityDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setDistrinct(rs.getString("distrinct"));
				dto.setImage(rs.getString("image"));
				dto.setMainAddress(rs.getString("mainaddress"));
				
				list.add(dto);
			}
			return list;
			
		} catch (Exception e) {
			System.out.println("CityDAO.findPopularCity");
			e.printStackTrace();

		}
		return null;
	}
	
	public CityDTO findCity(String cseq) {
		try {
			
			String sql = "select * from tblCity where seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cseq);
			rs = pstmt.executeQuery();
			
			CityDTO dto = new CityDTO();
			
			while(rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setDistrinct(rs.getString("distrinct"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setMainAddress(rs.getString("mainaddress"));
			}
			return dto;
			
		} catch (Exception e) {
			System.out.println("CityDAO.findCity");
			e.printStackTrace();

		}
		
		return null;
	}
	
	

}
