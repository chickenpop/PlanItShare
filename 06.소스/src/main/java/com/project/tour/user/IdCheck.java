package com.project.tour.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/idcheck.do")
public class IdCheck extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    
    	resp.setCharacterEncoding("UTF-8");
    	resp.setContentType("application/json");
    	
    	//아이디 가져오기
        String id = req.getParameter("id");

        UserDAO dao = new UserDAO();
        
        //아이디 중복검사
        int result = dao.Idcheck(id);
        


        
        PrintWriter writer = resp.getWriter();
        writer.printf("{\"result\" : \"%d\"}", result);
        writer.close();
        
        
    }

}
