package com.project.tour.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/idsearch.do")
public class IDSearch extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/idsearch.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
			req.setCharacterEncoding("UTF-8");

	        String name = req.getParameter("name");
	        String tel = req.getParameter("tel");

	        UserDAO dao = new UserDAO();
	        String id = dao.idSearch(name, tel);

	        if (id == null) {
	            req.setAttribute("notUser", "y");
	        } else {
	            req.setAttribute("id", id);
	        }

	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/idsearch.jsp");
	        dispatcher.forward(req, resp);
		
		

	}

}

