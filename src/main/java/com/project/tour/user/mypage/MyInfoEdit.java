package com.project.tour.user.mypage;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.tour.dto.UserDTO;

@WebServlet("/mypage/myinfoedit.do")
public class MyInfoEdit extends HttpServlet {
		
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		//아이디를 가져옴
		UserDTO user = (UserDTO)session.getAttribute("auth");
		String id = user.getId();
		
		MyPageDAO dao = new MyPageDAO();
		
		UserDTO dto = dao.getMyinfo(id);
		
		
		req.setAttribute("dto", dto);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/myinfoedit.jsp");

		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		UserDTO user = (UserDTO)session.getAttribute("auth");
		
		String id = user.getId();
		
		int size = 1024 * 1024 * 100;
	    String path = req.getRealPath("/userimage");
	    

	    MultipartRequest multi = new MultipartRequest(
	    											req,
	    											path,
	    											size,
	    											"UTF-8",
	    											new DefaultFileRenamePolicy());
		
		
		String name = multi.getParameter("name");
		String tel = multi.getParameter("tel");
		String pw = multi.getParameter("pw");
		
		//새로운 파일 선택
		String profile = multi.getFilesystemName("profile");
		
		
		MyPageDAO dao = new MyPageDAO();
		
		//기존에 등록되어있는 파일을 알기위한 dto
		UserDTO updto = dao.getMyinfo(id);
		
		//새롭게 업로드 하기위한 dto
		UserDTO udto = new UserDTO();
		
		//기존의 첨부파일이 있고 새로운 파일이 추가되었을 경우 
		if (updto.getProfile() != null && profile != null) { 
			
			File file = new File(path + "\\" + updto.getProfile());
			
			//파일이 기본이미지가 아닐경우만 삭제 
			if (!updto.getProfile().equals("user.png")) {
				file.delete();
			}
			
			udto.setProfile(profile);
			
			user.setProfile(profile);
			session.setAttribute("auth", user); 
			
		
		} else if (profile == null && multi.getParameter("delfile").equals("y")) {
			//기존파일만 삭제하고 새로운 파일을 추가 안했을 경우
			File file = new File(path + "\\" + updto.getProfile());
			
			file.delete();
			
			udto.setProfile("user.png");
			user.setProfile("user.png");
			session.setAttribute("auth", user); 
			

		} else if (profile == null) {
			//기존파일의 유무와 상관없이 새로운 파일을 추가 안했을 경우
			udto.setProfile(updto.getProfile());

			user.setProfile(profile);
			session.setAttribute("auth", user); 
			
		} else if (updto.getProfile() == null && profile != null) {
			//기존 파일이 없는데 새로운 파일을 추가하는 경우
			udto.setProfile(profile);
			
			user.setProfile(profile);
			session.setAttribute("auth", user); 

		}
	
		
		
		udto.setId(id);
		udto.setName(name);
		udto.setTel(tel);
		udto.setPw(pw);

		int result = dao.updateMyinfo(udto);
		
		req.setAttribute("result", result);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/myinfoedit.jsp");

		dispatcher.forward(req, resp);
		
		
	}

}

