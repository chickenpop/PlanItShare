package com.project.tour.city.food;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.UserDTO;

@WebServlet("/city/food/view/like.do")
public class FoodLike extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("seq");
		HttpSession session = req.getSession();
		
		UserDTO dto = (UserDTO) session.getAttribute("auth");
		String id = dto.getId();
		
		FoodDAO dao = new FoodDAO();
		
		int isLiked = dao.findLike(seq, id);
		
		String status = null; 
		
		if (isLiked == 1) { // 이미 좋아요를 누른 상태일 때
			dao.deleteLike(seq, id);
			status = "deleteLike";
		} else { // 좋아요를 누르지 않은 상태일 때
			dao.insertLike(seq, id);
			status = "insertLike";
		}
		
		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        
        writer.printf("{\"status\" : \"%s\"}", status);
        writer.close();
		
	}
}
