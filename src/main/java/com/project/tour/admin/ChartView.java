package com.project.tour.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.ChartDTO;

@WebServlet("/adminpage/chartview.do")
public class ChartView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AdminDAO dao = new AdminDAO();
		
		Calendar nowDate = Calendar.getInstance();
		
		String format = "yy/MM/dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		HashMap<String, String> map = new HashMap<String, String>();
			
		// 차트 시작날짜, 끝날짜
		nowDate.add(nowDate.DATE, 1);
		String end = sdf.format(nowDate.getTime());
		nowDate.add(nowDate.DATE, -7);
		String begin = sdf.format(nowDate.getTime());
		
		map.put("begin", begin);
		map.put("end", end);
		
		ArrayList<ChartDTO> mlist = dao.chartUserRegisterM(map);
		ArrayList<ChartDTO> flist = dao.chartUserRegisterF(map);	
		
		// 날짜 정보
		ArrayList<ChartDTO> wlist = dao.getChartUserRegisterWeek(map);
			
		String mdata = "[";
		
		int cnt = 0;
		
		for(ChartDTO data : mlist) {
			for(int i=cnt; i<7; i++) {
				if(data.getRegdate().replace("-", "/").substring(2, 10).equals(begin)) {
					mdata += data.getCnt() + ",";
					cnt++;
					nowDate.add(nowDate.DATE, 1);
					begin = sdf.format(nowDate.getTime());
					break;
				} else {
					mdata += "0,";	
					cnt++;
				}
				nowDate.add(nowDate.DATE, 1);
				begin = sdf.format(nowDate.getTime());
				
			}
		}
		
		for(int i=cnt; i<7; i++) {
			mdata += "0,";	
		}
		
		mdata = mdata.substring(0, mdata.length()-1);
		
		mdata += "]";
		
		// 여자 유저
		nowDate.add(nowDate.DATE, -7);
		begin = sdf.format(nowDate.getTime());
		
		String fdata = "[";
		
		cnt = 0;
		
		for(ChartDTO data : flist) {
			for(int i=cnt; i<7; i++) {
				if(data.getRegdate().replace("-", "/").substring(2, 10).equals(begin)) {
					fdata += data.getCnt() + ",";
					cnt++;
					nowDate.add(nowDate.DATE, 1);
					begin = sdf.format(nowDate.getTime());
					break;
				} else {
					fdata += "0,";	
					cnt++;
				}
				nowDate.add(nowDate.DATE, 1);
				begin = sdf.format(nowDate.getTime());
				
			}
		}
		
		for(int i=cnt; i<7; i++) {
			fdata += "0,";	
		}
		
		fdata = fdata.substring(0, fdata.length()-1);
		
		fdata += "]";		
		
		int j = 1;
		
		for(int i=0; i<wlist.size(); i++) {
			
			wlist.get(i).setMCnt(Integer.parseInt(mdata.substring(j, j+1)));
			wlist.get(i).setFCnt(Integer.parseInt(fdata.substring(j, j+1)));
			j+=2;
			
			double m = (wlist.get(i).getMCnt() + wlist.get(i).getFCnt());
			
			double mPercent;
			double fPercent;
			
			if(m != 0.0) {
			    mPercent = Math.round(((wlist.get(i).getMCnt()/m)) * 100.0);
			    fPercent = Math.round(((wlist.get(i).getFCnt()/m)) * 100.0);
			} else {
				mPercent = 0.0;
				fPercent = 0.0;
			}
			wlist.get(i).setMPercent(mPercent);
			wlist.get(i).setFPercent(fPercent);
			
		}
		
		req.setAttribute("mdata", mdata);
		req.setAttribute("fdata", fdata);
		req.setAttribute("wlist", wlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/chartview.jsp");
		
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}

