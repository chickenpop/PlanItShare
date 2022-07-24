package com.project.tour.plan;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/plan/add/date.do")
public class GetDate extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String startdate = req.getParameter("startdate"); // > 07/21/2022
		String enddate = req.getParameter("enddate"); // > 07/23/2022
		String day = "";

		/*
		 * System.out.println(startdate.substring(0,2)); // 07
		 * System.out.println(startdate.substring(3,5)); // 20
		 * System.out.println(startdate.substring(6,10)); // 2022
		 */

		// yyyy-mm-dd 형식으로 변경하기
		startdate = (startdate.substring(6, 10) + "-" + (startdate.substring(0, 2)) + "-"
				+ (startdate.substring(3, 5)));
		enddate = (enddate.substring(6, 10) + "-" + (enddate.substring(0, 2)) + "-" + (enddate.substring(3, 5)));

		//System.out.println("startdate: " + startdate); // 2022-07-02
		//System.out.println("enddate: " + enddate);

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");

		// 일차 계산하기 (두 날짜끼리 뺀 다음 + 1)

		try {

			// String type > date Type 변환시 생기는 예외로 인한 try catch 오류 처리
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

			// startdate, enddate 파싱
			Date FirstDate = format.parse(startdate);
			Date SecondDate = format.parse(enddate);

			// Date로 변환된 두 날짜를 계산한 뒤 그 리턴값으로 long type 변수
			long calDate = Math.abs(FirstDate.getTime() - SecondDate.getTime());

			// string으로 형변환 + 1을 해줘야 함
			day = Long.toString(TimeUnit.DAYS.convert(calDate, TimeUnit.MILLISECONDS) + 1);

			//System.out.println("day: " + day);

			// day, startdate와 enddate를 돌려줘야함

		} catch (Exception e) {
			System.out.println("GetDate.doGet");
			e.printStackTrace();

		}

		
		 PrintWriter writer = resp.getWriter();
		 
		 
		 writer.println("[");
		 writer.println("{");
		 writer.printf("\"day\" : \"%s\"", day); writer.println("}");
		 writer.println("]");
		 writer.close();
		 

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		

	}
	

}









