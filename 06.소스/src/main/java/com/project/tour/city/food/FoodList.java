package com.project.tour.city.food;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.city.CityDAO;
import com.project.tour.dto.CityDTO;
import com.project.tour.dto.FoodDTO;

@WebServlet("/city/food.do")
public class FoodList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String cseq = req.getParameter("cseq");
		String distinct = req.getParameter("distinct");
		String keyword = req.getParameter("keyword");
		
		int page = 1;
		
		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		CityDAO cdao = new CityDAO();
		CityDTO dto = cdao.findCity(cseq);
		
		FoodDAO dao = new FoodDAO();
		
		Pagination pagination = null;
		ArrayList<FoodDTO> list = null;
		
		if (distinct == null || distinct.equals("") || keyword == null || keyword.equals("")) { // 검색어가 없을 경우 > 전체 리스트 출력
			
			 list = dao.listFood(cseq, page);
			 pagination = new Pagination(page, dao.getTotalCount(cseq), 10, 10);
			
		} else { // 검색어가 있을 경우 > 검색 결과 리스트 출력
			
			if (distinct.equals("name")) { // 음식점명으로 검색하는 경우
				
				list = dao.searchByName(cseq, keyword, page);
				pagination = new Pagination(page, dao.getSearchByNameCount(cseq, keyword), 10, 10);
				req.setAttribute("distinct", "name");
				req.setAttribute("keyword", keyword);
				
			} else { // 카테고리로 검색하는 경우
				list = dao.searchByCategory(cseq, keyword, page);
				pagination = new Pagination(page, dao.getSearchByCategoryCount(cseq, keyword), 10, 10);
				req.setAttribute("distinct", "category");
				req.setAttribute("keyword", keyword);
			}
		}
		
		req.setAttribute("pagination", pagination);
		req.setAttribute("list", list);
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/food/foodlist.jsp");
		dispatcher.forward(req, resp);

	}

}
