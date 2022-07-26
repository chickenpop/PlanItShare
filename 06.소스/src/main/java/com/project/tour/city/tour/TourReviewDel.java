package com.project.tour.city.tour;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.TourDTO;

@WebServlet("/city/tourreviewdel.do")
public class TourReviewDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		TourDAO dao = new TourDAO();
		
		// 리뷰 사진 삭제
		TourDTO dto = dao.getImage(seq);
		
		if(dto.getImage() != null || dto.getImage() != "") {
			
			String path = req.getRealPath("/userimage/tour");
			path += "/" + dto.getImage();
			
			File file = new File(path);
			file.delete();
			
		}
		
		int result = dao.delTourReview(seq);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		writer.printf("{ \"result\": \"%d\" }", result);
		
		writer.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
