package com.project.tour.admin;

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
import com.project.tour.dto.BannedWordDTO;
import com.project.tour.dto.CityDTO;
import com.project.tour.dto.DistrinctDTO;
import com.project.tour.dto.PlanDTO;

@WebServlet("/admin/bannedwordlist.do")
public class BannedWordList extends HttpServlet {

	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String[] bannedword = req.getParameterValues("chk");
		String[] bannedplan = req.getParameterValues("chk1");
		
		
		AdminDAO dao = new AdminDAO();
		
		
		int res = dao.multiDelete(bannedword);
		int res1 = dao.multiDeleteplan(bannedplan);
	
		
		
		ArrayList<String> blist = dao.getWord();
		
		ArrayList<PlanDTO> plist = dao.getBannedplan(blist);
		
		HashMap<String,String> map = new HashMap<String,String>();
		
				int nowPage = 0; 	
				int begin = 0;
				int end = 0;
				int pageSize = 5;	
				
				int totalCount = 0; 
				int totalPage = 0;	
				

				String page = req.getParameter("page");
				
				if (page == null || page == "") nowPage = 1;
				else nowPage = Integer.parseInt(page);
				
				begin = ((nowPage - 1) * pageSize) + 1;
				end = begin + pageSize - 1;
				
				
				map.put("begin", begin + "");
				map.put("end", end + "");
				
				ArrayList<BannedWordDTO> list = dao.getList(map);
		

				totalCount = dao.getTotalCount(map);
				totalPage = (int)Math.ceil((double)totalCount / pageSize);
				
				
				String pagebar = "";
				
				int blockSize = 5;	//한번에 보여질 페이지 개수
				int n = 0;			//페이지 번호
				int loop = 0;		//루프
				
				pagebar = "";
				
				
				loop = 1;
				n = ((nowPage - 1) / blockSize) * blockSize + 1;
				
				
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
							+ "		      <a class=\"page-link\" href=\"/planitshare/admin/bannedwordlist.do?page=%d\" aria-label=\"Previous\">\r\n"
							+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n"
							+ "		      </a>\r\n"
							+ "		    </li> "
							, n - 1
							);
				}
				
				
				while (!(loop > blockSize || n > totalPage)) {
					
					if (n == nowPage) {
						pagebar += String.format(" <li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li> "
								, n);
					} else {
						pagebar += String.format(" <li class=\"page-item\"><a class=\"page-link\" href=\"/planitshare/admin/bannedwordlist.do?page=%d\">%d</a></li> "
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
							+ "		      <a class=\"page-link\" href=\"/planitshare/admin/bannedwordlist.do?page=%d\" aria-label=\"Next\">\r\n"
							+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n"
							+ "		      </a>\r\n"
							+ "		    </li> "
							, n
							);
				}
				
				
				pagebar += "</ul>";
				
				
		
		req.setAttribute("list", list);
		req.setAttribute("plist", plist);
		
		req.setAttribute("res", res);
		req.setAttribute("res1", res1);
		
		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		
		req.setAttribute("nowPage", nowPage);
		
		req.setAttribute("pagebar", pagebar);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/bannedwordlist.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}

