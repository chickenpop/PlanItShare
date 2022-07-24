package com.project.tour.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.city.CityDAO;

@WebServlet("/recommendcityadd.do")
public class RecommendCityAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String city = req.getParameter("city");
		
		CityDAO dao = new CityDAO(); 
		
		String seq = dao.getCitySeq(city);
		
		AdminDAO dao1 = new AdminDAO();
		
		int res = 0;
		
		if (dao1.seqCheck(seq) == 0) {
			res = dao1.addRecommendCity(seq);			
		} else {
			res = 0;
		}
	
		req.setAttribute("res", res);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/recommendcityadd.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
