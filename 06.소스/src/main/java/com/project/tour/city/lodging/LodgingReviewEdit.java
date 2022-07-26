package com.project.tour.city.lodging;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.LodgingReviewDTO;

@WebServlet("/city/lodgingreviewedit.do")
public class LodgingReviewEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/lodgingreviewedit.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		
		String star = req.getParameter("star");
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");
		
		LodgingReviewDTO dto = new LodgingReviewDTO();
		
		
		dto.setContent(content);
		dto.setStar(star);
		dto.setSeq(seq);
		
		System.out.println(dto);
		
		LodgingDAO dao = new LodgingDAO();
		
		int result = dao.editComment(dto);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();

		writer.printf("{\"result\": %d}", result);
		
		
		writer.close();
	}

}

