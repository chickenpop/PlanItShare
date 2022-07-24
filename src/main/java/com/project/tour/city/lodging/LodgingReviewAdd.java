package com.project.tour.city.lodging;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicToolBarSeparatorUI;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.tour.dto.LodgingReviewDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/city/lodgingreview.do")
public class LodgingReviewAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/lodging/lodgingreview.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//
		
		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("UTF-8");
		
		String path = req.getRealPath("/userimage/lodgingreview");
		

		//String path = "C:/class/serversy/planitshare/src/main/webapp/lodgingreview";
		int size = 1024 * 1000 * 10;
		
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
			System.out.println("LodgingReviewAdd.doPost");
			e.printStackTrace();

		}

		String content = multi.getParameter("content");
		String lseq = multi.getParameter("lseq");
		String star = multi.getParameter("star");
		String filename = multi.getFilesystemName("image");
		
		
		LodgingReviewDTO dto = new LodgingReviewDTO();
	
		//로그인이 되었을때만 리뷰를 등록할 수 있음 
		if (session.getAttribute("auth") != null) {
			UserDTO userDTO = (UserDTO)session.getAttribute("auth");
			String id = userDTO.getId();
			dto.setId(id);
		}
		
		dto.setContent(content);
		dto.setStar(star);
		dto.setLseq(lseq);
		
		
		if (filename != null) {
			dto.setImage(filename);
		}
		
		//System.out.println("dto : " + dto);
		
		
		LodgingDAO dao = new LodgingDAO();
		
		int result = dao.addComment(dto);
		
    	resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;");

		
		if (result == 1) {
			
			resp.sendRedirect(String.format("/planitshare/city/lodgingview.do?seq=%s", lseq));
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			
			
			writer.println("<html>");
			writer.println("<body>");
			writer.println("<script>");
			writer.println("alert('리뷰등록을 실패했습니다.');");
			writer.println("history.back();");
			writer.println("</script>");
			writer.println("</body>");
			writer.println("</html>");
			
			writer.close();			
		}
		
		
	}

}

