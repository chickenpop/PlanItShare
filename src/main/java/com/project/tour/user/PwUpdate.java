package com.project.tour.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pwupdate.do")
public class PwUpdate extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//pwsearch -> pwupdate로 입력한 아이디 넘기기
		String id = req.getParameter("id");
        req.setAttribute("id", id);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/pwupdate.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			req.setCharacterEncoding("UTF-8");

	        String id = req.getParameter("id");
	        String pw = req.getParameter("pw");
	        String pwCheck = req.getParameter("pwCheck");
	        
	        if (pw.equals(pwCheck)) {
	        	UserDAO dao = new UserDAO();
	        	int result = dao.pwUpdate(id, pw);
	        	req.setAttribute("result", result);
	        	//pwupdate로 result값 보내기 (알림띄우기위해)
	        	
	        } else {
	        	req.setAttribute("error", "비밀번호가 일치하지 않습니다.");
	        }
	       
	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/pwupdate.jsp");

			dispatcher.forward(req, resp);
			
	}

}

