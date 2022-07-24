package com.project.tour.plan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/plan/view/delete.do")
public class PlanDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		PlanDAO dao = new PlanDAO();
		
		int result = 0;
		
		// 좋아요 삭제
		result = dao.delLike(seq);

		// 하루 일정의 각 장소 삭제
		result = dao.delDailyFood(seq);
		result = dao.delDailyLodging(seq);
		result = dao.delDailyTour(seq);
		
		// 하루 일정 삭제
		result = dao.delDaily(seq);
		
		// 댓글 삭제
		result = dao.delComments(seq);
		
		// 일정-유저 삭제
		result = dao.delPlanUser(seq);
		
		// 초대 삭제
		result = dao.delInvitation(seq);
		
		// 일정 삭제
		result = dao.delPlan(seq);

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter writer = resp.getWriter();
		
		if (result == 1) {
			
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('삭제 성공');");
			writer.println("location.href='/planitshare/plan.do'");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
			
		} else {
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('삭제 실패');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
		}
		
	}

}
