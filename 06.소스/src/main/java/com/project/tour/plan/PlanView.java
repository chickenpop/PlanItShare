package com.project.tour.plan;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.CommentDTO;
import com.project.tour.dto.DayDTO;
import com.project.tour.dto.PlaceDTO;
import com.project.tour.dto.PlanDTO;
import com.project.tour.dto.PlanUserDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/plan/view.do")
public class PlanView extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		PlanDAO dao = new PlanDAO();
		
		// 조회수 증가
		dao.addReadCount(seq);
		PlanDTO pdto = dao.getPlan(seq);
		ArrayList<CommentDTO> clist = dao.listComment(seq);
		
		HttpSession session = req.getSession();
		
		// 로그인 한 상태일 때
		if (session.getAttribute("auth") instanceof UserDTO) {
			
			UserDTO udto = (UserDTO) session.getAttribute("auth");
			
			int result = dao.findLike(seq, udto.getId());
				
			if (result == 1) {
				req.setAttribute("like", "y");
			}
			
			// 접속 계정과 작성자 아이디가 일치하면 초대 가능한 아이디 목록 가져옴
			if (udto.getId().equals(pdto.getAuthor())) {
				ArrayList<String> idList = dao.getIdList(seq, udto.getId());
				req.setAttribute("idList", idList);
			}
		}
		
		// 일정의 각 일차 별 장소 리스트
		ArrayList<PlaceDTO> planList = new ArrayList<>();
		
		dao.addFoodPlace(seq, planList);
		dao.addLodgingPlace(seq, planList);
		dao.addTourPlace(seq, planList);
		
		// 일차 > 등록일자 별로 정렬
		Collections.sort(planList, new Comparator<PlaceDTO>() {
			
			@Override
			public int compare(PlaceDTO o1, PlaceDTO o2) {
				return (o1.getDay() + o1.getRegdate()).compareTo(o2.getDay() + o2.getRegdate());
			};
		});
		
		ArrayList<DayDTO> days = getDays(pdto);
		
		// 해당 일정의 편집 권한이 있는 사람 목록 가져오기
		ArrayList<PlanUserDTO> puList = dao.getPlanUser(seq);
		
		// date 파싱
		pdto.setRegdate(pdto.getRegdate().substring(0, 10));
		pdto.setStartdate(pdto.getStartdate().substring(0, 10));
		pdto.setEnddate(pdto.getEnddate().substring(0, 10));
		
		req.setAttribute("days", days);
		req.setAttribute("clist", clist);
		req.setAttribute("pdto", pdto);
		req.setAttribute("planList", planList);
		req.setAttribute("puList", puList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/plan/planview.jsp");
		dispatcher.forward(req, resp);

	}

	private ArrayList<DayDTO> getDays(PlanDTO pdto) {
		ArrayList<DayDTO> days = new ArrayList<>();
		
		LocalDate startDate = LocalDate.parse(pdto.getStartdate().substring(0, 10), DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(pdto.getEnddate().substring(0, 10), DateTimeFormatter.ISO_DATE);
		
		Period period = Period.between(startDate, endDate);
		LocalDate date = startDate;
		
		for (int i = 1 ; i <= period.getDays() + 1 ; i ++) {
			
			DayDTO dto = new DayDTO();

			dto.setDay(String.valueOf(i));
			dto.setDate(date.toString());
			
			date = date.plusDays(1);
			
			days.add(dto);
		}
		
		return days;
	}

}


