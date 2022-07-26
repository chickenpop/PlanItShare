package com.project.tour.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.tour.dto.UserDTO;

@WebServlet("/register.do")
public class Register extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/register.jsp");
        dispatcher.forward(req, resp);
    }

	
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isRegistered = false;
        boolean isAdded = false;

        int size = 1024 * 1024 * 100;
        String path = req.getRealPath("/userimage");

        MultipartRequest multi = new MultipartRequest(req, path, size, "UTF-8", new DefaultFileRenamePolicy());

        String id = multi.getParameter("id");
        String name = multi.getParameter("name");
        String tel = multi.getParameter("tel");
        String gender = multi.getParameter("gender");
        String pw = multi.getParameter("pw");
        String profile = multi.getFilesystemName("profile");

        UserDTO dto = new UserDTO();
        

        dto.setId(id);
        dto.setName(name);
        dto.setTel(tel);
        dto.setGender(gender);
        dto.setPw(pw);
        dto.setProfile(profile);
        
        

        UserDAO dao = new UserDAO();
        
        
        //회원가입
        int result = dao.register(dto);
        
          
    
    	resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;");

        
        PrintWriter writer = resp.getWriter();

        
        
        if (result == 1) {
            resp.sendRedirect("/planitshare/login.do");
            
        } else {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('회원가입을 실패하였습니다.')");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("<html>");
        }
    
    
    }
	
}
