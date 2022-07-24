package com.project.tour.plan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.CommentDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/plan/view/addcomment.do")
public class CommentAdd extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// 댓글 내용, 일정 번호 가져오기
		String comment = req.getParameter("comment");
		String seq = req.getParameter("seq");
		
		// 접속 계정 가져오기
		HttpSession session = req.getSession();
		UserDTO udto = (UserDTO) session.getAttribute("auth");

		PlanDAO dao = new PlanDAO();
		
		CommentDTO cdto = new CommentDTO();
		cdto.setContent(comment);
		cdto.setId(udto.getId());
		cdto.setPseq(seq);
		
		// 댓글 작성
		int result = dao.addComment(cdto);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		writer.printf("{\"result\" : \"%s\"}", result);
		writer.close();
		
	}

}
