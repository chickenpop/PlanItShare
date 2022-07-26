package com.project.tour.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.tour.DBUtil;
import com.project.tour.dto.AdminDTO;
import com.project.tour.dto.FoodReviewDTO;
import com.project.tour.dto.LodgingReviewDTO;
import com.project.tour.dto.LoginDTO;
import com.project.tour.dto.MyCommentDTO;
import com.project.tour.dto.TourReviewDTO;
import com.project.tour.dto.UserDTO;

public class UserDAO {
	
		
	  	Connection conn;
	    PreparedStatement pstat;
	    Statement stat;
	    ResultSet rs;

	    public UserDAO() {
	        conn = DBUtil.open();
	    }

	    
	  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~지윤 DAO 시작~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	    
	  		/**
	  		 * 아이디와 비밀번호가 일치하는 회원정보를 가져오는 메소드
	  		 * @author jiyoon
	  		 * 
	  		 * @param dto
	  		 * @return
	  		 */
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
	  			            
	  			            return null;
	  			            
	  			            
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
	  			            
	  			            return null;
	  					}
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.login");
	  				e.printStackTrace();

	  			}
	  			
	  			return null;
	  		}

	  		
	  		
	  		/**
	  		 * 
	  		 * 가입 메소드
	  		 * 
	  		 * @author jiyoon
	  		 * @param dto
	  		 * @return int
	  		 */
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

	  		
	  		
	  		
	  		/**
	  		 * 아이디 중복검사
	  		 * 
	  		 * @author jiyoon
	  		 * @param id
	  		 * @return int
	  		 */
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

	  		
	  		
	  		/**
	  		 * 이름과 전화번호를 받고 회원정보가 일치하는 아이디를 찾아주는 메소드
	  		 * @author jiyoon
	  		 * 
	  		 * @param name
	  		 * @param tel
	  		 * @return string
	  		 */
	  		public String idSearch(String name, String tel) {
	  			
	  			try {
	  				
	  				
	  				String sql = "select id from tblUser where name = ? and tel = ?";
	  				
	  				pstat = conn.prepareStatement(sql);
	  				pstat.setString(1, name);
	  				pstat.setString(2, tel);
	  				rs = pstat.executeQuery();
	  				
	  				if (rs.next()) {
	  					return rs.getString("id");
	  				}
	  			
	  				
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.idSearch");
	  				e.printStackTrace();

	  			}
	  			
	  			return null;
	  		}

	  		
	  	
	  		
	  		/**
	  		 *아이디, 이름, 전화번호를 받고 회원 정보가 일치하는지 확인하는 메소드 
	  		 *@author jiyoon
	  		 *
	  		 * @param id
	  		 * @param name
	  		 * @param tel
	  		 * @return int
	  		 */
	  		public int pwSearch(String id, String name, String tel) {
	  			
	  			try {
	  				
	  				String sql = "select count(*) as cnt from tblUser where name = ? and tel = ? and id = ?";
	  				
	  	            pstat = conn.prepareStatement(sql);
	  	            pstat.setString(1, name);
	  	            pstat.setString(2, tel);
	  	            pstat.setString(3, id);
	  	            rs = pstat.executeQuery();

	  	            if (rs.next()) {
	  	                return Integer.parseInt(rs.getString("cnt"));
	  	            }
	  	            
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.pwSearch");
	  				e.printStackTrace();

	  			}
	  			
	  			
	  			return 0;
	  		}


	  		public int pwUpdate(String id, String pw) {
	  			
	  			
	  			try {
	  				
	  			     	String sql = "update tblUser set pw = ? where id = ?";
	  		            pstat = conn.prepareStatement(sql);
	  		            pstat.setString(1, pw);
	  		            pstat.setString(2, id);

	  		            return pstat.executeUpdate();
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.pwUpdate");
	  				e.printStackTrace();

	  			}
	  			
	  			
	  			return 0;
	  		}


	  		public int unregister(String id) {
	  			
	  			
	  			try {
	  				

	  	            String sql = "update tblUser set name = 'not used', pw = 'not used', tel = 'not used', profile = 'not used', regdate = sysdate, gender = 'not used', active='n' where id = ?";
	  	            pstat = conn.prepareStatement(sql);
	  	            pstat.setString(1, id);

	  	            return pstat.executeUpdate();
	  				
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.unregister");
	  				e.printStackTrace();

	  			}
	  			
	  			
	  			return 0;
	  		}


	  		public int getFoodReviewCount(UserDTO dto) {

	  			try {
	  				
	  				//select count(*) as cnt from tblFoodReview fr inner join tblUser u on fr.id = u.id where u.id = 'yoon';
	  				String sql = "select count(*) as cnt from tblFoodReview fr inner join tblUser u on fr.id = u.id where u.id = ?";
	  				
	  				pstat = conn.prepareStatement(sql);
	  				
	  			    pstat.setString(1, dto.getId());
	  			    
	  	            rs = pstat.executeQuery();

	  	            if (rs.next()) {
	  	                return Integer.parseInt(rs.getString("cnt"));
	  	            }
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.getFoodReviewCount");
	  				e.printStackTrace();

	  			}
	  			
	  			
	  			return 0;
	  		}


	  		public ArrayList<FoodReviewDTO> getMyFoodReview(String id, int page) {
	  			
	  			
	  			try {
	  				
	  				int begin = (page - 1) * 20 + 1;
	  	            int end = page * 20;
	  	            
	  	            
	  	            
	  	            String sql = "select * from vwFoodReview where id = ? and rnum between ? and ?";
	  	            pstat  = conn.prepareStatement(sql);
	  	            pstat.setString(1, id);
	  	            pstat.setString(2, String.valueOf(begin));
	  	            pstat.setString(3, String.valueOf(end));

	  	            rs = pstat.executeQuery();

	  	            ArrayList<FoodReviewDTO> list = new ArrayList<>();

	  	            while (rs.next()) {

	  	                FoodReviewDTO dto = new FoodReviewDTO();

	  	                dto.setId(rs.getString("id"));
	  	                dto.setContent(rs.getString("content"));
	  	                dto.setRegdate(rs.getString("regdate"));
	  	                dto.setPlace(rs.getString("place"));
	  	                dto.setCity(rs.getString("city"));
	  	                dto.setRnum(rs.getString("rnum"));
	  	                dto.setSeq(rs.getString("seq"));
	  	                

	  	                list.add(dto);
	  	            }
	  	            
	  	            
	  	            return list;
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.getMyFoodReview");
	  				e.printStackTrace();

	  			}
	  			
	  			return null;
	  		}


	  		public int foodcheckdel(String[] checkdel) {
	  			
	  			
	  			try {
	  				
	  				String seq = "";
	  				
	  				for (int i=0; i<checkdel.length; i++) {
	  					
	  					seq += checkdel[i];
	  					
	  					if  (i < checkdel.length-1) {
	  						seq += ", ";
	  					}
	  				}
	  				
	  				
	  				//delete tblFoodReview where seq in ('1', '2');
	  				String sql = "delete tblFoodReview where seq in (" + seq + ")";
	  				
	  				 pstat = conn.prepareStatement(sql);

	  	             return pstat.executeUpdate();

	  				 
	  		         
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.checkdel");
	  				e.printStackTrace();

	  			}
	  			
	  			
	  			
	  			return 0;
	  		}


	  		public int getTourReviewCount(UserDTO dto) {
	  			
	  				try {
	  				
	  				//select count(*) as cnt from tblTourReview tr inner join tblUser u on tr.id = u.id where u.id = 'yoon';
	  				String sql = "select count(*) as cnt from tblTourReview tr inner join tblUser u on tr.id = u.id where u.id = ?";
	  				
	  				pstat = conn.prepareStatement(sql);
	  				
	  			    pstat.setString(1, dto.getId());
	  			    
	  	            rs = pstat.executeQuery();

	  	            if (rs.next()) {
	  	                return Integer.parseInt(rs.getString("cnt"));
	  	            }
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.getTourReviewCount");
	  				e.printStackTrace();

	  			}
	  			
	  			
	  			return 0;
	  		}


	  		public ArrayList<TourReviewDTO> getMyTourReview(String id, int page) {
	  			
	  			try {
	  				 
	  				int begin = (page - 1) * 20 + 1;
	  	            int end = page * 20;
	  	            
	  	            
	  	            String sql = "select * from vwTourReview where id = ? and rnum between ? and ?";
	  	            pstat  = conn.prepareStatement(sql);
	  	            pstat.setString(1, id);
	  	            pstat.setString(2, String.valueOf(begin));
	  	            pstat.setString(3, String.valueOf(end));

	  	            rs = pstat.executeQuery();

	  	            ArrayList<TourReviewDTO> list = new ArrayList<>();

	  	            while (rs.next()) {

	  	                TourReviewDTO dto = new TourReviewDTO();

	  	                dto.setId(rs.getString("id"));
	  	                dto.setContent(rs.getString("content"));
	  	                dto.setRegdate(rs.getString("regdate"));
	  	                dto.setPlace(rs.getString("place"));
	  	                dto.setCity(rs.getString("city"));
	  	                dto.setRnum(rs.getString("rnum"));
	  	                dto.setSeq(rs.getString("seq"));
	  	                

	  	                list.add(dto);
	  	            }
	  	            
	  	            
	  	            return list;
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.getMyTourReview");
	  				e.printStackTrace();

	  			}
	  			
	  			
	  			return null;
	  		}


	  		public int getLodgingReviewCount(UserDTO dto) {
	  			
	  			
	  			try {
	  				
	  				
	  				//select count(*) as cnt from tblLodgingReview lr inner join tblUser u on lr.id = u.id where u.id = 'yoon';
	  				String sql = "select count(*) as cnt from tblLodgingReview lr inner join tblUser u on lr.id = u.id where u.id = ?";
	  				
	  				pstat = conn.prepareStatement(sql);
	  				
	  			    pstat.setString(1, dto.getId());
	  			    
	  	            rs = pstat.executeQuery();

	  	            if (rs.next()) {
	  	                return Integer.parseInt(rs.getString("cnt"));
	  	            }
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.getLodgingReviewCount");
	  				e.printStackTrace();

	  			}
	  			
	  			return 0;
	  		}


	  		public ArrayList<LodgingReviewDTO> getMyLodgingReview(String id, int page) {
	  			
	  			
	  			try {
	  				
	  				int begin = (page - 1) * 20 + 1;
	  	            int end = page * 20;
	  	            
	  	            
	  	            String sql = "select * from vwLodgingReview where id = ? and rnum between ? and ?";
	  	            pstat  = conn.prepareStatement(sql);
	  	            pstat.setString(1, id);
	  	            pstat.setString(2, String.valueOf(begin));
	  	            pstat.setString(3, String.valueOf(end));

	  	            rs = pstat.executeQuery();

	  	            ArrayList<LodgingReviewDTO> list = new ArrayList<>();

	  	            while (rs.next()) {

	  	                LodgingReviewDTO dto = new LodgingReviewDTO();

	  	                dto.setId(rs.getString("id"));
	  	                dto.setContent(rs.getString("content"));
	  	                dto.setRegdate(rs.getString("regdate"));
	  	                dto.setPlace(rs.getString("place"));
	  	                dto.setCity(rs.getString("city"));
	  	                dto.setRnum(rs.getString("rnum"));
	  	                dto.setSeq(rs.getString("seq"));
	  	                

	  	                list.add(dto);
	  	            }
	  	            
	  	            
	  	            return list;
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.getMyLodgingReview");
	  				e.printStackTrace();

	  			}
	  			
	  			return null;
	  		}


	  		public int tourcheckdel(String[] checkdel) {
	  			
	  			try {
	  				
	  				String seq = "";
	  				
	  				for (int i=0; i<checkdel.length; i++) {
	  					
	  					seq += checkdel[i];
	  					
	  					if  (i < checkdel.length-1) {
	  						seq += ", ";
	  					}
	  				}
	  				
	  				
	  				
	  				String sql = "delete tblTourReview where seq in (" + seq + ")";
	  				
	  				 pstat = conn.prepareStatement(sql);

	  	             return pstat.executeUpdate();
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.tourcheckdel");
	  				e.printStackTrace();

	  			}
	  			
	  			
	  			return 0;
	  		}


	  		public int lodgingcheckdel(String[] checkdel) {
	  			
	  			
	  			try {
	  				
	  				String seq = "";
	  				
	  				for (int i=0; i<checkdel.length; i++) {
	  					
	  					seq += checkdel[i];
	  					
	  					if  (i < checkdel.length-1) {
	  						seq += ", ";
	  					}
	  				}
	  				
	  				
	  				
	  				String sql = "delete tblLodgingReview where seq in (" + seq + ")";
	  				
	  				 pstat = conn.prepareStatement(sql);

	  	             return pstat.executeUpdate();
	  	             
	  	             
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.lodgingcheckdel");
	  				e.printStackTrace();

	  			}
	  			
	  			return 0;
	  		}


	  		public int getTotalComment(String id) {
	  			
	  			try {
	  				
	  				 //select count(*) as cnt from tblComment c inner join tblUser u on c.id = u.id inner join tblPlan p on c.pseq = p.seq where u.id = 'yoon';
	  				 String sql = "select count(*) as cnt from tblComment c inner join tblUser u on c.id = u.id inner join tblPlan p on c.pseq = p.seq where u.id = ?";
	  		            pstat = conn.prepareStatement(sql);
	  		            pstat.setString(1, id);
	  		            rs = pstat.executeQuery();

	  		            if (rs.next()) {
	  		                return Integer.parseInt(rs.getString("cnt"));
	  		            }
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.getTotalComment");
	  				e.printStackTrace();

	  			}
	  			
	  			return 0;
	  		}


	  		public ArrayList<MyCommentDTO> getMyComment(String id, int page) {
	  			
	  			try {
	  				
	  				int begin = (page - 1) * 20 + 1;
	  	            int end = page * 20;
	  	            
	  	            //select * from vwGetmycomment where id = 'yoon' and rnum between 1 and 10;
	  	            
	  	            String sql = "select * from vwGetmycomment where id = ? and rnum between ? and ?";

	  	            
	  	            pstat = conn.prepareStatement(sql);
	  	            
	  	            
	  	            pstat.setString(1, id);
	  	            pstat.setString(2, String.valueOf(begin));
	  	            pstat.setString(3, String.valueOf(end));
	  	            
	  	            rs = pstat.executeQuery();


	  	            ArrayList<MyCommentDTO> list = new ArrayList<>();
	  	            
	  	            while (rs.next()) {

	  	                MyCommentDTO dto = new MyCommentDTO();

	  	                dto.setSeq(rs.getString("seq"));
	  	                dto.setTitle(rs.getString("title"));
	  	                dto.setContent(rs.getString("content"));
	  	                dto.setRegdate(rs.getString("regdate"));
	  	                dto.setRnum(rs.getString("rnum"));

	  	                list.add(dto);
	  	            }
	  	            
	  	            
	  	            return list;
	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.getMyComment");
	  				e.printStackTrace();

	  			}
	  			
	  			return null;
	  		}


	  		public int commentcheckdel(String[] checkdel) {
	  			
	  			try {
	  				
	  				
	  				String seq = "";
	  				
	  				for (int i=0; i<checkdel.length; i++) {
	  					
	  					seq += checkdel[i];
	  					
	  					if  (i < checkdel.length-1) {
	  						seq += ", ";
	  					}
	  				}
	  				
	  				
	  				//delete tblFoodReview where seq in ('1', '2');
	  				String sql = "delete tblComment where seq in (" + seq + ")";
	  				
	  				pstat = conn.prepareStatement(sql);

	  	            return pstat.executeUpdate();

	  				
	  				
	  			} catch (Exception e) {
	  				System.out.println("UserDAO.commentcheckdel");
	  				e.printStackTrace();

	  			}
	  			
	  			return 0;
	  		}
	  		
	  		
	  		
	  		
	  		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~지윤 DAO 끝~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	  

}


