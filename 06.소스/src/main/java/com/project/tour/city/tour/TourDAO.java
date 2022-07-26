package com.project.tour.city.tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.tour.DBUtil;
import com.project.tour.dto.TourDTO;
import com.project.tour.dto.TourLikeDTO;
import com.project.tour.dto.TourReviewDTO;

public class TourDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	ResultSet rs;
	
	public TourDAO() {
		conn = DBUtil.open();
	}
	
    /* -------------------------------------------------------------- 박채은 --------------------------------------------------------------------- */
	/**
	 * 
	 * 여행지를 선택하면 관광명소 목록을 가져오는 메소드
	 * 
	 * @author 박채은
	 * @param map 
	 * @return ArrayList<TourDTO>
	 */
	public ArrayList<TourDTO> getTourlist(HashMap<String, String> map) {

		try {
			
			String sql = "select\r\n"
					   + "       *\r\n"
					   + "  from (select \r\n"
					   + "               t.seq, \r\n"
					   + "               t.placename, \r\n"
					   + "               t.address, \r\n"
					   + "               t.open, \r\n"
					   + "               t.close, \r\n"
					   + "               t.image, \r\n"
					   + "               t.cseq, \r\n"
					   + "               tc.category, \r\n"
					   + "               (select count(*) from tblLikeTour lt where lt.tseq = t.seq) as likeCnt, \r\n"
					   + "               (select count(*) from tblTourReview tr where tr.tseq = t.seq) as reviewCnt, \r\n"
					   + "               (select round(avg(tr.star), 2) from tblTourReview tr where tr.tseq = t.seq) as reviewAvg,\r\n"
					   + "               rownum as rnum \r\n"
					   + "          from tblTour t\r\n"
					   + "         inner join tblCity c on t.cseq = c.seq\r\n"
					   + "         inner join tblTourCategory tc on t.tcseq = tc.seq \r\n"
					   + "         where c.seq = ?)\r\n"
					   + "  where rnum between ? and ? order by likeCnt desc";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, map.get("seq"));
			pstat.setString(2, map.get("begin"));
			pstat.setString(3, map.get("end"));
			
			rs = pstat.executeQuery();
			
			ArrayList<TourDTO> list = new ArrayList<TourDTO>();
			
			while(rs.next()) {
				
				TourDTO dto = new TourDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setPlaceName(rs.getString("placename"));
				dto.setAddress(rs.getString("address"));
				dto.setOpen(rs.getString("open"));
				dto.setClose(rs.getString("close"));
				dto.setImage(rs.getString("image"));
				dto.setCategory(rs.getString("category"));
				dto.setLikeCnt(rs.getString("likeCnt"));
				dto.setReviewCnt(rs.getString("reviewCnt"));
				dto.setReviewAvg(rs.getString("reviewAvg"));
				dto.setCseq(rs.getString("cseq"));
				
				list.add(dto);
			}
						
			return list;
			
		} catch (Exception e) {
			System.out.println("TourDAO.getTourlist");
			e.printStackTrace();

		}
		
		return null;
	}

	/**
	 * 
	 * 관광명소를 선택하면 관광명소의 상세정보를 가져오는 메소드
	 * 
	 * @author : 박채은
	 * @param seq
	 * @param cseq 
	 * @return TourDTO
	 */
	public TourDTO getTourOne(String seq, String cseq) {

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
					   + "       (select round(avg(tr.star), 2) from tblTourReview tr where tr.tseq = t.seq) as reviewAvg\r\n"
					   + "  from tblTour t \r\n"
					   + " inner join tblCity c on t.cseq = c.seq\r\n"
					   + " inner join tblTourCategory tc on t.tcseq = tc.seq \r\n"
					   + " where t.seq = ? and c.seq = ? order by likeCnt desc";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			pstat.setString(2, cseq);
			
			rs = pstat.executeQuery();
			
			TourDTO dto = new TourDTO();
			
			if(rs.next()) {
			
				dto.setSeq(rs.getString("seq"));
				dto.setPlaceName(rs.getString("placename"));
				dto.setAddress(rs.getString("address"));
				dto.setOpen(rs.getString("open"));
				dto.setClose(rs.getString("close"));
				dto.setImage(rs.getString("image"));
				dto.setCategory(rs.getString("category"));
				dto.setLikeCnt(rs.getString("likeCnt"));
				dto.setReviewCnt(rs.getString("reviewCnt"));
				dto.setReviewAvg(rs.getString("reviewAvg"));
				dto.setCseq(rs.getString("cseq"));
			}
						
			return dto;
			
		} catch (Exception e) {
			System.out.println("TourDAO.getTourOne");
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * 
	 * 선택한 관광명소의 리뷰 목록을 가져오는 메소드
	 * 
	 * @author : 박채은
	 * @param seq
	 * @return ArrayList<TourReviewDTO>
	 */
	public ArrayList<TourReviewDTO> getTourReviewList(String seq) {

		try {
			
			String sql = "select  \r\n"
					   + "       seq,\r\n"
					   + "       content, \r\n"
					   + "       star,\r\n"
					   + "       regdate,\r\n"
					   + "       id,\r\n"
					   + "       image\r\n"
					   + "  from tblTourReview where tseq = ? order by seq desc";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<TourReviewDTO> list = new ArrayList<TourReviewDTO>();
			
			while(rs.next()) {
				
				TourReviewDTO dto = new TourReviewDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setContent(rs.getString("content"));
				dto.setStar(rs.getString("star"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setId(rs.getString("id"));
				dto.setImage(rs.getString("image"));
				
				list.add(dto);
				
			}			
			return list;
			
		} catch (Exception e) {
			System.out.println("TourDAO.getTourReviewList");
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * 
	 * 관광명소 리뷰 등록 메소드
	 * 
	 * @author : 박채은
	 * @param dto
	 * @return int
	 */
	public int addTourReview(TourReviewDTO dto) {

		try {
			
			String sql = "insert into tblTourReview (seq, content, star, tseq, id, image) "
					   + "values (seqTourReview.nextVal, ?, ?, ?, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getContent());
			pstat.setString(2, dto.getStar());
			pstat.setString(3, dto.getSeq());
			pstat.setString(4, dto.getId());
			pstat.setString(5, dto.getImage());
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("TourDAO.addTourReview");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * 
	 * 회원이 작성한 관광명소 리뷰를 삭제하는 메소드
	 * 
	 * @author : 박채은
	 * @param seq
	 * @return int
	 */
	public int delTourReview(String seq) {

		try {
			
			String sql = "delete tblTourReview where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			int result =  pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("TourDAO.delTourReview");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * 
	 * 검색한 단어가 포함된 관광명소 목록을 가져오는 메소드
	 * 
	 * @author : 박채은
	 * @param dto
	 * @return ArrayList<TourDTO>
	 */
	public ArrayList<TourDTO> serachPlaceNameList(TourDTO dto) {

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
					   + "       (select round(avg(tr.star), 2) from tblTourReview tr where tr.tseq = t.seq) as reviewAvg\r\n"
					   + "  from tblTour t \r\n"
					   + " inner join tblCity c on t.cseq = c.seq\r\n"
					   + " inner join tblTourCategory tc on t.tcseq = tc.seq \r\n"
					   + " where c.seq = ? and t.placename like ? order by likeCnt desc";
			
			// 키워드 형변환
			String keyWord = "%" + dto.getKeyword() + "%";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getCseq());
			pstat.setString(2, keyWord);
			
			rs = pstat.executeQuery();
						
			ArrayList<TourDTO> list = new ArrayList<TourDTO>();
			
			while(rs.next()) {
				
				TourDTO tdto = new TourDTO();
				
				tdto.setSeq(rs.getString("seq"));
				tdto.setPlaceName(rs.getString("placename"));
				tdto.setAddress(rs.getString("address"));
				tdto.setOpen(rs.getString("open"));
				tdto.setClose(rs.getString("close"));
				tdto.setImage(rs.getString("image"));
				tdto.setCategory(rs.getString("category"));
				tdto.setLikeCnt(rs.getString("likeCnt"));
				tdto.setReviewCnt(rs.getString("reviewCnt"));
				tdto.setReviewAvg(rs.getString("reviewAvg"));
				tdto.setCseq(rs.getString("cseq"));
				
				list.add(tdto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("TourDAO.serachPlaceNameList");
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * 검색한 단어가 포함된 카테고리 목록을 가져오는 메소드
	 * 
	 * @author : 박채은
	 * @param dto
	 * @return ArrayList<TourDTO>
	 */
	public ArrayList<TourDTO> serachCategoryList(TourDTO dto) {

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
					   + "       (select round(avg(tr.star), 2) from tblTourReview tr where tr.tseq = t.seq) as reviewAvg\r\n"
					   + "  from tblTour t \r\n"
					   + " inner join tblCity c on t.cseq = c.seq\r\n"
					   + " inner join tblTourCategory tc on t.tcseq = tc.seq \r\n"
					   + " where c.seq = ? and tc.category like ? order by likeCnt desc";					
			
			// 키워드 형변환
			String keyWord = "%" + dto.getKeyword() + "%";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getCseq());
			pstat.setString(2, keyWord);
			
			rs = pstat.executeQuery();
						
			ArrayList<TourDTO> list = new ArrayList<TourDTO>();
			
			while(rs.next()) {
				
				TourDTO tdto = new TourDTO();
				
				tdto.setSeq(rs.getString("seq"));
				tdto.setPlaceName(rs.getString("placename"));
				tdto.setAddress(rs.getString("address"));
				tdto.setOpen(rs.getString("open"));
				tdto.setClose(rs.getString("close"));
				tdto.setImage(rs.getString("image"));
				tdto.setCategory(rs.getString("category"));
				tdto.setLikeCnt(rs.getString("likeCnt"));
				tdto.setReviewCnt(rs.getString("reviewCnt"));
				tdto.setReviewAvg(rs.getString("reviewAvg"));
				tdto.setCseq(rs.getString("cseq"));
				
				list.add(tdto);
				
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("TourDAO.serachCategoryList");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * 회원이 관심등록이 된 상태인지 확인하는 메소드
	 * 
	 * @author : 박채은
	 * @param dto
	 * @return int
	 */
	public int isTourlike(TourLikeDTO dto) {
		
		try {
			
			String sql = "select count(*) as cnt from tblLikeTour where tseq = ? and id = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getTseq());
			pstat.setString(2, dto.getId());
			
			rs = pstat.executeQuery();
			
			
			int result = 0;
			
			if (rs.next()) {
				result = rs.getInt("cnt");
			}
			

			int state = -1;
			
			if (result == 1) {
				//참여 했을경우
				state = 2; 
				
			} else {
				//참여 안했을경우
				state = 1;
			}
			
			return state;
			
		} catch (Exception e) {
			System.out.println("TourDAO.isTourlike");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * 관광명소 찜 버튼을 누르면 등록, 수정해주는 메소드
	 * 
	 * @author : 박채은
	 * @param dto
	 * @return int
	 */
	public int Tourlike(TourLikeDTO dto) {

		try {
			
			String sql = "select count(*) as cnt from tblLikeTour where tseq = ? and id = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getTseq());
			pstat.setString(2, dto.getId());
			
			rs = pstat.executeQuery();
			
			int result = 0;
			
			if (rs.next()) {
				result = rs.getInt("cnt");
			}
			

			int state = 0;
			
			if (result == 1) {
				//참여 했을경우
				state = 2; 
				
			} else {
				//참여 안했을경우
				state = 1;
			}
			
			if (state == 1) {
				//처음 참여하는 경우 insert
				sql = "insert into tblLikeTour (seq, id, tseq) values (seqLikeTour.nextVal, ?, ?)";
				
			} else if (state == 2) {
				//참여를 했는데 또 누르면 취소
				sql = "delete from tblLikeTour where id = ? and tseq = ?";	
			}
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getTseq());
			
			result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("TourDAO.Tourlike");
			e.printStackTrace();
		}
		
		return 0;
		
	}


	/**
	 * 
	 * 숙소에 찜한 횟수를 출력하기 위한 메소드
	 * 
	 * @author : 박채은
	 * @param seq
	 * @return int
	 */
	public int getTourlike(String seq) {
		
		try {
			
			String sql = "select likecnt from vwTour where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			int result = pstat.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			System.out.println("TourDAO.getTourlike");
			e.printStackTrace();
		}
		
		return 0;
	}


	/**
	 * 
	 * 리뷰에 등록된 이미지 가져오는 메소드
	 * 
	 * @author : 박채은
	 * @param seq
	 * @return TourDTO
	 */
	public TourDTO getImage(String seq) {
		
		try {
			
			String sql = "select image from tblTourReview where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);

			rs = pstat.executeQuery();
			
			TourDTO dto = new TourDTO();
			
			if(rs.next()) {
				
				dto.setImage(rs.getString("image"));
				
			}
			
			return dto;
			
		} catch (Exception e) {
			System.out.println("TourDAO.getImage");
			e.printStackTrace();
		}
		
		return null;
	}

	
	public int getTourlistCnt() {

		try {
			
			stat = conn.createStatement();
			
			String sql = "select count(*) as cnt from tblTour";
			
			rs = stat.executeQuery(sql);
			
			int result = 0;
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
						
			return result;
			
		} catch (Exception e) {
			System.out.println("TourDAO.getTourlistCnt");
			e.printStackTrace();
		}
		
		return 0;
	}
	/* -------------------------------------------------------------- 박채은 --------------------------------------------------------------------- */


}











