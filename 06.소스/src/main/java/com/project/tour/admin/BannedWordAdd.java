package com.project.tour.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.BannedWordDTO;

@WebServlet("/admin/bannedwordadd.do")
public class BannedWordAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		req.setCharacterEncoding("UTF-8");
		
		String word = req.getParameter("word");
		
		AdminDAO dao = new AdminDAO();
		
		int res = 1;
		
		if (dao.wordCheck(word) == 0) {
			res = dao.add(word);			
		} else {
			res = 0;
		}
		
		req.setAttribute("res", res);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/bannedwordadd.jsp");
		dispatcher.forward(req, resp);
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
