package com.project.tour.city.lodging;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.LodgingDTO;
import com.project.tour.dto.LodgingReviewDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/city/lodgingview.do")
public class LodgingView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		
		String seq = req.getParameter("seq");
		
		LodgingDAO dao = new LodgingDAO();
		
		LodgingDTO dto = dao.get(seq);
		
		//댓글 목록 가져오기
		ArrayList<LodgingReviewDTO> list  = dao.review(seq);
		
		//현재 로그인한 아이디 가져오기

		
		req.setAttribute("dto", dto);
		
		req.setAttribute("list", list);
		
	    if (session.getAttribute("auth") instanceof UserDTO) {
	    	
	        UserDTO udto = (UserDTO) session.getAttribute("auth");
	          
	        // 로그인 후 해당 음식점이 관심 등록 목록에 있으면 결과 넘김 
	        if (udto != null) { 
	           int likeResult = dao.findLike(seq, udto.getId());
	             
	           System.out.println(likeResult);
	           
	           if (likeResult == 1) {
	                req.setAttribute("like", "y");
	           }
	        }
	          
	     }
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/lodging/lodgingview.jsp");

		dispatcher.forward(req, resp);
	
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}

