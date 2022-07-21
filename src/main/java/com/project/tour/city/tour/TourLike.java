package com.project.tour.city.tour;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.TourLikeDTO;
import com.project.tour.dto.UserDTO;
import com.project.tour.plan.PlanDAO;

@WebServlet("/city/tourlike.do")
public class TourLike extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("UTF-8");
		
		String seq = req.getParameter("seq");
		
		TourLikeDTO dto = new TourLikeDTO();
		
		//로그인이 되었을때만 좋아요를 누를 수 있음
		if (session.getAttribute("auth") != null) {
			UserDTO userDTO = (UserDTO)session.getAttribute("auth");
			String id = userDTO.getId();
			dto.setId(id);
		} else {
			return;
		}
		
		dto.setTseq(seq);

		TourDAO dao = new TourDAO();
		
		//좋아요 눌렀을때 입력 정보업데이트
		int result = dao.Tourlike(dto);
		
		//해당 숙소의 좋아요 갯수
		int likenum = dao.getTourlike(seq);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		writer.printf("{\"likenum\": %d}", likenum);
		
		writer.close();
		
	}

}
