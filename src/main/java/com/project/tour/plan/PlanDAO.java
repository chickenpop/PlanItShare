package com.project.tour.plan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.tour.DBUtil;
import com.project.tour.dto.CityDTO;
import com.project.tour.dto.CommentDTO;
import com.project.tour.dto.FoodCategoryDTO;
import com.project.tour.dto.FoodDTO;
import com.project.tour.dto.InvitationDTO;
import com.project.tour.dto.LodgingCategoryDTO;
import com.project.tour.dto.LodgingDTO;
import com.project.tour.dto.PlaceDTO;
import com.project.tour.dto.PlanDTO;
import com.project.tour.dto.PlanUserDTO;
import com.project.tour.dto.TourCategoryDTO;
import com.project.tour.dto.TourDTO;

public class PlanDAO {

	Connection conn = null;
	PreparedStatement pstat = null;
	Statement stat = null;
	ResultSet rs = null;
	
	public PlanDAO() {
		conn = DBUtil.open();
	}
	
	   //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~서영 DAO 시작~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	   /**
	    * 
	    * 일정을 관심등록순으로 출력하기 위한 메소드
	    * 
	    * @author 백서영
	    * @return ArrayList<PlanDTO>
	    */
	   public ArrayList<PlanDTO> getlist() {
	      try {
	         String sql = "select \r\n"
	               + "    p.seq,\r\n"
	               + "    p.cseq,\r\n"
	               + "    p.regdate,\r\n"
	               + "    p.readcount,\r\n"
	               + "    p.startdate,\r\n"
	               + "    p.enddate,\r\n"
	               + "    p.title,\r\n"
	               + "    p.content,\r\n"
	               + "    p.author,\r\n"
	               + "    c.name,\r\n"
	               + "    c.image,\r\n"
	               + "    (select count(*) from tblLikePlan lp where lp.pseq = p.seq) as likecnt,\r\n"
	               + "    (select count(*) from tblComment c where c.pseq = p.seq) as reviewcnt\r\n"
	               + "from tblPlan p inner join tblCity c on p.cseq = c.seq order by p.regdate desc";
	         
	         stat = conn.createStatement();
	         
	         rs = stat.executeQuery(sql);
	         
	         ArrayList<PlanDTO> list = new ArrayList<PlanDTO>();
	         
	         while (rs.next()) {
	            PlanDTO dto = new PlanDTO();
	            dto.setSeq(rs.getString("seq"));
	            dto.setCseq(rs.getString("cseq"));
	            dto.setRegdate(rs.getString("regdate"));
	            dto.setReadCount(rs.getString("readcount"));
	            dto.setStartDate(rs.getString("startdate"));
	            dto.setEndDate(rs.getString("enddate"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setAuthor(rs.getString("author"));
	            dto.setName(rs.getString("name"));
	            dto.setImage(rs.getString("image"));
	            dto.setLikecnt(rs.getString("likecnt"));
	            dto.setReviewcnt(rs.getString("reviewcnt"));
	            
	            list.add(dto);
	                  
	         }
	         
	         return list;

	      } catch (Exception e) {
	         System.out.println("PlanDAO.getlist");
	         e.printStackTrace();

	      }
	      return null;
	   }

	   /**
	    * 
	    * 
	    * 
	    * @param map
	    * @return ArrayList<LodgingDTO>
	    */
	   public ArrayList<PlanDTO> planSearch(HashMap<String, String> map) {
	      try {
	         
	         String where = "";
	                  
	            if (map.get("isSearch").equals("y")) {
	               where = " where " + map.get("column")  + " like " + "\'%" + map.get("word") + "%\'";

	            }
	         

	         String sql = String.format("select * from (select p.*, rownum as rnum from vwPPlan p %s) where rnum between %s and %s ", where, map.get("begin"), map.get("end"));
	         
	         stat = conn.createStatement();
	         
	         rs = stat.executeQuery(sql);
	         
	         ArrayList<PlanDTO> list = new ArrayList<PlanDTO>();
	         
	         while (rs.next()) {
	            PlanDTO dto = new PlanDTO();
	            dto.setSeq(rs.getString("seq"));
	            dto.setCseq(rs.getString("cseq"));
	            dto.setRegdate(rs.getString("regdate"));
	            dto.setReadCount(rs.getString("readcount"));
	            dto.setStartDate(rs.getString("startdate"));
	            dto.setEndDate(rs.getString("enddate"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setAuthor(rs.getString("author"));
	            dto.setName(rs.getString("name"));
	            dto.setImage(rs.getString("image"));
	            dto.setLikecnt(rs.getString("likecnt"));
	            dto.setReviewcnt(rs.getString("reviewcnt"));
	            
	            list.add(dto);
	         }
	         
	         return list;
	         
	      } catch (Exception e) {
	         System.out.println("PlanDAO.planSearch");
	         e.printStackTrace();

	      }
	      return null;
	   }

	   public int getTotalCount(HashMap<String, String> map) {
	      try {
	         
	         String where = "";
	         
	         if (map.get("isSearch").equals("y")) {
	            where = " where " + map.get("column")  + " like " + "\'%" + map.get("word") + "%\'";

	         }
	         
	         String sql = "select count(*) as cnt from tblPlan" + where;
	      
	         
	         stat = conn.createStatement();
	         
	         rs = stat.executeQuery(sql);
	         
	         while (rs.next()) {
	            
	            return rs.getInt("cnt");
	            
	         }
	         
	         
	      } catch (Exception e) {
	         System.out.println("PlanDAO.getTotalCount");
	         e.printStackTrace();

	      }
	      return 0;
	   }
	   
	   //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~서영 DAO 끝~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	// ===================================================수아 DAO 시작===============================================
		public PlanDTO getPlan(String seq) {
			try {
				
				String sql = "select p.*, (select count(*) from tblLikePlan lp where p.seq = lp.pseq) as likecnt, (select profile from tblUser u where u.id = p.author) as author_profile, c.name from tblPlan p inner join tblCity c on p.cseq = c.seq where p.seq = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				rs = pstat.executeQuery();

				PlanDTO dto = new PlanDTO();
				
				if (rs.next()) {
					
					dto.setSeq(rs.getString("seq"));
					dto.setCseq(rs.getString("cseq"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setReadcount(rs.getString("readcount"));
					dto.setStartdate(rs.getString("startdate"));
					dto.setEnddate(rs.getString("enddate"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setAuthor(rs.getString("author"));
					dto.setLikeCnt(rs.getString("likecnt"));
					dto.setAuthorProfile(rs.getString("author_profile"));
					dto.setCity(rs.getString("name"));
				}
				
				return dto;
				
			} catch (Exception e) {
				System.out.println("PlanDAO.getPlan");
				e.printStackTrace();
			} 
			return null;
		}
		
		public ArrayList<CommentDTO> listComment(String seq) {
			try {
				
				String sql = "select * from tblComment where pseq = ? order by seq";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				rs = pstat.executeQuery();
				
				ArrayList<CommentDTO> list = new ArrayList<>();
				
				while(rs.next()) {
					
					CommentDTO dto = new CommentDTO();
					
					dto.setSeq(rs.getString("seq"));
					dto.setContent(rs.getString("content"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setId(rs.getString("id"));
					dto.setPseq(rs.getString("pseq"));
					
					list.add(dto);
				}
				
				return list;
				
			} catch (Exception e) {
				System.out.println("PlanDAO.listComment");
				e.printStackTrace();
			} 
			
			return null;
		}

		public void addFoodPlace(String seq, ArrayList<PlaceDTO> list) {
			
			try {
				
				String sql = "select d.day, df.regdate, f.name, f.address, f.image, f.open, f.close, f.lat, f.lng, fc.category from tblDaily d inner join tblDailyFood df on df.dseq = d.seq inner join tblFood f on df.fseq = f.seq inner join tblFoodCategory fc on f.fcseq =fc.seq where pseq = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				rs = pstat.executeQuery();

				while(rs.next()) {
					
					PlaceDTO dto = new PlaceDTO();
					
					dto.setDay(rs.getString("day"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setName(rs.getString("name"));
					dto.setAddress(rs.getString("address"));
					dto.setImage(rs.getString("image"));
					dto.setOpen(rs.getString("open"));
					dto.setClose(rs.getString("close"));
					dto.setLat(rs.getString("lat"));
					dto.setLng(rs.getString("lng"));
					dto.setCategory(rs.getString("category"));
					
					list.add(dto);
				}
				
			} catch (Exception e) {
				System.out.println("PlanDAO.addFoodPlace");
				e.printStackTrace();
			} 
		}
		
		public void addTourPlace(String seq, ArrayList<PlaceDTO> list) {
			
			try {
				
				String sql = "select d.day, dt.regdate, t.placename, t.address, t.image, t.open, t.close, t.lat, t.lng, tc.category from tblDaily d inner join tblDailyTour dt on dt.dseq = d.seq inner join tblTour t on dt.tseq = t.seq inner join tblTourCategory tc on t.tcseq =tc.seq where pseq = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				rs = pstat.executeQuery();
				
				while(rs.next()) {
					
					PlaceDTO dto = new PlaceDTO();
					
					dto.setDay(rs.getString("day"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setName(rs.getString("placename"));
					dto.setAddress(rs.getString("address"));
					dto.setImage(rs.getString("image"));
					dto.setOpen(rs.getString("open"));
					dto.setClose(rs.getString("close"));
					dto.setLat(rs.getString("lat"));
					dto.setLng(rs.getString("lng"));
					dto.setCategory(rs.getString("category"));
					
					list.add(dto);
				}
				
			} catch (Exception e) {
				System.out.println("PlanDAO.addTourPlace");
				e.printStackTrace();
			} 
		}
		
		public void addLodgingPlace(String seq, ArrayList<PlaceDTO> list) {
			
			try {
				
				String sql = "select d.day, dl.regdate, l.name, l.address, l.image, l.checkin, l.checkout, l.lat, l.lng, lc.category from tblDaily d inner join tblDailyLodging dl on dl.dseq = d.seq inner join tblLodging l on dl.lseq = l.seq inner join tblLodgingCategory lc on l.lcseq =lc.seq where pseq = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				rs = pstat.executeQuery();
				
				while(rs.next()) {
					
					PlaceDTO dto = new PlaceDTO();
					
					dto.setDay(rs.getString("day"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setName(rs.getString("name"));
					dto.setAddress(rs.getString("address"));
					dto.setImage(rs.getString("image"));
					dto.setOpen(rs.getString("checkIn"));
					dto.setClose(rs.getString("checkOut"));
					dto.setLat(rs.getString("lat"));
					dto.setLng(rs.getString("lng"));
					dto.setCategory(rs.getString("category"));
					
					list.add(dto);
				}
				
			} catch (Exception e) {
				System.out.println("PlanDAO.addLodgingPlace");
				e.printStackTrace();
			} 
		}

		public void addReadCount(String seq) {
			
			try {
				
				String sql = "update tblPlan set readcount = readcount + 1 where seq = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.addTourPlace");
				e.printStackTrace();
			} 
		}
		
		public int findLike(String seq, String id) {
			try {
				
				String sql = "select count(*) as cnt from tblLikePlan where pseq = ? and id = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				pstat.setString(2, id);
				rs = pstat.executeQuery();
				
				if (rs.next()) {
					return Integer.parseInt(rs.getString("cnt")); 
				}
				
			} catch (Exception e) {
				System.out.println("FoodDAO.findLike");
				e.printStackTrace();

			} 
			return 0;
		}

		public int deleteLike(String seq, String id) {
			try {
				String sql = "delete from tblLikePlan where pseq = ? and id = ?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				pstat.setString(2, id);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("FoodDAO.deleteLike");
				e.printStackTrace();

			} 
			return 0;
		}
		
		public int insertLike(String seq, String id) {
			try {
				
				String sql = "insert into tblLikePlan values (seqLikePlan.nextVal, ?, ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, id);
				pstat.setString(2, seq);
				
				return pstat.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("FoodDAO.deleteLike");
				e.printStackTrace();

			} 
			return 0;
			
		}

		public int addComment(CommentDTO cdto) {
			try {
				
				String sql = "insert into tblComment values (seqComment.nextVal, ?, default, ?, ?)";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, cdto.getContent());
				pstat.setString(2, cdto.getId());
				pstat.setString(3, cdto.getPseq());
				
				return pstat.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("PlanDAO.addComment");
				e.printStackTrace();

			} 
			return 0;
		}

		public int delComment(String seq) {
			try {
				
				String sql = "delete from tblComment where seq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delComment");
				e.printStackTrace();

			} 
			return 0;
		}

		public int delDaily(String seq) {
			
			try {
				
				String sql = "delete from tblDaily where pseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delDaily");
				e.printStackTrace();

			} 
			return 0;
		}
		
		public int delComments(String seq) {
			
			try {
				
				String sql = "delete from tblComment where pseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delComments");
				e.printStackTrace();
				
			} 
			return 0;
		}
		
		public int delPlanUser(String seq) {
			
			try {
				
				String sql = "delete from tblPlanUser where pseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delPlanUser");
				e.printStackTrace();
				
			} 
			return 0;
		}
		
		public int delInvitation(String seq) {
			
			try {
				
				String sql = "delete from tblInvitation where pseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delInvitation");
				e.printStackTrace();
				
			} 
			return 0;
		}

		public int delLike(String seq) {

			try {
				
				String sql = "delete from tblLikePlan where pseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delLike");
				e.printStackTrace();
				
			} 
			return 0;
			
		}

		public int delPlan(String seq) {
			
			try {
				
				String sql = "delete from tblPlan where seq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delPlan");
				e.printStackTrace();
				
			} 
			return 0;
			
		}

		public ArrayList<PlanUserDTO> getPlanUser(String seq) {
			
			try {
				
				String sql = "select u.id, u.profile from tblplanuser pu inner join tblUser u on pu.id = u.id where pseq = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				rs = pstat.executeQuery();
				
				ArrayList<PlanUserDTO> list = new ArrayList<>();
				
				while(rs.next()) {
					PlanUserDTO dto = new PlanUserDTO();
					
					dto.setId(rs.getString("id"));
					dto.setProfile(rs.getString("profile"));
					
					list.add(dto);
				}
				return list;
				
				
			} catch (Exception e) {
				System.out.println("PlanDAO.getPlanUser");
				e.printStackTrace();
				
			} 
			return null;
		}

		public int delDailyFood(String seq) {
			
			try {
				
				String sql = "delete from tblDailyFood where dseq in (select distinct dseq from tblDaily d inner join tblDailyFood df on d.seq = df.dseq where d.pseq = ?)";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delDailyFood");
				e.printStackTrace();
				
			}
			return 0;
		}
		
		public int delDailyLodging(String seq) {
			
			try {
				
				String sql = "delete from tblDailyLodging where dseq in (select distinct dseq from tblDaily d inner join tblDailyLodging dl on d.seq = dl.dseq where d.pseq = ?)";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delDailyLodging");
				e.printStackTrace();
				
			}
			return 0;
		}
		
		public int delDailyTour(String seq) {
			
			try {
				
				String sql = "delete from tblDailyTour where dseq in (select distinct dseq from tblDaily d inner join tblDailyTour dt on d.seq = dt.dseq where d.pseq = ?)";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, seq);
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.delDailyTour");
				e.printStackTrace();
				
			} 
			return 0;
		}

		public ArrayList<String> getIdList(String seq, String id) {
			
			try {
				
				
				String sql = "select * from tblUser where id not in (select u.id from tblUser u inner join tblPlanUser pu on u.id = pu.id where pseq = ?) and id != ?";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, seq);
				pstat.setString(2, id);
				
				rs = pstat.executeQuery();
				
				ArrayList<String> list = new ArrayList<>();
				
				while(rs.next()) {
					list.add(rs.getString("id"));
				}
				return list;
				
			} catch (Exception e) {
				System.out.println("PlanDAO.getIdList");
				e.printStackTrace();

			} 
			
			return null;
		}

		public int makeInvite(InvitationDTO dto) {
			
			try {
				
				String sql = "insert into tblInvitation values (seqInvitation.nextval, ?, ?, ?, default)";
				
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, dto.getPseq());
				pstat.setString(2, dto.getHost());
				pstat.setString(3, dto.getGuest());
				
				return pstat.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("PlanDAO.makeInvite");
				e.printStackTrace();

			} 
			
			return 0;
		}
		
		// ===================================================수아 DAO 끝===============================================
		

		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~지윤 DAO 시작~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	    
				/**
				 * 지역 분류 목록을 가져오는 메소드
				 * 
				 * @author jiyoon
				 * 
				 * @return ArrayList<String>
				 */
				public ArrayList<String> getDistrincts() {
					
					
					try {
						
							//select distinct distrinct from tblCity;
							String sql = "select distinct distrinct from tblCity";
							
							stat = conn.createStatement();
							rs = stat.executeQuery(sql);
							
							ArrayList<String> list = new ArrayList<String>();
							
							while(rs.next()) {
								list.add(rs.getString("distrinct"));
							}
							
							return list;
						
						
					} catch (Exception e) {
						System.out.println("PlanDAO.getDistrincts");
						e.printStackTrace();

					} 
					
					
					return null;
				}



				/**
				 * 행정 구역에 해당하는 도시 목록 가져오는 메소드
				 * 
				 * @param distrinct
				 * @return ArrayList<CityDTO>
				 * 
				 */
				public ArrayList<CityDTO> getCities(String distrinct) {
					
					try {
						
						conn = DBUtil.open();
						
						//select * from tblCity where distrinct = '경상북도';
						String sql = "select * from tblCity where distrinct = ?";
						
						pstat = conn.prepareStatement(sql);
						pstat.setString(1, distrinct);
						rs = pstat.executeQuery();
						
						
						ArrayList<CityDTO> list = new ArrayList<CityDTO>();
						
						while (rs.next()) {
							
							CityDTO dto = new CityDTO();
							
							dto.setSeq(rs.getString("seq"));
							dto.setName(rs.getString("name"));
							
							list.add(dto);
							
						}
						
						return list;
						
						
					} catch (Exception e) {
						System.out.println("PlanDAO.getCities");
						e.printStackTrace();

					}
					
					
					return null;
				}


				/**
				 * 음식 카테고리를 가져오는 메소드
				 * 
				 * @author jiyoon
				 * 
				 * @return ArrayList<FoodCategoryDTO>
				 */
				public ArrayList<FoodCategoryDTO> getFoodCategory() {
					
					try {
						
						conn = DBUtil.open();
						
						String sql = "select * from tblFoodCategory";
						
						stat = conn.createStatement();
						rs = stat.executeQuery(sql);
						
						ArrayList<FoodCategoryDTO> list = new ArrayList<>();
						
						
						while (rs.next()) {
							
							FoodCategoryDTO dto = new FoodCategoryDTO();
							
							dto.setSeq(rs.getString("seq"));
							dto.setCategory(rs.getString("category"));
							
							list.add(dto);
							
						}
						
						return list;
						
						
					} catch (Exception e) {
						System.out.println("PlanDAO.getFoodCategory");
						e.printStackTrace();

					}
					
					
					return null;
				}


				/**
				 * 관광명소 카테고리를 가져오는 메소드
				 * 
				 * @author jiyoon
				 * @return ArrayList<TourCategoryDTO>
				 */
				public ArrayList<TourCategoryDTO> getTourCategory() {
					
					try {
						
						conn = DBUtil.open();
						
						String sql = "select * from tblTourCategory";
						stat = conn.createStatement();
						rs = stat.executeQuery(sql);
						
						
						ArrayList<TourCategoryDTO> list = new ArrayList<>();
						
						
						while(rs.next()) {
							
							TourCategoryDTO dto = new TourCategoryDTO();
							
							dto.setSeq(rs.getString("seq"));
							dto.setCategory(rs.getString("category"));
							
							list.add(dto);
						}
						
						
						return list;
						
						
					} catch (Exception e) {
						System.out.println("PlanDAO.getTourCategory");
						e.printStackTrace();

					}
					
					return null;
				}


				
				
				/**
				 * 숙소 카테고리를 가져오는 메소드
				 * 
				 * @author jiyoon
				 * @return ArrayList<LodgingCategoryDTO>
				 */
				public ArrayList<LodgingCategoryDTO> getLodgingCategory() {
					
						try {
							
							conn = DBUtil.open();
							
							String sql = "select * from tblLodgingCategory";
							
							
							stat = conn.createStatement();
							rs = stat.executeQuery(sql);
							
							
							ArrayList<LodgingCategoryDTO> list = new ArrayList<>();
							
							
							while (rs.next()) {
								LodgingCategoryDTO dto = new LodgingCategoryDTO();
								
								dto.setSeq(rs.getString("seq"));
								dto.setCategory(rs.getString("category"));
								
								list.add(dto);
								
							}
							
							
							return list;
							
						} catch (Exception e) {
							System.out.println("PlanDAO.getLodgingCategory");
							e.printStackTrace();

						}
					
					
					return null;
				}


				/**
				 * 
				 * 
				 * @author jiyoon
				 * @param category 
				 * @return ArrayList<FoodDTO>
				 */
				public ArrayList<FoodDTO> getFoodPlace(String category) {
					
					try {
						
						conn = DBUtil.open();
						
						//select f.seq as seq, f.name as place from tblFood f inner join tblFoodCategory fc on fc.seq = f.fcseq where fc.category = '중식';
						String sql = "select f.seq as seq, f.name as place from tblFood f inner join tblFoodCategory fc on fc.seq = f.fcseq where fc.category = ?";
						
						pstat = conn.prepareStatement(sql);
						
						pstat.setString(1, category);
						
						rs = pstat.executeQuery();
						
						ArrayList<FoodDTO> list = new ArrayList<FoodDTO>();
						
						while (rs.next()) {
							
							FoodDTO dto = new FoodDTO();
							
							dto.setSeq(rs.getString("seq"));
							dto.setPlace(rs.getString("place"));
							
							list.add(dto);
						}
						
						return list;
						
					} catch (Exception e) {
						System.out.println("PlanDAO.getFoodPlace");
						e.printStackTrace();

					}
					
					
					return null;
				}


				public ArrayList<TourDTO> getTourPlace(String category) {

					try {
						
						conn = DBUtil.open();
						
						
						//select t.seq as seq, t.placename as place from tblTour t inner join tblTourCategory tc on tc.seq = t.tcseq where tc.category = '랜드마크';
						String sql = "select t.seq as seq, t.placename as place from tblTour t inner join tblTourCategory tc on tc.seq = t.tcseq where tc.category = ?";
						
						pstat = conn.prepareStatement(sql);
						
						pstat.setString(1, category);
						
						rs = pstat.executeQuery();
						
						ArrayList<TourDTO> list = new ArrayList<TourDTO>();
						
						while (rs.next()) {
							
							TourDTO dto = new TourDTO();
							
							dto.setSeq(rs.getString("seq"));
							dto.setPlace(rs.getString("place"));
							
							list.add(dto);
						}
						
						return list;
						
						
					} catch (Exception e) {
						System.out.println("PlanDAO.getTourPlace");
						e.printStackTrace();

					}
					
					
					return null;
				}


				public ArrayList<LodgingDTO> getLodgingPlace(String category) {
					
					try {
						
						conn = DBUtil.open();
						
						//select l.seq as seq, l.name as place from tblLodging l inner join tblLodgingCategory lc on lc.seq = l.lcseq where lc.category = '호텔';
						String sql = "select l.seq as seq, l.name as place from tblLodging l inner join tblLodgingCategory lc on lc.seq = l.lcseq where lc.category = ?";
						
						pstat = conn.prepareStatement(sql);
						
						pstat.setString(1, category);
						
						rs = pstat.executeQuery();
						
						ArrayList<LodgingDTO> list = new ArrayList<LodgingDTO>();
						
						while (rs.next()) {
							
							LodgingDTO dto = new LodgingDTO();
							
							dto.setSeq(rs.getString("seq"));
							dto.setPlace(rs.getString("place"));
							
							list.add(dto);
						}
						
						return list;
						
						
					} catch (Exception e) {
						System.out.println("PlanDAO.getLodgingPlace");
						e.printStackTrace();

					}
					return null;
				}

			    
				//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~지윤 DAO 끝~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				
				public void addDaily(int i, int pseq) {
					try {
						String sql = "insert into tblDaily values (seqDaily.nextVal, ?, ?)";
						pstat = conn.prepareStatement(sql);
						pstat.setInt(1, pseq);
						pstat.setInt(2, i);
						pstat.executeUpdate();
						
					} catch (Exception e) {
						System.out.println("PlanDAO.addDaily");
						e.printStackTrace();
					}
				}
				
				public int addPlan(PlanDTO pdto) {
			         
			         try {
			            
			            
			            //insert into tblPlan(seq, cseq, regdate, readcount, startdate, enddate, title, content, author) values(seqPlan.nextVal, 1, sysdate, 80, '2022-02-03', '2022-02-05', '가족이랑 제주 여행', '가족이랑 함께 즐기기 좋은 제주도 일정.', 'yoon');
			            
			             String sql = "insert into tblPlan(seq, cseq, regdate, readcount, startdate, enddate, title, content, author) values (seqPlan.nextVal, ? , default, default, ?,  ?, ?, ?, ?)";

			                pstat = conn.prepareStatement(sql);

			                pstat.setString(1, pdto.getCseq());  
			                pstat.setString(2, pdto.getStartdate());
			                pstat.setString(3, pdto.getEnddate());
			                pstat.setString(4, pdto.getTitle());
			                pstat.setString(5, pdto.getContent());
			                pstat.setString(6, pdto.getAuthor());
			               
			               
			                return pstat.executeUpdate();
			            
			            
			         } catch (Exception e) {
			            System.out.println("PlanDAO.addPlan");
			            e.printStackTrace();

			         }
			         
			         return 0;
			      }


			      public int getMaxplanseq() {
			         
			         
			         try {
			            
			            String sql = "select max(seq) as maxSeq from tblPlan";
			            
			            stat = conn.createStatement();
			            rs = stat.executeQuery(sql);
			            
			            
			            if (rs.next()) {
			                   return rs.getInt("maxSeq");
			               }
			            
			            
			         } catch (Exception e) {
			            System.out.println("PlanDAO.getMaxplanseq");
			            e.printStackTrace();

			         }

			         
			         return 0;
			      
			         
			      }

				public void addDailyFood(int maxPlanSeq, String day, String placeSeq) {
					try {
			            
			            String sql = "insert into tblDailyFood values(seqDailyFood.nextVal, (select seq from tblDaily where pseq = ? and day = ?), ?, default)";
			            
			            pstat = conn.prepareStatement(sql);
			            pstat.setInt(1, maxPlanSeq);
			            pstat.setString(2, day);
			            pstat.setString(3, placeSeq);
			            
			            pstat.executeUpdate();
			            
			         } catch (Exception e) {
			            System.out.println("PlanDAO.getMaxplanseq");
			            e.printStackTrace();

			         }

				}
				
				public void addDailyTour(int maxPlanSeq, String day, String placeSeq) {
					try {
			            
			            String sql = "insert into tblDailyTour values(seqDailyTour.nextVal, (select seq from tblDaily where pseq = ? and day = ?), ?, default)";
			            
			            pstat = conn.prepareStatement(sql);
			            pstat.setInt(1, maxPlanSeq);
			            pstat.setString(2, day);
			            pstat.setString(3, placeSeq);
			            
			            pstat.executeUpdate();
			            
			         } catch (Exception e) {
			            System.out.println("PlanDAO.getMaxplanseq");
			            e.printStackTrace();

			         }
				}
				
				public void addDailyLodging(int maxPlanSeq, String day, String placeSeq) {
					try {
			            
			            String sql = "insert into tblDailyLodging values(seqDailyLodging.nextVal, (select seq from tblDaily where pseq = ? and day = ?), ?, default)";
			            
			            pstat = conn.prepareStatement(sql);
			            pstat.setInt(1, maxPlanSeq);
			            pstat.setString(2, day);
			            pstat.setString(3, placeSeq);
			            
			            pstat.executeUpdate();
			            
			         } catch (Exception e) {
			            System.out.println("PlanDAO.getMaxplanseq");
			            e.printStackTrace();

			         }
				}
}
