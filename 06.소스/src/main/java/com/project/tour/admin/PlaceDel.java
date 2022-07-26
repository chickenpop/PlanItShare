package com.project.tour.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminpage/place/delete.do")
public class PlaceDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String distinct = req.getParameter("distinct");
		String seq = req.getParameter("seq");
		
		AdminDAO dao = new AdminDAO();
		
		int result = 0;
		
		if (distinct.equals("food")) { // 음식점
			dao.delFoodReviews(seq); // 참조하는 리뷰 삭제
			dao.delLikeFood(seq); // 참조하는 관심 등록 삭제
			result = dao.delFood(seq);
			
		} else if (distinct.equals("tour")) { // 관광명소
			dao.delTourReviews(seq); // 참조하는 리뷰 삭제
			dao.delLikeTour(seq); // 참조하는 관심 등록 삭제
			result = dao.delTour(seq);
			
		} else { // 숙소
			dao.delLodgingReviews(seq); // 참조하는 리뷰 삭제
			dao.delLikeLodging(seq); // 참조하는 관심 등록 삭제
			result = dao.delLodging(seq);
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter writer = resp.getWriter();
		
		if (result == 1) {
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('삭제가 완료되었습니다.');");
			writer.println("location.href='/planitshare/adminpage/place.do'");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
			
			writer.close();
		}
		
	}

}

