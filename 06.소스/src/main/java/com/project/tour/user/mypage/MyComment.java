package com.project.tour.user.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.tour.dto.UserDTO;
import com.project.tour.user.UserDAO;
import com.project.tour.dto.MyCommentDTO;

@WebServlet("/mypage/mycomment.do")
public class MyComment extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		UserDTO dto = (UserDTO) session.getAttribute("auth");
		
		int page = 1;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        
        
        UserDAO dao = new UserDAO();
        
        
        Pagination pagination = new Pagination(page, dao.getTotalComment(dto.getId()), 20, 10);
        
        ArrayList<MyCommentDTO> list = dao.getMyComment(dto.getId(), page);
        setList(list);
        
        //System.out.println("MyComment의 list: " + list);
        
        req.setAttribute("pagination", pagination);
        req.setAttribute("list", list);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/mycomment.jsp");
		dispatcher.forward(req, resp);

	}
	
	
	
	

	private void setList(ArrayList<MyCommentDTO> list) {
		
		Calendar now = Calendar.getInstance();
	    String strNow = String.format("%tF", now);
	    
	    for(MyCommentDTO dto : list) {

            //regdate
            if (dto.getRegdate().startsWith(strNow)) {
                //오늘
                dto.setRegdate(dto.getRegdate().substring(11));
            } else {
                //어제 이전
                dto.setRegdate(dto.getRegdate().substring(0, 10));
            }
            

            //댓글 15글자 이상 시 말줄임표
            if (dto.getContent().length() > 15) {
                dto.setContent(dto.getContent().substring(0,15) + "...");
            }
            
            //제목 17글자 이상 시 말줄임표
            if (dto.getTitle().length() > 17) {
                dto.setTitle(dto.getTitle().substring(0,17) + "...");
            }
            
            
            
		}
	    
		
	}





	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
