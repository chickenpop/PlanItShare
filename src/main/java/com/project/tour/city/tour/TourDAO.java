package com.project.tour.city.tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.tour.DBUtil;
import com.project.tour.dto.TourDTO;

public class TourDAO {

	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;
	
	public TourDAO() {
		conn = DBUtil.open();
	}
	
	
	/**
	 * 
	 * 여행지를 선택하면 관광명소 list을 가져오는 메소드
	 * 
	 * @author 박채은
	 * @param seq 
	 * @return ArrayList<TourDTO> 관광명소 list
	 */
	public ArrayList<TourDTO> getTourlist(String seq) {

		try {
			
			String sql = "select\r\n"
					   + "       t.seq, \r\n"
					   + "       t.placename, \r\n"
					   + "       t.address, \r\n"
					   + "       t.open, \r\n"
					   + "       t.close, \r\n"
					   + "       t.image, \r\n"
					   + "       t.cseq, \r\n"
					   + "       tc.category, \r\n"
					   + "       (select count(*) from tblLikeTour lt where lt.tseq = t.seq) as likeCnt, \r\n"
					   + "       (select count(*) from tblTourReview tr where tr.tseq = t.seq) as reviewCnt, \r\n"
					   + "       (select avg(tr.star) from tblTourReview tr where tr.tseq = t.seq) as reviewAvg\r\n"
					   + "  from tblTour t \r\n"
					   + " inner join tblCity c on t.cseq = c.seq\r\n"
					   + " inner join tblTourCategory tc on t.tcseq = tc.seq \r\n"
					   + " where c.seq = ? order by likeCnt desc";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<TourDTO> list = new ArrayList<TourDTO>();
			
			while(rs.next()) {
				
				TourDTO dto = new TourDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setPlaceName(rs.getString("placeName"));
				dto.setAddress(rs.getString("address"));
				dto.setOpen(rs.getString("open"));
				dto.setClose(rs.getString("close"));
				dto.setImage(rs.getString("image"));
				dto.setCategory(rs.getString("category"));
				dto.setLikeCnt(rs.getString("likeCnt"));
				dto.setReviewCnt(rs.getString("reviewCnt"));
				dto.setReviewAvg(rs.getString("reviewAvg"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("TourDAO.getTourlist");
			e.printStackTrace();

		}
		
		return null;
	}

}











