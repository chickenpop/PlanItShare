package com.project.tour.city.lodging;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.tour.city.CityDAO;
import com.project.tour.dto.CityDTO;
import com.project.tour.dto.LodgingDTO;

@WebServlet("/city/lodge.do")
public class LodgingList extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      
      String cseq = req.getParameter("cseq");
      
      LodgingDAO ldao = new LodgingDAO();
      
      CityDAO cdao = new CityDAO();
      
      
      ArrayList<LodgingDTO> list = ldao.lodginglist(cseq);

      CityDTO dto = cdao.findCity(cseq);
      
      
      req.setAttribute("list", list);
      req.setAttribute("dto", dto);
      
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/city/lodging/lodginglist.jsp");

      dispatcher.forward(req, resp);
   }


   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      
      
      

   }

}
