package com.project.tour.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.tour.DBUtil;
import com.project.tour.dto.LoginDTO;
import com.project.tour.dto.UserDTO;

public class UserDAO {
	
	
	  	Connection conn;
	    PreparedStatement pstat;
	    Statement stat;
	    ResultSet rs;

	    public UserDAO() {
	        conn = DBUtil.open();
	    }

		public UserDTO login(LoginDTO dto) {
			
			
			try {
					
					//select * from tblUser where id = 'yoon' and pw = '1111';
					String sql = "select * from tblUser where id = ? and pw = ?";
				 	pstat = conn.prepareStatement(sql);
		            pstat.setString(1, dto.getId());
		            pstat.setString(2, dto.getPw());
		            rs = pstat.executeQuery();

		            if (rs.next()) {
		                UserDTO udto = new UserDTO();

		                udto.setId(rs.getString("id"));
		                udto.setName(rs.getString("name"));
		                udto.setTel(rs.getString("tel"));
		                udto.setGender(rs.getString("gender"));
		                udto.setPw(rs.getString("pw"));
		                udto.setProfile(rs.getString("profile"));
		                udto.setRegdate(rs.getString("regdate"));
		                udto.setActive(rs.getString("active"));
		            
		                return udto;
		            }
				
				
			} catch (Exception e) {
				System.out.println("UserDAO.login");
				e.printStackTrace();

			}
			
			return null;
		}

}
