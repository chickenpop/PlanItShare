package com.project.tour.plan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.InvitationDTO;

@WebServlet("/plan/view/invite.do")
public class InviteUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pseq = req.getParameter("pseq");
		String host = req.getParameter("host");
		String id = req.getParameter("id");
		
		PlanDAO dao = new PlanDAO();
		
		InvitationDTO dto = new InvitationDTO();
		
		dto.setPseq(pseq);
		dto.setHost(host);
		dto.setGuest(id);
		
		int result = dao.makeInvite(dto);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		writer.printf("{\"result\" : \"%d\"}", result);
		writer.close();
		
	}

}
