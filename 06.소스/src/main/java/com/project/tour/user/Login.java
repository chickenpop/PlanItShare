package com.project.tour.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.AdminDTO;
import com.project.tour.dto.LoginDTO;
import com.project.tour.dto.UserDTO;

@WebServlet("/login.do")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/login.jsp");

		dispatcher.forward(req, resp);

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        String loginmode = req.getParameter("loginmode");

        LoginDTO dto = new LoginDTO();
        dto.setId(id);
        dto.setPw(pw);
        dto.setLoginmode(loginmode);
        
        
        UserDAO dao = new UserDAO();
        
        if (dao.login(dto) instanceof AdminDTO) {
            
            AdminDTO adto = (AdminDTO)dao.login(dto);
            
            adto.setLoginmode(loginmode);

             HttpSession session = req.getSession();
             session.setAttribute("auth", adto);

             resp.sendRedirect("/planitshare/main.do");
            

         } else if (dao.login(dto) instanceof UserDTO) {
            
            
            UserDTO udto = (UserDTO)dao.login(dto);
            
             udto.setLoginmode(loginmode);
              
             HttpSession session = req.getSession();
             session.setAttribute("auth", udto);

             resp.sendRedirect("/planitshare/main.do");

         } else {
            req.setAttribute("loginError", "y");
             RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
             dispatcher.forward(req, resp);
         }
        
        
   
        
        
      

		
		

	}

}
