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

import com.project.tour.dto.FoodReviewDTO;
import com.project.tour.dto.LodgingReviewDTO;
import com.project.tour.dto.TourReviewDTO;
import com.project.tour.dto.UserDTO;
import com.project.tour.user.UserDAO;

@WebServlet("/mypage/myreview.do")
public class MyReview extends HttpServlet {
	
	
	Calendar now = Calendar.getInstance();
    String strNow = String.format("%tF", now);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			
			String distinct = "food";
		
		
		 	HttpSession session = req.getSession();
	        UserDTO dto = (UserDTO) session.getAttribute("auth");

	        int page = 1;
	        
	        
	        if (req.getParameter("distinct") != null) {
	        	distinct = req.getParameter("distinct");
	        }
	        

	        if (req.getParameter("page") != null) {
	            page = Integer.parseInt(req.getParameter("page"));
	        }

	        UserDAO dao = new UserDAO();
	        
	        Pagination pagination = null;
	        
	        //System.out.println("MyFoodReview.java의 id= " + dto.getId());
	        
	        if (distinct.equals("food")) { //음식점일때 (기본값)
	        	
	        	pagination = new Pagination(page, dao.getFoodReviewCount(dto), 20, 10);	        	
	        	ArrayList<FoodReviewDTO> list = dao.getMyFoodReview(dto.getId(), page);
	        	foodSetList(list);
	        	
	        	req.setAttribute("list", list);
	        	req.setAttribute("distinct", "food");
	        	
	        	
	        } else if (distinct.equals("tour")) { //관광명소
	        	pagination = new Pagination(page, dao.getTourReviewCount(dto), 20, 10);
	        	ArrayList<TourReviewDTO> list = dao.getMyTourReview(dto.getId(), page);
	        	tourSetList(list);
	        	
	        	req.setAttribute("list", list);
	        	req.setAttribute("distinct", "tour");
	        	
	        } else { //숙소
	        	
	        	pagination = new Pagination(page, dao.getLodgingReviewCount(dto), 20, 10);
	        	ArrayList<LodgingReviewDTO> list = dao.getMyLodgingReview(dto.getId(), page);
	        	lodgingSetList(list);
	        	
	        	req.setAttribute("list", list);
	        	req.setAttribute("distinct", "lodging");
	        }
	        
	        
	        req.setAttribute("pagination", pagination); 
		
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/myreview.jsp");
		dispatcher.forward(req, resp);

	}

	
	private void lodgingSetList(ArrayList<LodgingReviewDTO> list) {
		
		for(LodgingReviewDTO dto : list) {

            //regdate
            if (dto.getRegdate().startsWith(strNow)) {
                //오늘
                dto.setRegdate(dto.getRegdate().substring(11));
            } else {
                //어제 이전
                dto.setRegdate(dto.getRegdate().substring(0, 10));
            }

            //제목 자르기
            if (dto.getContent().length() > 25) {
                dto.setContent(dto.getContent().substring(0,25) + "...");
            }
		}
		
	}


	private void tourSetList(ArrayList<TourReviewDTO> list) {
		
		for(TourReviewDTO dto : list) {

            //regdate
            if (dto.getRegdate().startsWith(strNow)) {
                //오늘
                dto.setRegdate(dto.getRegdate().substring(11));
            } else {
                //어제 이전
                dto.setRegdate(dto.getRegdate().substring(0, 10));
            }

            //제목 자르기
            if (dto.getContent().length() > 25) {
                dto.setContent(dto.getContent().substring(0,25) + "...");
            }
		
        }
		
	}


	private void foodSetList(ArrayList<FoodReviewDTO> list) {
		
        for(FoodReviewDTO dto : list) {

            //regdate
            if (dto.getRegdate().startsWith(strNow)) {
                //오늘
                dto.setRegdate(dto.getRegdate().substring(11));
            } else {
                //어제 이전
                dto.setRegdate(dto.getRegdate().substring(0, 10));
            }

            //제목 자르기
            if (dto.getContent().length() > 25) {
                dto.setContent(dto.getContent().substring(0,25) + "...");
            }
		
        }

	}

}
