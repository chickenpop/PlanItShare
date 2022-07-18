package com.project.tour.city.tour;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.AdminDTO;
import com.project.tour.dto.TourDTO;
import com.project.tour.dto.TourReviewDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/city/tourview.do")
public class TourView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 계정 권한
		HttpSession session = req.getSession();
		
		String seq = req.getParameter("seq");   // 관광명소 seq
		String cseq = req.getParameter("cseq"); // 여행지명 seq
		
		TourDAO dao = new TourDAO();
		
		TourDTO dto = dao.getTourOne(seq, cseq);// 관광명소 상세내용
		
		dao = new TourDAO();
		ArrayList<TourReviewDTO> rlist = dao.getTourReviewList(seq);
		
		// 여행지 정보
		TourDTO TourSeq = new TourDTO();
		
		TourSeq.setSeq(seq);
		TourSeq.setCseq(cseq);
		
		req.setAttribute("sdto", TourSeq);
		req.setAttribute("dto", dto);
		req.setAttribute("rlist", rlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/tour/tourview.jsp");

		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}


