package com.project.tour.user.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.dto.MyPageDTO;

@WebServlet("/mypage/mylikeplace.do")
public class MyLikePlace extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HashMap<String, String> map = new HashMap<String, String>();

		// 음식점 관심목록
		// 1. DB작업
		// 2. 결과
		// 3. JSP 호출 및 결과 전달

		// 1. + 2.
		MyPageDAO dao = new MyPageDAO();

		String distint = "food";

		if (req.getParameter("distint") != null) {
			distint = req.getParameter("distint");
		}

		// 음식점일 경우
		if (distint.equals("food")) {
			req.setAttribute("distint", "food");

			// 페이징
			int currentPage = 0;
			int begin = 0;
			int end = 0;
			int pageCnt = 10;

			int totalCnt = 0;
			int totalPage = 0;

			String page = req.getParameter("page");

			if (page == null || page == "")
				currentPage = 1;
			else
				currentPage = Integer.parseInt(page);

			begin = ((currentPage - 1) * pageCnt) + 1;
			end = begin + pageCnt - 1;

			map.put("begin", begin + "");
			map.put("end", end + "");

			// 음식점
			ArrayList<MyPageDTO> fList = dao.fList(map);
			req.setAttribute("fList", fList);

			// 음식점 총 갯수
			totalCnt = dao.getFoodCnt();
			totalPage = (int) Math.ceil((double) totalCnt / pageCnt);

			String pagebar = "";

			int pageSize = 10; // 보이는 페이지 개수
			int n = 0; // 페이지 번호
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
						+ "		      <a class=\"page-link\" href=\"/planitshare/mypage/mylikeplace.do?distint=food&page=%d\" aria-label=\"Previous\">\r\n"
						+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + "		      </a>\r\n"
						+ "		    </li> ", n - 1);
			}

			while (!(loop > pageSize || n > totalPage)) {

				if (n == currentPage) {
					pagebar += String.format(
							" <li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li> ", n);
				} else {
					pagebar += String.format(
							" <li class=\"page-item\"><a class=\"page-link\" href=\"/planitshare/mypage/mylikeplace.do?distint=food&page=%d\">%d</a></li> ",
							n, n);
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
						+ "		      <a class=\"page-link\" href=\"/planitshare/mypage/mylikeplace.do?distint=food&=%d\" aria-label=\"Next\">\r\n"
						+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n" + "		      </a>\r\n"
						+ "		    </li> ", n);
			}

			pagebar += "</ul>";

			req.setAttribute("list", fList);

			req.setAttribute("pagebar", pagebar);

			System.out.println(444);

//----------------------------------------------------------------------------------------------------------------------------------------

			// 관광명소일 경우
		} else if (distint.equals("tour")) {
			req.setAttribute("distint", "tour");


			// 페이징
			int currentPage = 0;
			int begin = 0;
			int end = 0;
			int pageCnt = 10;

			int totalCnt = 0;
			int totalPage = 0;

			String page = req.getParameter("page");

			if (page == null || page == "")
				currentPage = 1;
			else
				currentPage = Integer.parseInt(page);

			begin = ((currentPage - 1) * pageCnt) + 1;
			end = begin + pageCnt - 1;

			map.put("begin", begin + "");
			map.put("end", end + "");
			

			// 관광지
			ArrayList<MyPageDTO> tList = dao.tList(map);
			req.setAttribute("tList", tList);
			

			// 관광지 총 갯수
			totalCnt = dao.getTourCnt();
			totalPage = (int) Math.ceil((double) totalCnt / pageCnt);

			String pagebar = "";

			int pageSize = 10; // 보이는 페이지 개수
			int n = 0; // 페이지 번호
			int loop = 0;

			req.setAttribute("list", tList);
			req.setAttribute("pagebar", pagebar);

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
						+ "		      <a class=\"page-link\" href=\"/planitshare/mypage/mylikeplace.do?distint=tour&page=%d\" aria-label=\"Previous\">\r\n"
						+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + "		      </a>\r\n"
						+ "		    </li> ", n - 1);
			}

			while (!(loop > pageSize || n > totalPage)) {

				if (n == currentPage) {
					pagebar += String.format(
							" <li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li> ", n);
				} else {
					pagebar += String.format(
							" <li class=\"page-item\"><a class=\"page-link\" href=\"/planitshare/mypage/mylikeplace.do?distint=tour&page=%d\">%d</a></li> ",
							n, n);
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
						+ "		      <a class=\"page-link\" href=\"/planitshare/mypage/mylikeplace.do?distint=tour&=%d\" aria-label=\"Next\">\r\n"
						+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n" + "		      </a>\r\n"
						+ "		    </li> ", n);
			}

			pagebar += "</ul>";

			req.setAttribute("list", tList);

			req.setAttribute("pagebar", pagebar);

// ----------------------------------------------------------------------------------------------------------------------------------------

			// 숙소일 경우
		} else { 
			req.setAttribute("distint", "lodging");

			// 페이징
			int currentPage = 0;
			int begin = 0;
			int end = 0;
			int pageCnt = 10;

			int totalCnt = 0;
			int totalPage = 0;

			String page = req.getParameter("page");

			if (page == null || page == "")
				currentPage = 1;
			else
				currentPage = Integer.parseInt(page);

			begin = ((currentPage - 1) * pageCnt) + 1;
			end = begin + pageCnt - 1;

			map.put("begin", begin + "");
			map.put("end", end + "");
			

			// 숙소
			ArrayList<MyPageDTO> lList = dao.lList(map);
			req.setAttribute("lList", lList);

			// 숙소 총 갯수
			totalCnt = dao.getLodgingCnt();
			totalPage = (int) Math.ceil((double) totalCnt / pageCnt);

			String pagebar = "";

			int pageSize = 10; // 보이는 페이지 개수
			int n = 0; // 페이지 번호
			int loop = 0;

			req.setAttribute("list", lList);
			req.setAttribute("pagebar", pagebar);

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
						+ "		      <a class=\"page-link\" href=\"/planitshare/mypage/mylikeplace.do?distint=lodging&page=%d\" aria-label=\"Previous\">\r\n"
						+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + "		      </a>\r\n"
						+ "		    </li> ", n - 1);
			}

			while (!(loop > pageSize || n > totalPage)) {

				if (n == currentPage) {
					pagebar += String.format(
							" <li class=\"page-item active\"><a class=\"page-link\" href=\"#!\">%d</a></li> ", n);
				} else {
					pagebar += String.format(
							" <li class=\"page-item\"><a class=\"page-link\" href=\"/planitshare/mypage/mylikeplace.do?distint=lodging&page=%d\">%d</a></li> ",
							n, n);
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
						+ "		      <a class=\"page-link\" href=\"/planitshare/mypage/mylikeplace.do?distint=lodging&=%d\" aria-label=\"Next\">\r\n"
						+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n" + "		      </a>\r\n"
						+ "		    </li> ", n);
			}

			pagebar += "</ul>";

			req.setAttribute("list",lList);

			req.setAttribute("pagebar", pagebar);
			
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/mylikeplace.jsp");

		dispatcher.forward(req, resp);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
