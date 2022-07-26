package com.project.tour.city;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.CityDTO;
import com.project.tour.dto.DistrinctDTO;


@WebServlet("/city.do")
public class CityMainPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String cseq = req.getParameter("cseq");
		String search = req.getParameter("search");
		
		CityDAO dao = new CityDAO();
		
		ArrayList<CityDTO> list = dao.findMainCity();
		ArrayList<CityDTO> rlist = dao.findRecommendCity();
		ArrayList<DistrinctDTO> dlist = dao.findSubCity();
		
		req.setAttribute("list", list);
		req.setAttribute("rlist", rlist);
		req.setAttribute("dlist", dlist);
		
		if (cseq == null) { // 여행지 메인 페이지일 경우
			
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/city.jsp");
			dispatcher.forward(req, resp);
			
		} else { // 여행지 상세 페이지
			
			resp.sendRedirect("/planitshare/citydetail.do?cseq=" + cseq);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				req.setCharacterEncoding("UTF-8");
				
				//2.
				String city = req.getParameter("city");
				
				CityDAO dao = new CityDAO(); 
				
				String cseq = dao.getCitySeq(city);
				
				if(cseq != null) { // 도시명 검색 성공
					
					resp.sendRedirect("/planitshare/city.do?cseq=" + cseq);
		  
					
				}
				else { 
	
					ArrayList<CityDTO> list = dao.findMainCity();
					ArrayList<CityDTO> rlist = dao.findRecommendCity();
					ArrayList<DistrinctDTO> dlist = dao.findSubCity();
					
					req.setAttribute("list", list);
					req.setAttribute("rlist", rlist);
					req.setAttribute("dlist", dlist);
					
					req.setAttribute("searchError", "y");
	                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/city.jsp");
	                dispatcher.forward(req, resp);
		           
		        }

	}

}

