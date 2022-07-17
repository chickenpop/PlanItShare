package com.project.tour.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.project.tour.DBUtil;
import com.project.tour.dto.AdminDTO;
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

		public Object login(LoginDTO dto) {
			
			
			try {
					
				
					if (dto.getLoginmode().equals("user")) {
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
			            
			            
					} else if (dto.getLoginmode().equals("admin")) {
						
						String sql = "select * from tblAdmin where id = ? and pw = ?";
						pstat = conn.prepareStatement(sql);
			            pstat.setString(1, dto.getId());
			            pstat.setString(2, dto.getPw());
			            rs = pstat.executeQuery();
			            
			            if (rs.next()) {
			            	
			            	AdminDTO adto = new AdminDTO();
			            	
			            	adto.setId(rs.getString("id"));
			            	adto.setPw(rs.getString("pw"));
			           
			            	return adto;
			            }
					}
				
				
			} catch (Exception e) {
				System.out.println("UserDAO.login");
				e.printStackTrace();

			}
			
			return null;
		}

		public int register(UserDTO dto) {
			
			
			try {
				
	            String sql = "";
	            

	            // 프로필 사진 업로드한 경우
	            if (dto.getProfile() != null) { 
	                sql = "insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values (?, ?, ?, ?, ?, default, 'y', ?)";
	                
	                //insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values ('test', '테스트', '01012345678', '1111', default, default, 'y', 'm'); 
	                
	                
	                pstat = conn.prepareStatement(sql);

	                pstat.setString(1, dto.getId());
	                pstat.setString(2, dto.getName());
	                pstat.setString(3, dto.getTel());
	                pstat.setString(4, dto.getPw());
	                pstat.setString(5, dto.getProfile());
	                pstat.setString(6, dto.getGender());
	                
	                

	            } else { // 프로필 사진 업로드 x(default)
	                sql = "insert into tblUser(id, name, tel, pw, profile, regdate, active, gender) values (?, ?, ?, ?, default, default, 'y', ?)";

	                pstat = conn.prepareStatement(sql);

	                pstat.setString(1, dto.getId());
	                pstat.setString(2, dto.getName());
	                pstat.setString(3, dto.getTel());
	                pstat.setString(4, dto.getPw());
	                pstat.setString(5, dto.getGender());
	            }
	            
	            	return pstat.executeUpdate();
		
				
			} catch (Exception e) {
				System.out.println("UserDAO.register");
				e.printStackTrace();

			}
			
			
				return 0;
	
		}

		//아이디 중복검사
		public int Idcheck(String id) {
			
			
		 
			
			try {
				
				//가입 유무 확인
				String sql = "select count(*) as cnt from tblUser where id = ?";
	            pstat = conn.prepareStatement(sql);
	            pstat.setString(1, id);
	            rs = pstat.executeQuery();

	            if (rs.next()) {
	            	//cnt가 0이면 기존 회원 x
	            	return Integer.parseInt(rs.getString("cnt"));
	            }
				
	            
			} catch (Exception e) {
				System.out.println("UserDAO.Idcheck");
				e.printStackTrace();

			}
			
			
			return 0;
		
			
			
		}


}












