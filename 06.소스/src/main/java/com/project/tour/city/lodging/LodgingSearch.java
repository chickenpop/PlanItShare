package com.project.tour.city.lodging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.LodgingDTO;

@WebServlet("/city/lodgingsearch.do")
public class LodgingSearch extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String cseq = req.getParameter("cseq");
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String isSearch = "n";
		
		//검색일 경우만 isSearch가 y
		if ((column == null || word == null)
				|| (column == "" || word == "")) {
			isSearch = "n";
		} else {
			isSearch = "y";
		}

		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("column", column);
		map.put("word", word);
		map.put("isSearch", isSearch);
		
		
		LodgingDAO dao = new LodgingDAO();
		
		
		ArrayList<LodgingDTO> list = dao.searchlist(map); 

		//페이징
		int nowPage = 0; //현재 페이지 번호(=page)
		int begin = 0; //SQL조건 -시작값 ex) 1
		int end = 0; //SQL조건 -끝값 ex) 10
		int pageSize = 10; //한페이지 당 출력할 게시물 수
		
		int totalCount = 0; //총 게시물 수 
		int totalPage = 0; //총 페이지 수
		
		//페이지라는 데이터를 넘길 것이므로 
		String page = req.getParameter("page");
		
		if (page == null || page == "") nowPage = 1; //페이지를 안넘기면 무조건 1페이지
		else nowPage = Integer.parseInt(page); 
		
		begin = ((nowPage -1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		map.put("begin", begin + ""); 
		map.put("end", end + "");
		
		//총 페이지 수 구하기
		totalCount = dao.getTotalCount(map);
		totalPage = (int)Math.ceil((double)totalCount/ pageSize);
		
		String pagebar = "";
		
		int blockSize = 10; //한번에 보여질 페이지 개수
		int n = 0;			//페이지 번호
		int loop = 0;		//루프변수
		
		pagebar = "";
		
		loop = 1;
		n = ((nowPage - 1) / blockSize) * blockSize + 1;
		
		pagebar += "<ul class=\"pagination\">";
		
		if (n == 1) {
			
			pagebar += String.format("<li class=\"page-item \">\r\n"
					+ "		      <a class=\"page-link\" href=\"#!\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li>");
				
			} else {
			
			pagebar += String.format("<<li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"/planitshare/city/lodgingsearch.do?cseq=%d&page=%d\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li> ", cseq, n -1);
			
			}
		
while (!(loop > blockSize || n > totalPage )) {
			
			if (n == nowPage) {
				pagebar += String.format("<li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li> ", n);
				
			} else {
				pagebar += String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"/planitshare/city/lodgingsearch.do?cseq=%d&page=%d\">%d</a></li> ", cseq, n, n);
				
			}
			
			loop++;
			n++;
			
			}
		
			if (n > totalPage) {
				pagebar += String.format("<li class=\"page-item\">\r\n"
						+ "		      <a class=\"page-link\" href=\"#!\" aria-label=\"Next\">\r\n"
						+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n"
						+ "		      </a>\r\n"
						+ "		    </li>");
			
			} else {
				
				pagebar += String.format("<li class=\"page-item\">\r\n"
						+ "		      <a class=\"page-link\" href=\"/planitshare/city/lodgingsearch.do?cseq=%d&page=%d\" aria-label=\"Next\">\r\n"
						+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n"
						+ "		      </a>\r\n"
						+ "		    </li>", cseq, n);
			}
			
			pagebar += "</ul>";
			
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		
		req.setAttribute("nowPage", nowPage);

		req.setAttribute("pagebar", pagebar);
			
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/lodging/lodgingsearch.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
