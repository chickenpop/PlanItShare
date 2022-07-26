package com.project.tour.plan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.FoodCategoryDTO;
import com.project.tour.dto.LodgingCategoryDTO;
import com.project.tour.dto.TourCategoryDTO;

@WebServlet("/plan/add/distinct.do")
public class GetDistinct extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


				// 음식점, 관광명소, 숙소 분류
				req.setCharacterEncoding("UTF-8");
				String distinct = req.getParameter("distinct");
				
				//System.out.println("GetDistinct.java의 distinct: " + distinct);
				
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");
				
				PlanDAO dao = new PlanDAO();
				
				PrintWriter writer = resp.getWriter();
				
				
				if (distinct.equals("food")) { //음식점
					ArrayList<FoodCategoryDTO> list = dao.getFoodCategory();
					
					writer.println("[");
					
					for (int i = 0 ; i < list.size() ; i ++) {
						writer.println("{");
						writer.printf("\"seq\" : \"%s\",", list.get(i).getSeq());
						writer.printf("\"category\" : \"%s\"", list.get(i).getCategory());
						writer.println("}");
						
						if (i == list.size() - 1) {
							break;
						}
						
						writer.println(",");
					}
					
					writer.println("]");
					
				} else if (distinct.equals("tour")) { //관광명소
					ArrayList<TourCategoryDTO> list = dao.getTourCategory();
					
					writer.println("[");
					
					for (int i = 0 ; i < list.size() ; i ++) {
						writer.println("{");
						writer.printf("\"seq\" : \"%s\",", list.get(i).getSeq());
						writer.printf("\"category\" : \"%s\"", list.get(i).getCategory());
						writer.println("}");
						
						if (i == list.size() - 1) {
							break;
						}
						
						writer.println(",");
					}
					
					writer.println("]");
					
				} else { //숙소
					ArrayList<LodgingCategoryDTO> list = dao.getLodgingCategory();
					
					writer.println("[");
					
					for (int i = 0 ; i < list.size() ; i ++) {
						writer.println("{");
						writer.printf("\"seq\" : \"%s\",", list.get(i).getSeq());
						writer.printf("\"category\" : \"%s\"", list.get(i).getCategory());
						writer.println("}");
						
						if (i == list.size() - 1) {
							break;
						}
						
						writer.println(",");
					}
					
					writer.println("]");
				}
				
				writer.close();


	
				
		
		
	}


}
