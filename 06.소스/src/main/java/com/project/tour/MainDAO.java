package com.project.tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.tour.dto.CityDTO;
import com.project.tour.dto.PlanDTO;
import com.project.tour.dto.RecommendPlaceDTO;

public class MainDAO {
	
	Connection conn = null;
	PreparedStatement pstat = null;
	Statement stat = null;
	ResultSet rs = null;
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~서영 DAO 시작~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * 
	 * 메인화면에 추천여행지 리스트를 출력하기 위한 메소드
	 * 
	 * @author 백서영
	 * @return ArrayList<RecommendPlaceDTO>
	 */
	public ArrayList<RecommendPlaceDTO> getRecommendList() {
		try {
			
			conn = DBUtil.open();
			
			String sql = "select \r\n"
					+ "    rc.seq,\r\n"
					+ "    c.name,\r\n"
					+ "    c.image,\r\n"
					+ "    c.mainaddress,\r\n"
					+ "    c.seq as cseq\r\n"
					+ "from tblRecommendCity rc inner join tblCity c on rc.sceq = c.seq";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<RecommendPlaceDTO> rlist = new ArrayList<RecommendPlaceDTO>();
			
			while (rs.next()) {
				RecommendPlaceDTO dto = new RecommendPlaceDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setImage(rs.getString("image"));
				dto.setMainaddress(rs.getString("mainaddress"));
				dto.setCseq(rs.getString("cseq"));
				
				rlist.add(dto);
				
			}
			
			return rlist;
			
		} catch (Exception e) {
			System.out.println("MainDAO.getRecommendList");
			e.printStackTrace();

		} finally {
			DBUtil.close();
		}
		return null;
	}

	/**
	 * 
	 * 일정에서 상위 9개의 찜목록을 가져온다.
	 * 
	 * @author 백서영
	 * @return ArrayList<PlanDTO>
	 */
	public ArrayList<PlanDTO> getPlanList() {
		
		try {
			conn = DBUtil.open();
			
			String sql = "select * from vwSPlan where rownum <= 9";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<PlanDTO> plist = new ArrayList<PlanDTO>();
			
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
				
				plist.add(dto);
						
			}
			
			return plist;

		} catch (Exception e) {
			System.out.println("PlanDAO.getlist");
			e.printStackTrace();

		}finally {
			DBUtil.close();
		}
		return null;
	}

	/**
	 * 
	 * 메인화면에서 지역명으로 지역검색시, 해당 지역으로 이동할 수 있도록 만든 메소드
	 * 
	 * @author 백서영
	 * @param word
	 * @return String
	 */
	public String findCity(String word) {
		try {
			
			conn = DBUtil.open();
			
			String sql = "select seq from tblCity where name like ?";
			
			CityDTO dto = new CityDTO();
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, "%" + word + "%");
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				dto.setSeq(rs.getString("seq"));
				
			}
			
			return dto.getSeq();
		
		} catch (Exception e) {
			System.out.println("CityDAO.getCitySeq");
			e.printStackTrace();

		}finally {
			DBUtil.close();
		}
		return null;
	}

	public String findPlan(String word) {
		try {
			
			conn = DBUtil.open();
			
			String sql = "select seq from tblPlan where title like ?";
			
			PlanDTO dto = new PlanDTO();
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, "%" + word + "%");
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
			}
			
			return dto.getSeq();
				
		} catch (Exception e) {
				System.out.println("MainDAO.findPlan");
				e.printStackTrace();

		}finally {
			DBUtil.close();
		}
		return null;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~서영 DAO 끝 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
}
