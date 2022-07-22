package com.project.tour.city.tour;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.tour.dto.TourReviewDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/city/tourreviewadd.do")
public class TourReviewAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
				
		req.setCharacterEncoding("UTF-8");
		
		// 리뷰 이미지 업로드
		String path = req.getRealPath("/userimage/tour");
		int size = 1024 * 1024 * 100;
		
		MultipartRequest multi = null;
		
		try {
			
			multi = new MultipartRequest(
					req,
					path,
					size,
					"UTF-8",
					new DefaultFileRenamePolicy()
				);
			
		} catch (Exception e) {
			System.out.println("TourReviewAdd.doGet");
			e.printStackTrace();
		}
		
		UserDTO adto = (UserDTO)session.getAttribute("auth");
		String id = adto.getId();
		
		int result = 0;
		
		if(id == null || id == "") {
			result = 0;
		} else {
		
			// 리뷰 이미지 이름
			String filename = multi.getFilesystemName("attach");
			
			String content = multi.getParameter("content");
			String star = multi.getParameter("star");
			
			TourReviewDTO dto = new TourReviewDTO();
			
			dto.setContent(content);
			dto.setId(id);
			dto.setImage(filename);
			dto.setStar(star);
			dto.setSeq(multi.getParameter("seq"));
			
			TourDAO dao = new TourDAO();
			
			result = dao.addTourReview(dto);
		}
		
		// 여행지 정보 
		String seq = multi.getParameter("seq");
		String cseq = multi.getParameter("cseq");
		
		if (result == 1) {
			resp.sendRedirect(String.format("/planitshare/city/tourview.do?seq=%s&cseq=%s", seq, cseq));
		} else {
			resp.sendRedirect(String.format("/planitshare/city/tourview.do?seq=%s&cseq=%s", seq, cseq));
		}
		
	}
	
	
	
	
	
	
	
	
	
	

}
