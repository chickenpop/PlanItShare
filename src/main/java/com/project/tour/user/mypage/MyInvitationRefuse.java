package com.project.tour.user.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.city.lodging.LodgingDAO;
import com.project.tour.dto.InvitationDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/mypage/myinvitationrefuse.do")
public class MyInvitationRefuse extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		UserDTO udto = (UserDTO)session.getAttribute("auth"); 
		String id = udto.getId();
		
		String seq = req.getParameter("seq");
		
		MyPageDAO dao = new MyPageDAO();
		
		int result = dao.refuseInvitation(seq);
		
		ArrayList<InvitationDTO> list = dao.myinvitation(id);

		int count = list.size();
		

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();

		writer.printf("{");
		writer.printf("\"result\": %d,", result);
		writer.printf("\"count\": %d", count);
		writer.printf("}");
		
		writer.close();
		
		
	}

}

