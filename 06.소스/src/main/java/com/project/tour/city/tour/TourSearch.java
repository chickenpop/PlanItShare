package com.project.tour.city.tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.TourDTO;

@WebServlet("/city/tourserach.do")
public class TourSearch extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String cseq = req.getParameter("cseq");
		String keyWordType = req.getParameter("keywordtype");
		String keyWord = req.getParameter("keyword");

		
		
		TourDAO dao = new TourDAO();
		TourDTO dto = new TourDTO();
		
		dto.setCseq(cseq);
		dto.setKeyword(keyWord);
		
		ArrayList<TourDTO> list = new ArrayList<TourDTO>();
		
		if(("placename").equals(keyWordType)) {
		
			list = dao.serachPlaceNameList(dto);
			
		} else if(("category").equals(keyWordType)) {
			
			list = dao.serachCategoryList(dto);
		
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		String temp = "";
		
		temp += "[";
		
		for (TourDTO tdto : list) {
			temp += "{";
			temp += String.format("\"seq\":         %s,"  , tdto.getSeq());
			temp += String.format("\"placeName\": \"%s\",", tdto.getPlaceName());
			temp += String.format("\"address\":   \"%s\",", tdto.getAddress());
			temp += String.format("\"open\":      \"%s\",", tdto.getOpen());
			temp += String.format("\"close\":     \"%s\",", tdto.getClose());
			temp += String.format("\"image\":     \"%s\",", tdto.getImage());
			temp += String.format("\"category\":  \"%s\",", tdto.getCategory());
			temp += String.format("\"likeCnt\":   \"%s\",", tdto.getLikeCnt());
			temp += String.format("\"reviewCnt\": \"%s\",", tdto.getReviewCnt());
			temp += String.format("\"reviewAvg\": \"%s\"," , tdto.getReviewAvg() == null ? "0" : tdto.getReviewAvg());			
			temp += String.format("\"cseq\":        %s" , tdto.getCseq());			
			temp += "},";
		}
		
		if (list.size() > 0) {
			temp = temp.substring(0, temp.length() - 1);
		}
		
		temp += "]";
		
		writer.print(temp);
		
		writer.close();
		
		
	}

}




















