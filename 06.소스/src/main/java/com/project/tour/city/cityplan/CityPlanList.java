package com.project.tour.city.cityplan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.city.CityDAO;
import com.project.tour.dto.CityDTO;

@WebServlet("/city/plan.do")
public class CityPlanList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		// 검색
		req.setCharacterEncoding("UTF-8");

		String seq = req.getParameter("cseq");
//		String name = req.getParameter("name");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String isSearch = "n";

		if ((column == null || word == null) || (column == "" || word == "")) {
			isSearch = "n";
		} else {
			isSearch = "y";
		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("column", column);
		map.put("word", word);
		map.put("isSearch", isSearch);

		CityPlanDAO dao = new CityPlanDAO();
		
		//ArrayList<CityDTO> jList = dao.jList(map);
	
		
//------------------------------------------------------------------------------------


		// 페이징
		int currentPage = 0;
		int begin = 0;
		int end = 0;
		int pageCnt = 10;

		int totalPage = 0;
		int totalCnt = 0;

		String page = req.getParameter("page");

		if (page == null || page == "")
			currentPage = 1;
		else
			currentPage = Integer.parseInt(page);

		begin = ((currentPage - 1) * pageCnt) + 1;
		end = begin + pageCnt - 1;

		map.put("begin", begin + "");
		map.put("end", end + "");

		// 제주 일정
		ArrayList<CityDTO> jList = dao.jList(map);
		req.setAttribute("jList", jList);

		// 제주 일정 총 갯수
		totalCnt = dao.getjpCnt();
		totalPage = (int) Math.ceil((double) totalCnt / pageCnt);

		String pagebar = "";

		int pageSize = 10;
		int n = 0;
		int loop = 0;

		pagebar = "";

		loop = 1;
		n = ((currentPage - 1) / pageSize) * pageSize + 1;

		pagebar += "<ul class=\"pagination\">";

		if (n == 1) {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#!\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + "		      </a>\r\n"
					+ "		    </li> ");
		} else {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"/planitshare/city/plan.do?cseq=%s&page=%d\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + "		      </a>\r\n"
					+ "		    </li> ", seq, n - 1);
		}

		while (!(loop > pageSize || n > totalPage)) {

			if (n == currentPage) {
				pagebar += String
						.format(" <li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li> ", n);
			} else {
				pagebar += String.format(
						" <li class=\"page-item\"><a class=\"page-link\" href=\"/planitshare/city/plan.do?cseq=%s&page=%d\">%d</a></li> ",
						 seq, n, n);
			}

			loop++;
			n++;
		}

		if (n > totalPage) {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#!\" aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n" + "		      </a>\r\n"
					+ "		    </li> ");
		} else {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"/planitshare/city/plan.do?cseq=%s&page=%d\" aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n" + "		      </a>\r\n"
					+ "		    </li> ", seq, n);
		}

		pagebar += "</ul>";

		CityDAO cdao = new CityDAO();
		CityDTO dto = cdao.findCity(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("list", jList);
		req.setAttribute("pagebar", pagebar);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/cityplan/cityplanlist.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
		
		
		
		
	}

}
