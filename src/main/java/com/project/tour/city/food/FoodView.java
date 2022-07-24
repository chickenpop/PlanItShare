package com.project.tour.city.food;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.FoodDTO;
import com.project.tour.dto.FoodReviewDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/city/food/view.do")
public class FoodView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String seq = req.getParameter("seq");
		
		FoodDAO dao = new FoodDAO();
		
		// 회원으로 로그인 시 관심 등록 여부 체크
		if (session.getAttribute("auth") instanceof UserDTO) {

			UserDTO udto = (UserDTO) session.getAttribute("auth");
			
			// 로그인 후 해당 음식점이 관심 등록 목록에 있으면 결과 넘김 
			if (udto != null) { 
				int likeResult = dao.findLike(seq, udto.getId());
				
				if (likeResult == 1) {
					req.setAttribute("like", "y");
				}
			}
			
		}

		FoodDTO dto = dao.findFood(seq);
		ArrayList<FoodReviewDTO> rlist = dao.findReviews(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("rlist", rlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/food/foodview.jsp");
		dispatcher.forward(req, resp);

	}

}
