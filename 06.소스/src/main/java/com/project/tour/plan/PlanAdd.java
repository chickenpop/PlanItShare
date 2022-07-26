package com.project.tour.plan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.PlanDTO;
import com.project.tour.dto.UserDTO;


@WebServlet("/plan/add.do")
public class PlanAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//distrinct
		
		
		req.setCharacterEncoding("UTF-8");
		
		
		PlanDAO dao = new PlanDAO();
		ArrayList<String> distrincts = dao.getDistrincts();
		
		
		
		
		req.setAttribute("distrincts", distrincts);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/plan/planadd.jsp");
		dispatcher.forward(req, resp);
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
	      
	    UserDTO udto = (UserDTO) session.getAttribute("auth");
	   
	      
	      
	      Enumeration<String> test =  req.getParameterNames();
	      String startdate = req.getParameter("startdate");
	      String enddate = req.getParameter("enddate");
	      String cseq = req.getParameter("city");
	      String title = req.getParameter("title");
	      String content = req.getParameter("content");
	      String days = req.getParameter("day");
	      String author = udto.getId();
	      
	      
	      startdate = (startdate.substring(6, 10) + "-" + (startdate.substring(0, 2)) + "-" + (startdate.substring(3, 5)));
	      enddate = (enddate.substring(6, 10) + "-" + (enddate.substring(0, 2)) + "-" + (enddate.substring(3, 5)));
	      
	   
	      PlanDAO dao = new PlanDAO();
	      
	      PlanDTO pdto = new PlanDTO();
	      
	      pdto.setCseq(cseq);
	      pdto.setStartdate(startdate);
	      pdto.setEnddate(enddate);
	      pdto.setTitle(title);
	      pdto.setContent(content);
	      pdto.setAuthor(author);
	      
	      int result = dao.addPlan(pdto);
	      
	      int maxPlanSeq = dao.getMaxplanseq();
		
		/*
		
		Day 1_1_aaa:food
		Day 1_1_bbb:황셰프 양식당
		Day 2_2_aaa:tour
		Day 2_2_bbb:신비의 도로
		
		*/
		
		for (int i = 1 ; i <= Integer.parseInt(days) ; i ++) {
			dao.addDaily(i, maxPlanSeq);
		}
		
		
		while (test.hasMoreElements()) {
			//System.out.println(test.nextElement());
			
			String name = test.nextElement();
			String distinct = "";
			String placeSeq = "";
			
			//System.out.println(name);
			
			if (name.startsWith("Day ")) {
				
				String day = name.substring(4, 5); // 일차
				
				if (name.contains("distinct")) { // 장소별 구분
					distinct = req.getParameter(name);
				}

				name = test.nextElement();
				
				if (name.contains("place")) { // 장소의 시퀀스값 가져오기
					placeSeq = req.getParameter(name);
				}
				
				if (distinct.equals("food")) {
					dao.addDailyFood(maxPlanSeq, day, placeSeq); //pseq, day, placeSeq
					
				} else if (distinct.equals("tour")) {
					dao.addDailyTour(maxPlanSeq, day, placeSeq);
					
				} else {
					dao.addDailyLodging(maxPlanSeq, day, placeSeq);
				}

			}
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;");
		
		PrintWriter writer = resp.getWriter();
		
		if (result == 1) {
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('일정 등록이 완료되었습니다.');");
			writer.printf("location.href='/planitshare/plan/view.do?seq=%d'", maxPlanSeq);
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
		} else {
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('일정 등록 실패');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
		}
		writer.close();

	}

}























