package com.project.tour.city.lodging;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.LodgingReviewDTO;

@WebServlet("/city/lodgingreviewmore.do")
public class LodgingreviewMore extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/lodging/lodgingreviewmore.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String lseq = req.getParameter("lseq");
		String first = req.getParameter("first");
		String last = req.getParameter("last");
		
		/*
		System.out.println("lseq : " + lseq);
		System.out.println("first : " + first);
		System.out.println("last : " + last);
		*/
		
		LodgingDAO dao = new LodgingDAO();
		ArrayList<LodgingReviewDTO> list  = dao.reviewMore(lseq, first, last);
		
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/lodging/lodgingreviewmore.jsp");

		dispatcher.forward(req, resp);
		
		
		
	}

}

