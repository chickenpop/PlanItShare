package com.project.tour.city.tour;

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
import com.project.tour.dto.TourDTO;

@WebServlet("/city/tourlist.do")
public class TourList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 페이징
		int currentPage = 0;
		int begin = 0;
		int end = 0;
		int pageCnt = 10;
		
		int totalCnt = 0;
		int totalPage = 0;
		
		String page = req.getParameter("page");
		String seq = req.getParameter("cseq");   // 여행지 seq
		String cityname = req.getParameter("name");
		
		// 페이징
		if (page == null || page == "") currentPage = 1;
		else currentPage = Integer.parseInt(page);
		
		begin = ((currentPage - 1) * pageCnt) + 1;
		end = begin + pageCnt - 1;
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		TourDAO dao = new TourDAO();
		
		totalCnt = dao.getTourlistCnt();
		totalPage = (int)Math.ceil((double)totalCnt / pageCnt);
		
        String pagebar = "";
		
		int pageSize = 10;	// 보이는 페이지 개수
		int n = 0;			// 페이지 번호
		int loop = 0;		
		
		pagebar = "";
		
		loop = 1;
		n = ((currentPage - 1) / pageSize) * pageSize + 1;
		
		pagebar += "<ul class=\"pagination\">";
		
		if (n == 1) {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#!\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li> "
					);
		} else {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"/planitshare/city/tourlist.do?cseq=%s&page=%d\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li> "
					, seq , n - 1
					);
		}
		
		while (!(loop > pageSize || n > totalPage)) {
			
			if (n == currentPage) {
				pagebar += String.format(" <li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li> "
						, n);
			} else {
				pagebar += String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/planitshare/city/tourlist.do?cseq=%s&page=%d\">%d</a></li> "
						, seq
						, n
						, n);
			}
			
			loop++;
			n++;
		}
		
		if (n > totalPage) {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#!\" aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li> "
					);
		} else {
			pagebar += String.format(" <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"/planitshare/city/tourlist.do?cseq=%s&page=%d\" aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n"
					+ "		      </a>\r\n"
					+ "		    </li> "
					, seq
					, n
					);
		}
		
		
		pagebar += "</ul>";
		
		
		// 관광명소 
		map.put("seq", seq + "");
		
		ArrayList<TourDTO> list = dao.getTourlist(map);
		
		CityDAO cdao = new CityDAO();
		CityDTO dto = cdao.findCity(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("list", list);
		req.setAttribute("cseq", seq);
		req.setAttribute("pagebar", pagebar);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/tour/tourlist.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}

