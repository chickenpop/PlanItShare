package com.project.tour.city;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.tour.DBUtil;
import com.project.tour.dto.CityDTO;
import com.project.tour.dto.DistrinctDTO;
import com.project.tour.dto.PlanDTO;


public class CityDAO {
	
	Connection conn = null;
	PreparedStatement pstat = null;
	Statement stat = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	
	public CityDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<CityDTO> findMainCity() {
		try {
			String sql = "select * from tblCity where distrinct = \'주요도시\'";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
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
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cseq);
			rs = pstat.executeQuery();
			
			CityDTO dto = new CityDTO();
			
			while(rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setDistrinct(rs.getString("distrinct"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setMainAddress(rs.getString("mainaddress"));
				dto.setEngname(rs.getString("engname"));
			}
			return dto;
			
		} catch (Exception e) {
			System.out.println("CityDAO.findCity");
			e.printStackTrace();

		}
		
		return null;
	}

	public ArrayList<CityDTO> findRecommendCity() {
		try {
			String sql = "select * from tblrecommendcity inner join tblCity on tblrecommendcity.sceq = tblCity.seq order by sceq asc";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<CityDTO> rlist = new ArrayList<CityDTO>();
			
			while (rs.next()) {
				
				CityDTO rdto = new CityDTO();
				
				rdto.setSeq(rs.getString("seq"));
				rdto.setName(rs.getString("name"));
				rdto.setDistrinct(rs.getString("distrinct"));
				rdto.setImage(rs.getString("image"));
				rdto.setMainAddress(rs.getString("mainaddress"));
				rdto.setCseq(rs.getString("sceq"));
				
				rlist.add(rdto);
			}
			return rlist;
			
		} catch (Exception e) {
			System.out.println("CityDAO.findRecommendCity");
			e.printStackTrace();

		}
		return null;
	}


	public String getCitySeq(String city) {
		try {
			
			String sql = "select seq from tblCity where name like ?";
			
			CityDTO dto = new CityDTO();
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, "%" + city + "%");
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				dto.setSeq(rs.getString("seq"));
				
			}
			
			return dto.getSeq();
		
		} catch (Exception e) {
			System.out.println("CityDAO.getCitySeq");
			e.printStackTrace();

		}
		return null;
	}
	
	public ArrayList<DistrinctDTO> findSubCity() {
		try {
			String sql = "select distinct distrinct from tblCity where distrinct != \'주요도시\' order by distrinct asc";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<DistrinctDTO> dlist = new ArrayList<DistrinctDTO>();
			
				
			while (rs.next()) {
				DistrinctDTO dto = new DistrinctDTO();

				dto.setDistrinct(rs.getString("distrinct"));
				
				sql = "select seq, name from tblCity where distrinct = ?";
								
				pstat = conn.prepareStatement(sql);
				
				pstat.setString(1, dto.getDistrinct());
				
				rs2 = pstat.executeQuery();
				
				ArrayList<CityDTO> cdlist = new ArrayList<CityDTO>();
				
				while(rs2.next()) {
					CityDTO cdto = new CityDTO();
					cdto.setSeq(rs2.getString("seq"));
					cdto.setName(rs2.getString("name"));
					cdlist.add(cdto);
				}
				dto.setClist(cdlist);
				
				dlist.add(dto);
				
			}
			
			return dlist;
			
		} catch (Exception e) {
			System.out.println("CityDAO.findSubCity");
			e.printStackTrace();

		}
		return null;
	}

	public ArrayList<PlanDTO> getLikePlan(String seq) {
		
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
					+ "from tblPlan p inner join tblCity c on p.cseq = c.seq \r\n"
					+ "where c.seq = ? order by likecnt desc";
			
			
			
			ArrayList<PlanDTO> list = new ArrayList<PlanDTO>();
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
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
			System.out.println("CityDAO.getLikePlan");
			e.printStackTrace();

		}
		
		return null;
	}

}
