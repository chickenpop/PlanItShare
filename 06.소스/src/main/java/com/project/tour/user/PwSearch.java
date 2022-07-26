package com.project.tour.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pwsearch.do")
public class PwSearch extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/pwsearch.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		  req.setCharacterEncoding("UTF-8");

	        String id = req.getParameter("id");
	        String name = req.getParameter("name");
	        String tel = req.getParameter("tel");

	        UserDAO dao = new UserDAO();
	        int result = dao.pwSearch(id, name, tel);

	        if (result == 1) { // 가입한 회원이면

	        
	            req.setAttribute("id", id);
	            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/pwupdate.jsp");
	            dispatcher.forward(req, resp);

	        } else { // 가입하지 않은 회원이면

	            req.setAttribute("notUser", "y");
	            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/pwsearch.jsp");
	            dispatcher.forward(req, resp);
	        }
		
		
	}

}

