package com.project.tour.city.food;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.tour.dto.FoodReviewDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/city/food/view/reviewadd.do")
public class FoodReviewAdd extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String path = req.getRealPath("/userimage/food");
		int size = 1024 * 1024 * 100; // 100MB
		
		MultipartRequest multi = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy());
		
		String star = multi.getParameter("star");
		String content = multi.getParameter("content");
		String image = multi.getFilesystemName("image");
		String fseq = multi.getParameter("fseq");
		
		UserDTO udto = (UserDTO) session.getAttribute("auth");
		
		FoodReviewDTO dto = new FoodReviewDTO();
		
		dto.setContent(content);
		dto.setId(udto.getId());
		dto.setStar(star);
		dto.setImage(image);
		dto.setFseq(fseq);
		
		FoodDAO dao = new FoodDAO();
		int result = dao.addReview(dto);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		writer.printf("{\"result\" : \"%s\"}", result);
		writer.close();
	}

}
