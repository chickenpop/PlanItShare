package com.project.tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.PlanDTO;
import com.project.tour.dto.RecommendPlaceDTO;

@WebServlet("/main.do")
public class Main extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MainDAO dao = new MainDAO();

		// 추천여행지 목록
		ArrayList<RecommendPlaceDTO> rlist = dao.getRecommendList();

		// 인기 여행 일정 목록
		ArrayList<PlanDTO> plist = dao.getPlanList();

		
		req.setAttribute("rlist", rlist);

		req.setAttribute("plist", plist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String word = req.getParameter("word");
		String column = req.getParameter("column");

		MainDAO dao = new MainDAO();

		// 컬럼이 name일경우는 여행지역에서 검색
		if (column.equals("name")) {

			String cseq = dao.findCity(word);

			// 검색이 성공 했을 경우 지역으로 이동
			if (cseq != null) {

				resp.sendRedirect("/planitshare/city.do?cseq=" + cseq);

				// 검색 실패
			} else {

				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");

				PrintWriter writer = resp.getWriter();

				writer.printf("{\"result\": %d }", 1);

				writer.close();

			}
		}

		// 컬럼이 title일경우는 일정에서 검색
		if (column.equals("title")) {

			String seq = dao.findPlan(word);
			
			if (seq != null) {

				//// 일정 상세페이지 작성되면 확인하고 수정 체크 !!
				resp.sendRedirect("/planitshare/planview.do?seq=" + seq);
				
			//검색실패
			} else {

				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");

				PrintWriter writer = resp.getWriter();

				writer.printf("{\"result\": %d }", 1);

				writer.close();

			}
		}

		
		
		
		
		
		
		
		
		
		
		
	}

}
