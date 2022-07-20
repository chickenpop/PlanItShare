package com.project.tour.plan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.project.tour.DBUtil;
import com.project.tour.dto.PlaceDTO;
import com.project.tour.dto.PlanDTO;

public class PlanDAO {

	Connection conn;
	PreparedStatement pstmt;
	Statement stat;
	ResultSet rs;
	
	public PlanDTO getPlan(String seq) {
		try {
			
			conn = DBUtil.open();
			
			String sql = "select p.*, (select count(*) from tblLikePlan lp where p.seq = lp.pseq) as likecnt, (select profile from tblUser u where u.id = p.author) as author_profile, c.name from tblPlan p inner join tblCity c on p.cseq = c.seq where p.seq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seq);
			rs = pstmt.executeQuery();

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
		} finally {
			DBUtil.close();
		}
		return null;
	}
	

	public void addFoodPlace(String seq, ArrayList<PlaceDTO> list) {
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "select d.day, df.regdate, f.name, f.address, f.image, f.open, f.close, f.lat, f.lng, fc.category from tblDaily d inner join tblDailyFood df on df.dseq = d.seq inner join tblFood f on df.fseq = f.seq inner join tblFoodCategory fc on f.fcseq =fc.seq where pseq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seq);
			rs = pstmt.executeQuery();

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
		} finally {
			DBUtil.close();
		}
	}
	
	public void addTourPlace(String seq, ArrayList<PlaceDTO> list) {
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "select d.day, dt.regdate, t.placename, t.address, t.image, t.open, t.close, t.lat, t.lng, tc.category from tblDaily d inner join tblDailyTour dt on dt.dseq = d.seq inner join tblTour t on dt.tseq = t.seq inner join tblTourCategory tc on t.tcseq =tc.seq where pseq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seq);
			rs = pstmt.executeQuery();
			
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
		} finally {
			DBUtil.close();
		}
	}
	
	public void addLodgingPlace(String seq, ArrayList<PlaceDTO> list) {
		
		try {
			
			conn = DBUtil.open();
			
			String sql = "select d.day, dl.regdate, l.name, l.address, l.image, l.checkin, l.checkout, l.lat, l.lng, lc.category from tblDaily d inner join tblDailyLodging dl on dl.dseq = d.seq inner join tblLodging l on dl.lseq = l.seq inner join tblLodgingCategory lc on l.lcseq =lc.seq where pseq = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seq);
			rs = pstmt.executeQuery();
			
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
		} finally {
			DBUtil.close();
		}
	}
	
}
