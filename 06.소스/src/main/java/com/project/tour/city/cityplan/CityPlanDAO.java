package com.project.tour.city.cityplan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.tour.DBUtil;
import com.project.tour.dto.CityDTO;

public class CityPlanDAO {

	Connection conn;
	PreparedStatement pstat;
	Statement stat;
	ResultSet rs;

	public CityPlanDAO() {
		conn = DBUtil.open();
	}
	
	// 서블릿에서 제주일정 목록 받아오기
	public ArrayList<CityDTO> jList(HashMap<String, String> map) {

		try {
			
			String where = "";
			
			
				if (map.get("isSearch").equals("y")) {
					where = String.format(" where %s like '%%%s%%'", map.get("column"), map.get("word"));	
			}
			
				

			String sql = String.format("SELECT * From (SELECT a.*, rownum as rnum From vwPlan a %s) where rnum BETWEEN %s and %s", where, map.get("begin"), map.get("end"));


				
			
			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<CityDTO> jList = new ArrayList<CityDTO>();

			while (rs.next()) {

				CityDTO dto = new CityDTO();

		
				dto.setImage(rs.getString("image"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setLikeCnt(rs.getString("likecnt"));
				

				jList.add(dto);

			}

			return jList;

		} catch (Exception e) {
			System.out.println("CityPlanDAO.jList");
			e.printStackTrace();

		}

		return null;

	}// jList

	// 일정게시판 총 게시물수 가져오는 메소드
	public int getjpCnt() {

		try {

			stat = conn.createStatement();

			String sql = "select count(*) as cnt from tblplan";

			rs = stat.executeQuery(sql);

			int result = 0;

			if (rs.next()) {
				result = rs.getInt("cnt");
			}

			rs.close();
			stat.close();

			return result;

		} catch (Exception e) {
			System.out.println("CityPlanDAO.getjpCnt");
			e.printStackTrace();

		}

		return 0;
	}
	
	

}// class
