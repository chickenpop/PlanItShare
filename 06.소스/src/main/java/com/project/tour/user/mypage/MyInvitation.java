package com.project.tour.user.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.InvitationDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/mypage/myinvitation.do")
public class MyInvitation extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		UserDTO userDTO = (UserDTO)session.getAttribute("auth");
		String id = userDTO.getId();
		
		MyPageDAO dao = new MyPageDAO();
		
		ArrayList<InvitationDTO> list = dao.myinvitation(id);

		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/myinvitation.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}

