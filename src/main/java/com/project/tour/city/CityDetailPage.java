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
import com.project.tour.dto.PlanDTO;

@WebServlet("/citydetail.do")
public class CityDetailPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String seq = req.getParameter("cseq");
		
		CityDAO dao = new CityDAO();
		
		System.out.println(seq);
		
		CityDTO dto = dao.findCity(seq); 
		
		ArrayList<PlanDTO> list = dao.getLikePlan(seq);
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("list", list);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/citydetail.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String keyword = req.getParameter("keyword");
		
		resp.sendRedirect("/planitshare/city/food.do?cseq=1&distinct=name&keyword=" + keyword);
            
			
	}

}

