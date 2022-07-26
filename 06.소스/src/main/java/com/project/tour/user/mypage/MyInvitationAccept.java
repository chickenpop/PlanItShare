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

@WebServlet("/mypage/myinvitationaccept.do")
public class MyInvitationAccept extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/name.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		UserDTO userDTO = (UserDTO)session.getAttribute("auth");
		String id = userDTO.getId();

		String pseq = req.getParameter("pseq");
		
		String seq = req.getParameter("seq");
		
		InvitationDTO dto = new InvitationDTO();
		
		dto.setGuest(id);
		dto.setPseq(pseq);
		
		MyPageDAO dao = new MyPageDAO();
		
		int result = dao.AcceptInvitation(dto);
		
		
		//초대를 수락하면 초대 테이블에서는 삭제되어야하므로 삭제되는 메소드사용 
		result = dao.refuseInvitation(seq);
		
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
