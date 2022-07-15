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

@WebServlet("/city.do")
public class CityMainPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String cseq = req.getParameter("cseq");
		
		CityDAO dao = new CityDAO();
		
		if (cseq == null) { // 여행지 메인 페이지일 경우
			
			ArrayList<CityDTO> list = dao.findMainCity();
			req.setAttribute("list", list);
		
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/city.jsp");
			dispatcher.forward(req, resp);
			
		} else { // 여행지 상세 페이지
			
			CityDTO dto = dao.findCity(cseq);
			
			req.setAttribute("dto", dto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/citydetail.jsp");
			dispatcher.forward(req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}

