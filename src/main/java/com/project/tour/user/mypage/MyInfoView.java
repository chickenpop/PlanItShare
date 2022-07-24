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

@WebServlet("/mypage.do")
public class MyInfoView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		
		//아이디를 가져옴
		UserDTO user = (UserDTO)session.getAttribute("auth");
		String id = user.getId();
		
		MyPageDAO dao = new MyPageDAO();
		
		UserDTO dto = dao.getMyinfo(id);
		
		//전화번호에 하이픈('-')을 붙여주기 위함
		dto.setTel(dao.convertTelNo(dto.getTel()));
		
		req.setAttribute("dto", dto);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/myinfo.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}

