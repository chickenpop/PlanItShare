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

@WebServlet("/adminpage/place/add.do")
public class PlaceAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		AdminDAO dao = new AdminDAO();
		ArrayList<String> distrincts = dao.getDistrincts();
		
		req.setAttribute("distrincts", distrincts);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/placeadd.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int size = 1024 * 1024 * 100; // 100MB
		String path = req.getRealPath("/asset/image");

		MultipartRequest multi = new MultipartRequest(req, path, size, "utf-8", new DefaultFileRenamePolicy());
		
		String distinct = multi.getParameter("distinct");
		String category = multi.getParameter("category");
		String city = multi.getParameter("city");
		String name = multi.getParameter("name");
		String address = multi.getParameter("address");
		String image = multi.getFilesystemName("image");
		
		AdminDAO dao = new AdminDAO();
		
		int result = 0;
		
		if (distinct.equals("lodging")) { // 숙소
			
			String checkIn = multi.getParameter("checkin");
			String checkOut = multi.getParameter("checkout");
			
			LodgingDTO dto = new LodgingDTO();
			
			dto.setName(name);
			dto.setLcseq(category);
			dto.setImage(image);
			dto.setCseq(city);
			dto.setCheckin(checkIn);
			dto.setCheckout(checkOut);
			dto.setAddress(address);
			
			result = dao.addLodging(dto);
			
			
		} else if (distinct.equals("food")){ // 음식점
			
			String open = multi.getParameter("open");
			String close = multi.getParameter("close");
			
			FoodDTO dto = new FoodDTO();
			
			dto.setName(name);
			dto.setFcseq(category);
			dto.setImage(image);
			dto.setCseq(city);
			dto.setOpen(open);
			dto.setClose(close);
			dto.setAddress(address);
			
			result = dao.addFood(dto);
			
		} else { // 관광명소
			
			String open = multi.getParameter("open");
			String close = multi.getParameter("close");
			
			TourDTO dto = new TourDTO();
			
			dto.setPlaceName(name);
			dto.setTcseq(category);
			dto.setImage(image);
			dto.setCseq(city);
			dto.setOpen(open);
			dto.setClose(close);
			dto.setAddress(address);
			
			result = dao.addTour(dto);
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter writer = resp.getWriter();
		
		if (result == 1) {
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('등록이 완료되었습니다.');");
			writer.println("location.href='/planitshare/adminpage/place.do'");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
			
			writer.close();
		}
		
	}

}
