package com.project.tour.user.mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.UserDTO;
import com.project.tour.user.UserDAO;

@WebServlet("/mypage/mypwedit.do")
public class MyPwEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String id = req.getParameter("id");

		req.setAttribute("id", id);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/mypwedit.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pw = req.getParameter("pw");
		String newPw = req.getParameter("newPw");
		String checkPw = req.getParameter("checkPw");
		
		HttpSession session = req.getSession();
		
		UserDTO user = (UserDTO)session.getAttribute("auth");
		
		String id = user.getId();
		
		UserDAO udao = new UserDAO();

		MyPageDAO mdao = new MyPageDAO(); 
		
		String userPw = mdao.getPw(id);
		
		 if (pw.equals(userPw) && newPw.equals(checkPw))  {
			 
        	int result = udao.pwUpdate(id, newPw);
        	req.setAttribute("result", result);
        	//pwupdate로 result값 보내기 (알림띄우기위해)
        	
        } else if (!pw.equals(userPw)) {
        	req.setAttribute("error1", "기존의 비밀번호와 입력한 비밀번호가 일치하지 않습니다.");
        	
        } else if (!newPw.equals(checkPw)) {
        
        	req.setAttribute("error2", "새로등록할 비밀번호가 일치하지 않습니다.");
        }
	       
	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/mypwedit.jsp");

			dispatcher.forward(req, resp);
			
		
		
	}

}

