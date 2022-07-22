package com.project.tour.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.tour.DBUtil;
import com.project.tour.dto.ChartDTO;
import com.project.tour.dto.UserDTO;

public class AdminDAO {

	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	ResultSet rs;
	
	public AdminDAO() {
		conn = DBUtil.open();
	}
	
	/* -------------------------------------------------------------- 박채은 --------------------------------------------------------------------- */
	/**
	 * 
	 * 모든 회원정보의 목록을 가져오는 메소드
	 * 
	 * @author : 박채은
	 * @param map 
	 * @return ArrayList<UserDTO>
	 */
	public ArrayList<UserDTO> getUserList(HashMap<String, String> map) {

		try {
			
			String sql = "select * from (select id, name, gender, tel, active, to_char(regdate, 'yyyy.mm.dd') as regdate, rownum as rnum from tblUser u) where rnum between ? and ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, map.get("begin"));
			pstat.setString(2, map.get("end"));
			
			rs = pstat.executeQuery();
			
			ArrayList<UserDTO> list = new ArrayList<UserDTO>();
			
			while(rs.next()) {
				
				UserDTO dto = new UserDTO();
				
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setTel(rs.getString("tel"));
				dto.setActive(rs.getString("active"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
				
			}
						
			return list;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.getUserList");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * 회원가입한 모든 회원의 총 인원수를 가져오는 메소드
	 * 
	 * @author : 박채은
	 * @return int
	 */
	public int getUserCnt() {

		try {

			stat = conn.createStatement();
		
			String sql = "select count(id) as cnt from tblUser";
				
			rs = stat.executeQuery(sql);
			
			int result = 0;
			if(rs.next()) {
				result = rs.getInt("cnt");
			}			
			
			return result;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.getUserCnt");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 
	 * 일정기간동안 회원가입한 남자 회원의 수를 기간별로 가져오는 메소드
	 * begin 시작날짜
	 * end 끝날짜
	 * 
	 * @author : 박채은
	 * @param map 
	 * @return ArrayList<ChartDTO>
	 */
	public ArrayList<ChartDTO> chartUserRegisterM(HashMap<String, String> map) {

		try {
			
			String sql = "select \r\n"
					  + "       count(*) as cnt,\r\n"
					  + "       to_date(regdate, 'yy/mm/dd') as regdate\r\n"
					  + "  from tblUser \r\n"
					  + " where regdate >= to_date(?, 'yy/mm/dd') and regdate <= to_date(?, 'yy/mm/dd') and gender = 'm'\r\n"
					  + " group by to_date(regdate, 'yy/mm/dd')\r\n"
					  + " order by regdate";
			
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, map.get("begin"));
			pstat.setString(2, map.get("end"));
			
			rs = pstat.executeQuery();
			
			ArrayList<ChartDTO> mlist = new ArrayList<ChartDTO>(); 
			
			while(rs.next()) {
				
				ChartDTO dto = new ChartDTO();
				
				dto.setCnt(rs.getString("cnt"));
				dto.setRegdate(rs.getString("regdate"));
				
				mlist.add(dto);
				
			}
			
			return mlist;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.chartUserRegister");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * 일정기간동안 회원가입한 여자 회원의 수를 기간별로 가져오는 메소드
	 * begin 시작날짜
	 * end 끝날짜
	 * 
	 * @author : 박채은
	 * @param map
	 * @return ArrayList<ChartDTO>
	 */
	public ArrayList<ChartDTO> chartUserRegisterF(HashMap<String, String> map) {

		try {
			
			String sql = "select \r\n"
					   + "       count(*) as cnt,\r\n"
					   + "       to_date(regdate, 'yy/mm/dd') as regdate\r\n"
					   + "  from tblUser \r\n"
					   + " where regdate >= to_date(?, 'yy/mm/dd') and regdate <= to_date(?, 'yy/mm/dd') and gender = 'f'\r\n"
					   + " group by to_date(regdate, 'yy/mm/dd')\r\n"
					   + " order by regdate";
			
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, map.get("begin"));
			pstat.setString(2, map.get("end"));
			
			rs = pstat.executeQuery();
			
			ArrayList<ChartDTO> flist = new ArrayList<ChartDTO>(); 
			
			while(rs.next()) {
				
				ChartDTO dto = new ChartDTO();
				
				dto.setCnt(rs.getString("cnt"));
				dto.setRegdate(rs.getString("regdate"));
				
				flist.add(dto);
				
			}
			
			return flist;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.chartUserRegisterF");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * 일정기간 날짜를 가져오는 메소드
	 * 
	 * @author : 박채은
	 * @param map
	 * @return ArrayList<ChartDTO>
	 */
	public ArrayList<ChartDTO> getChartUserRegisterWeek(HashMap<String, String> map) {

		try {
			
			String sql = "select\r\n"
					      + "    to_date(?, 'yy-mm-dd') + (level-1) as regdate\r\n"
					      + "from dual\r\n"
					      + "    connect by level <= (to_date(?, 'yy-mm-dd') - to_date(?, 'yy-mm-dd'))";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, map.get("begin"));
			pstat.setString(2, map.get("end"));
			pstat.setString(3, map.get("begin"));
			
			rs = pstat.executeQuery();
			
			ArrayList<ChartDTO> wlist = new ArrayList<ChartDTO>();
			
			while(rs.next()) {
				
				ChartDTO cdto = new ChartDTO();
				
				cdto.setRegdate(rs.getString("regdate").substring(0, 11));
				
				wlist.add(cdto);
			}
			
			return wlist;
			
		} catch (Exception e) {
			System.out.println("AdminDAO.getChartUserRegisterWeek");
			e.printStackTrace();
		}
		
		return null;
	}
	/* -------------------------------------------------------------- 박채은 --------------------------------------------------------------------- */

}
