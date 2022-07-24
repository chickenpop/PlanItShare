package com.project.tour.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.CityDTO;

@WebServlet("/adminpage/place/add/distrinct.do")
public class SelectDistrinct extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 경기도, 강원도, ..
		req.setCharacterEncoding("UTF-8");
		String distrinct = req.getParameter("distrinct");
		
		AdminDAO dao = new AdminDAO();
		
		ArrayList<CityDTO> cities = dao.getCities(distrinct);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		writer.println("[");
		
		for (int i = 0 ; i < cities.size() ; i ++) {
			writer.println("{");
			writer.printf("\"seq\" : \"%s\",", cities.get(i).getSeq());
			writer.printf("\"name\" : \"%s\"", cities.get(i).getName());
			writer.println("}");
			
			if (i == cities.size() - 1) {
				break;
			}
			
			writer.println(",");
		}
		
		writer.println("]");
		writer.close();
		
	}

}

