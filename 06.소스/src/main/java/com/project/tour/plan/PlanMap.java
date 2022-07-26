package com.project.tour.plan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.PlaceDTO;

@WebServlet("/plan/planmap.do")
public class PlanMap extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		PlanDAO dao = new PlanDAO();
				
		ArrayList<PlaceDTO> planList = new ArrayList<>();
		
		dao.addFoodPlace(seq, planList);
		dao.addLodgingPlace(seq, planList);
		dao.addTourPlace(seq, planList);
		
		Collections.sort(planList, new Comparator<PlaceDTO>() {
			
			@Override
			public int compare(PlaceDTO o1, PlaceDTO o2) {
				return (o1.getDay() + o1.getRegdate()).compareTo(o2.getDay() + o2.getRegdate());
			};
		});
		
		String lastDay = planList.get(planList.size()-1).getDay();

		req.setAttribute("planList", planList);
		req.setAttribute("lastDay", lastDay);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/plan/planmap.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}

