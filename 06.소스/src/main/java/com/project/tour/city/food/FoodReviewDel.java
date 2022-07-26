package com.project.tour.city.food;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/city/food/view/reviewdel.do")
public class FoodReviewDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("seq");
		
		FoodDAO dao = new FoodDAO();
		int result = dao.delReview(seq);
		
		PrintWriter writer = resp.getWriter();
        
        writer.printf("{\"result\" : \"%s\"}", result);
        writer.close();

	}

}
