package com.project.tour.user.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.user.UserDAO;

@WebServlet("/mypage/mycommentdel.do")
public class MyCommentDel extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
req.setCharacterEncoding("UTF-8");
		
		String [] checkdel = req.getParameterValues("check");
		
		UserDAO dao = new UserDAO();
		
		
		int result = 0;
		

		result = dao.commentcheckdel(checkdel);
		
		
		req.setAttribute("result", result);

		
		
        if (result >= 1) {
        	
            resp.sendRedirect("/planitshare/mypage/mycomment.do");
            
            
        } else if (result == 0){
        	

            resp.setContentType("text/html;charset=UTF-8");

            PrintWriter writer = resp.getWriter();

            writer.println("<html>");
            writer.println("<body>");
            writer.println("<script>");
            writer.println("alert('삭제를 실패했습니다.');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");

            writer.close();

        }
		
		
	}

}
