package com.project.tour.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.tour.DBUtil;
import com.project.tour.dto.UserDTO;

public class AdminDAO {

	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	ResultSet rs;
	
	public AdminDAO() {
		conn = DBUtil.open();
	}
	
	/**
	 * 
	 * 모든 회원정보의 목록을 가져오는 메소드
	 * 
	 * @author : 박채은
	 * @return ArrayList<UserDTO>
	 */
	public ArrayList<UserDTO> getUserList() {

		try {
			
			String sql = "select id, name, gender, tel, active, to_char(regdate, 'yyyy.mm.dd') as regdate from tblUser";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
			
			while(rs.next()) {
				
				UserDTO dto = new UserDTO();
				
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setTel(rs.getString("tel"));
				dto.setActive(rs.getString("active"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.getUserList");
			e.printStackTrace();
		}
		
		return null;
	}

}
