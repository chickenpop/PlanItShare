package com.project.tour.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.city.CityDAO;
import com.project.tour.dto.CityDTO;


@WebServlet("/recommendcitylist.do")
public class RecommendCityList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CityDAO dao = new CityDAO();
		AdminDAO dao1 = new AdminDAO();
		
		req.getParameter("cseq");
		String cityseq = req.getParameter("cityseq");
		
		int res = dao1.deleteRecommendCity(cityseq);
		
		ArrayList<CityDTO> rlist = dao.findRecommendCity();
		
		req.setAttribute("rlist", rlist);

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/recommendcitylist.jsp");

		dispatcher.forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/recommendcitylist.jsp");

		dispatcher.forward(req, resp);

	}

}

