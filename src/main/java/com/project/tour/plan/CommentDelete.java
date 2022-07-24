package com.project.tour.plan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.city.food.FoodDAO;

@WebServlet("/plan/view/delcomment.do")
public class CommentDelete extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("seq");
		
		PlanDAO dao = new PlanDAO();
		int result = dao.delComment(seq);
		
		PrintWriter writer = resp.getWriter();
        
        writer.printf("{\"result\" : \"%s\"}", result);
        writer.close();
	}

}

