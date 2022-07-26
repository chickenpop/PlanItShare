package com.project.tour.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.city.food.Pagination;

@WebServlet("/adminpage/place.do")
public class PlaceList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String distinct = "lodging";
		int page = 1;
		
		if (req.getParameter("distinct") != null) {
			distinct = req.getParameter("distinct");
		}
		
		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		AdminDAO dao = new AdminDAO();
		Pagination pagination = null;
		
		int blockRows = 20;
		int blockSize = 10;
		
		
		if (distinct.equals("lodging")) { // 숙소일 경우 (기본값)
			pagination = new Pagination(page, dao.getTotalLodgingCnt(), blockRows, blockSize);
			req.setAttribute("list", dao.listLodging(page));
			req.setAttribute("distinct", "lodging");
		} else if (distinct.equals("tour")) { // 관광명소일 경우
			pagination = new Pagination(page, dao.getTotalTourCnt(), blockRows, blockSize);
			req.setAttribute("list", dao.listTour(page));
			req.setAttribute("distinct", "tour");
		} else { // 음식점일 경우
			pagination = new Pagination(page, dao.getTotalFoodCnt(), blockRows, blockSize);
			req.setAttribute("list", dao.listFood(page));
			req.setAttribute("distinct", "food");
		}
		
		req.setAttribute("pagination", pagination);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/placelist.jsp");
		dispatcher.forward(req, resp);

	}

}
