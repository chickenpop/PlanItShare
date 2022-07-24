package com.project.tour.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.tour.dto.FoodDTO;
import com.project.tour.dto.LodgingDTO;
import com.project.tour.dto.TourDTO;

@WebServlet("/adminpage/place/update.do")
public class PlaceEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String distinct = req.getParameter("distinct");
		String seq = req.getParameter("seq");
		
		AdminDAO dao = new AdminDAO();
		
		if (distinct.equals("food")) { // 음식점일 때 카테고리 및 DTO 넘겨주기
			
			FoodDTO fdto = dao.getFood(seq);
			
			req.setAttribute("categories", dao.getFoodCategory());
			req.setAttribute("fdto", fdto);
			req.setAttribute("cities", dao.getCities(fdto.getDistrinct()));
			
		} else if (distinct.equals("lodging")) { // 숙소일 때 카테고리 및 DTO 넘겨주기
			
			LodgingDTO ldto = dao.getLodging(seq);
			
			req.setAttribute("categories", dao.getLodgingCategory());
			req.setAttribute("ldto", ldto);
			req.setAttribute("cities", dao.getCities(ldto.getDistrinct()));
			
		} else { // 관광명소일 때 카테고리 및 DTO 넘겨주기
			
			TourDTO tdto = dao.getTour(seq);
			
			req.setAttribute("categories", dao.getTourCategory());
			req.setAttribute("tdto", dao.getTour(seq));
			req.setAttribute("cities", dao.getCities(tdto.getDistrinct()));
		}
		
		ArrayList<String> distrincts = dao.getDistrincts(); // 지역 분류
		
		req.setAttribute("distrincts", distrincts);
		req.setAttribute("distinct", distinct);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/placeedit.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		String distinct = req.getParameter("distinct");
		String category = req.getParameter("category");
		String city = req.getParameter("city");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String seq = req.getParameter("seq");
		
		AdminDAO dao = new AdminDAO();
		
		int result = 0;
		
		if (distinct.equals("lodging")) { // 숙소
			
			String checkIn = req.getParameter("checkin");
			String checkOut = req.getParameter("checkout");
			
			LodgingDTO dto = new LodgingDTO();
			
			dto.setName(name);
			dto.setLcseq(category);
			dto.setCseq(city);
			dto.setCheckin(checkIn);
			dto.setCheckout(checkOut);
			dto.setAddress(address);
			dto.setSeq(seq);
			
			result = dao.updateLodging(dto);
			
			
		} else if (distinct.equals("food")){ // 음식점
			
			String open = req.getParameter("open");
			String close = req.getParameter("close");
			
			FoodDTO dto = new FoodDTO();
			
			dto.setName(name);
			dto.setFcseq(category);
			dto.setCseq(city);
			dto.setOpen(open);
			dto.setClose(close);
			dto.setAddress(address);
			dto.setSeq(seq);
			
			result = dao.updateFood(dto);
			
		} else { // 관광명소
			
			String open = req.getParameter("open");
			String close = req.getParameter("close");
			
			TourDTO dto = new TourDTO();
			
			dto.setPlaceName(name);
			dto.setTcseq(category);
			dto.setCseq(city);
			dto.setOpen(open);
			dto.setClose(close);
			dto.setAddress(address);
			dto.setSeq(seq);
			
			result = dao.updateTour(dto);
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter writer = resp.getWriter();
		
		if (result == 1) {
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('수정이 완료되었습니다.');");
			writer.println("location.href='/planitshare/adminpage/place.do'");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
			
			writer.close();
		}
		
	}

}
