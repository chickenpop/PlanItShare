package com.project.tour.user.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.project.tour.DBUtil;
import com.project.tour.dto.InvitationDTO;
import com.project.tour.dto.MyPageDTO;
import com.project.tour.dto.UserDTO;

public class MyPageDAO {

	Connection conn;
	PreparedStatement pstat;
	Statement stat;
	ResultSet rs;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  창현  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
	
	public MyPageDAO() {
		conn = DBUtil.open();
	}

	// 서블릿에서 음식점 목록 받아오기
	public ArrayList<MyPageDTO> fList(HashMap<String, String> map) {

		try {

			System.out.println(map);

			String sql = String
					.format("SELECT * From (select a.*, rownum as rnum from vwFood a) where rnum BETWEEN ? and ?");

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, map.get("begin"));
			pstat.setString(2, map.get("end"));

			stat = conn.createStatement();

			rs = pstat.executeQuery();

			ArrayList<MyPageDTO> fList = new ArrayList<MyPageDTO>();

			while (rs.next()) {

				MyPageDTO dto = new MyPageDTO();

				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setImage(rs.getString("image"));
				dto.setCategory(rs.getString("category"));
				dto.setLikeCnt(rs.getString("likecnt"));
				dto.setReviewCnt(rs.getString("reviewcnt"));
				dto.setReviewAvg(rs.getString("reviewavg"));

				fList.add(dto);

			}

			return fList;

		} catch (Exception e) {
			System.out.println("MyPageDAO.fList");
			e.printStackTrace();

		}

		return null;
	}

	// 서블릿에서 여행지 목록 받아오기
	public ArrayList<MyPageDTO> tList(HashMap<String, String> map) {

		try {

			String sql = "SELECT * From (select b.*, rownum as rnum from vwTour b) where rnum BETWEEN ? and ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, map.get("begin"));
			pstat.setString(2, map.get("end"));

			stat = conn.createStatement();

			rs = pstat.executeQuery();

			ArrayList<MyPageDTO> tList = new ArrayList<MyPageDTO>();

			while (rs.next()) {

				MyPageDTO dto = new MyPageDTO();

				dto.setPlacename(rs.getString("placename"));
				dto.setAddress(rs.getString("address"));
				dto.setImage(rs.getString("image"));
				dto.setCategory(rs.getString("category"));
				dto.setLikeCnt(rs.getString("likecnt"));
				dto.setReviewCnt(rs.getString("reviewcnt"));
				dto.setReviewAvg(rs.getString("reviewavg"));

				tList.add(dto);
			}

			return tList;

		} catch (Exception e) {
			System.out.println("MyPageDAO.tList");
			e.printStackTrace();

		}

		return null;

	}

	// 서블릿에서 숙소 목록 받아오기
	public ArrayList<MyPageDTO> lList(HashMap<String, String> map) {

		try {

			String sql = "SELECT * From (select c.*, rownum as rnum from vwLodging c) where rnum BETWEEN ? and ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, map.get("begin"));
			pstat.setString(2, map.get("end"));

			stat = conn.createStatement();

			rs = pstat.executeQuery();

			ArrayList<MyPageDTO> lList = new ArrayList<MyPageDTO>();

			while (rs.next()) {

				MyPageDTO dto = new MyPageDTO();
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setImage(rs.getString("image"));
				dto.setCategory(rs.getString("category"));
				dto.setLikeCnt(rs.getString("likecnt"));
				dto.setReviewCnt(rs.getString("reviewcnt"));
				dto.setReviewAvg(rs.getString("reviewavg"));

				lList.add(dto);

			}

			return lList;

		} catch (Exception e) {
			System.out.println("MyPageDAO.lList");
			e.printStackTrace();

		}

		return null;
	}

	// 음식점 총 갯수 가져오는 메소드
	public int getFoodCnt() {

		try {

			stat = conn.createStatement();

			String sql = "select count(*) as cnt from tblfood";

			rs = stat.executeQuery(sql);

			int result = 0;

			if (rs.next()) {
				result = rs.getInt("cnt");
			}

			rs.close();
			stat.close();

			return result;

		} catch (Exception e) {
			System.out.println("MyPageDAO.getFoodCnt");
			e.printStackTrace();

		}
		return 0;
	}
	
	// 여행장소 총 갯수 가져오는 메소드
	public int getTourCnt() {
		try {

			stat = conn.createStatement();

			String sql = "select count(*) as cnt from tblTour";

			rs = stat.executeQuery(sql);

			int result = 0;

			if (rs.next()) {
				result = rs.getInt("cnt");
			}

			rs.close();
			stat.close();

			return result;

		} catch (Exception e) {
			System.out.println("MyPageDAO.getTourCnt");
			e.printStackTrace();

		}
		return 0;
	}
	
	// 숙소 총 갯수 가져오는 메소드
	public int getLodgingCnt() {
		try {

			stat = conn.createStatement();

			String sql = "select count(*) as cnt from tblLodging";

			rs = stat.executeQuery(sql);

			int result = 0;

			if (rs.next()) {
				result = rs.getInt("cnt");
				System.out.println(result);
			}

			rs.close();
			stat.close();

			return result;

		} catch (Exception e) {
			System.out.println("MyPageDAO.getLodgingCnt");
			e.printStackTrace();

		}
		return 0;
	}
	
	// 서블릿에서 관심 여행일정 받아오기
	public ArrayList<MyPageDTO> lpList(HashMap<String, String> map) {

		try {

			String sql = String.format("SELECT * From (SELECT a.*, rownum as rnum From vwLikePlan a) where rnum BETWEEN ? and ?");

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, map.get("begin"));
			pstat.setString(2, map.get("end"));

			stat = conn.createStatement();

			rs = pstat.executeQuery();

			ArrayList<MyPageDTO> lpList = new ArrayList<MyPageDTO>();

			while (rs.next()) {

				MyPageDTO dto = new MyPageDTO();

				dto.setImage(rs.getString("image"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setLikeCnt(rs.getString("likecnt"));

				lpList.add(dto);
				
			}

			return lpList;

		} catch (Exception e) {
			System.out.println("MyPageDAO.tList");
			e.printStackTrace();

		}

		return null;
		
		
	}
	
	// 관심 여행지 총 갯수 불러오는 메소드
	public int getLPCnt() {

		try {

			stat = conn.createStatement();

			String sql = "select count(*) as cnt from tblLikePlan";

			rs = stat.executeQuery(sql);

			int result = 0;

			if (rs.next()) {
				result = rs.getInt("cnt");
				System.out.println(result);
			}

			rs.close();
			stat.close();

			return result;

		} catch (Exception e) {
			System.out.println("MyPageDAO.getLodgingCnt");
			e.printStackTrace();

		}
		
		return 0;
	}

	
	// 서블릿에서 내 일정 받아오기
	public ArrayList<MyPageDTO> mpList(String id, HashMap<String, String> map) {

		try {
			
			
			String sql = "select * from \r\n"
					+ "(select rownum as rnum, a.* from (select p.*, c.image,\r\n"
					+ "                    (select count(*) from tblLikePlan lp where lp.pseq = p.seq) as likecnt,\r\n"
					+ "                   (select count(*) from tblComment c where c.pseq = p.seq) as reviewcnt from tblPlan p inner join tblCity c on p.cseq = c.seq where author = ? order by p.seq desc) a) where rnum between ? and ?";
			
			pstat = conn.prepareStatement(sql);		
			
			pstat.setString(1, id);
			pstat.setString(2, map.get("begin"));
            pstat.setString(3, map.get("end"));

            stat = conn.createStatement();

            rs = pstat.executeQuery();
            
           ArrayList<MyPageDTO> mpList = new ArrayList<MyPageDTO>();
           
           while (rs.next()) {

               MyPageDTO dto = new MyPageDTO();

               dto.setSeq(rs.getString("seq"));
               dto.setTitle(rs.getString("title"));
               dto.setName(rs.getString("author"));
               dto.setReadcount(rs.getString("readcount"));
               dto.setLikeCnt(rs.getString("likecnt"));
               dto.setImage(rs.getString("image"));

               mpList.add(dto);

           }
            
           return mpList;
            
		} catch (Exception e) {
			System.out.println("MyPageDAO.mpList");
			e.printStackTrace();

		}
		
		
		
		
		return null;
	}

	// 내 일정 전체 갯수 불러오는 메소드
	   public int getmpCnt(String id) {
	      
	      try {

	         String sql = "select count(*) as cnt from tblPlan where author = ?";
	         pstat = conn.prepareStatement(sql);
	         pstat.setString(1, id);
	         
	         rs = pstat.executeQuery();

	         if (rs.next()) {
	            System.out.println(rs.getInt("cnt"));
	            return rs.getInt("cnt");
	         }

	         rs.close();
	         stat.close();

	      } catch (Exception e) {
	         System.out.println("MyPageDAO.getLodgingCnt");
	         e.printStackTrace();

	      }
	      
	      return 0;
	   }

	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  창현  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~서영 DAO 시작~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
		/**
		 * 
		 * 아이디를 받아와서 내정보를 출력하기 위한 메소드 
		 * 
		 * @author 백서영
		 * @param id
		 * @return UserDTO
		 */
		public UserDTO getMyinfo(String id) {
			try {
				String sql = "select u.*, (select count(*) from tblLodgingReview where id = ?) + (select count(*) from tblFoodReview where id = ?) + (select count(*) from tblTourReview where id = ?) as reviewcnt,(select count(*) from tblComment where id = ?) as commentcnt from tblUser u where id = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, id);
				pstat.setString(2, id);
				pstat.setString(3, id);
				pstat.setString(4, id);
				pstat.setString(5, id);
				
				rs = pstat.executeQuery();
				
				if (rs.next()) {
					UserDTO dto = new UserDTO();
					dto.setId(rs.getString("id"));
					dto.setName(rs.getString("name"));
					dto.setTel(rs.getString("tel"));
					dto.setGender(rs.getString("gender"));
					dto.setPw(rs.getString("pw"));
					dto.setProfile(rs.getString("profile"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setActive(rs.getString("active"));
					dto.setReviewcnt(rs.getString("reviewcnt"));
					dto.setCommentcnt(rs.getString("commentcnt"));
					
					return dto;
				}
				
				
				
			} catch (Exception e) {
				System.out.println("MyPageDAO.getMyinfo");
				e.printStackTrace();

			}
			return null;
		}

		
		/**
		 * 
		 * 
		 * 전화번호를 가져와서 하이픈을 넣어주는 메소드 
		 * 
		 * @author 백서영
		 * @param src
		 * @return String
		 */
		public String convertTelNo(String src) {
			String mobTelNo = src;

			if (mobTelNo != null) {
				// 일단 기존 - 전부 제거
				mobTelNo = mobTelNo.replaceAll(Pattern.quote("-"), "");

				if (mobTelNo.length() == 11) {
					// 010-1234-1234
					mobTelNo = mobTelNo.substring(0, 3) + "-" + mobTelNo.substring(3, 7) + "-" + mobTelNo.substring(7);

				} else if (mobTelNo.length() == 8) {
					// 1588-1234
					mobTelNo = mobTelNo.substring(0, 4) + "-" + mobTelNo.substring(4);
				} else {
					if (mobTelNo.startsWith("02")) { // 서울은 02-123-1234
						mobTelNo = mobTelNo.substring(0, 2) + "-" + mobTelNo.substring(2, 5) + "-" + mobTelNo.substring(5);
					} else { // 그외는 012-123-1345
						mobTelNo = mobTelNo.substring(0, 3) + "-" + mobTelNo.substring(3, 6) + "-" + mobTelNo.substring(6);
					}
				}

			}

			return mobTelNo;
		}

		
		/**
		 * 
		 * 아이디 비밀번호가 일치하면 이름, 전화번호, 프로필 사진을 수정하는 메소드 
		 * 
		 * @author 백서영
		 * @param udto
		 * @return int
		 */
		public int updateMyinfo(UserDTO udto) {
			try {
				
				String sql = "update tblUser set name = ?, tel = ?, profile = ? where id = ? and pw = ?"; 
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, udto.getName());
				pstat.setString(2, udto.getTel());
				pstat.setString(3, udto.getProfile());
				pstat.setString(4, udto.getId());
				pstat.setString(5, udto.getPw());
				
				return pstat.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("MyPageDAO.updateMyinfo");
				e.printStackTrace();

			}
			return 0;
		}

		/**
		 * 
		 * 아이디를 매개변수로 받아 비밀번호를 가져오는 메소드
		 * 
		 * @author 백서영
		 * @param id
		 * @return String 
		 */
		public String getPw(String id) {
			try {
				String sql = "select pw from tblUser where id = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, id);
				
				rs = pstat.executeQuery();
				
				if (rs.next()) {
					
					return rs.getString("pw");
					
				}
				
				
				
			} catch (Exception e) {
				System.out.println("MyPageDAO.getPw");
				e.printStackTrace();

			}
			return null;
		}

		
		/**
		 * 
		 * 나에게 초대된 목록을 불러오는 메소드
		 * 
		 * @author 백서영
		 * @param id
		 * @return ArrayList<InvitationDTO>
		 */
		public ArrayList<InvitationDTO> myinvitation(String id) {
			try {
				String sql = "select i.*, (select name from tblUser u where u.id = i.host) as name from tblInvitation i where guest = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, id);
				
				rs = pstat.executeQuery();
				
				ArrayList<InvitationDTO> list = new ArrayList<InvitationDTO>();
				
				while (rs.next()) {
					
					InvitationDTO dto = new InvitationDTO();
					
					dto.setSeq(rs.getString("seq"));
					dto.setPseq(rs.getString("pseq"));
					dto.setHost(rs.getString("host"));
					dto.setGuest(rs.getString("guest"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setName(rs.getString("name"));

					list.add(dto);

				}
				
				return list;
				
			} catch (Exception e) {
				System.out.println("MyPageDAO.myinvitation");
				e.printStackTrace();

			}
			return null;
		}

		/**
		 * 
		 * 초대를 거절하면 나의 초대목록에서 삭제되는 리스트 
		 * 
		 * @author 백서영
		 * @param seq
		 * @return int
		 */
		public int refuseInvitation(String seq) {
			try {
				
				String sql = "delete from tblInvitation where seq = ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("MyPageDAO.delInvitation");
				e.printStackTrace();

			}
			return 0;
		}

		/**
		 * 
		 * 초대를 수락하면 일정-유저 테이블에 추가되는 메소드
		 * 
		 * @author 백서영
		 * @param dto
		 * @return int
		 */
		public int AcceptInvitation(InvitationDTO dto) {
				try {
					String sql = "insert into tblPlanUser(seq, id, pseq) values(seqPlanUser.nextVal, ?, ?)";
					
					
					
					pstat = conn.prepareStatement(sql);
					
					pstat.setString(1, dto.getGuest());
					pstat.setString(2, dto.getPseq());
					
					pstat.executeUpdate();
					
					
					
				} catch (Exception e) {
					System.out.println("MyPageDAO.AcceptInvitation");
					e.printStackTrace();

				}
			return 0;
		}
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~서영 DAO 끝~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


	
	
	
	

	
	
	

	
	
	

	
	
	
	
	
	
	
}
