package org.tours.toursiteback.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tours.toursiteback.dao.MySQLController;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tourInfo")
public class TourInfoServlet extends HttpServlet {
    private final MySQLController mySQLController = new MySQLController();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");
        int tourId = Integer.parseInt(request.getParameter("tourId"));

        PrintWriter printWriter = response.getWriter();
        printWriter.println(new ObjectMapper().writeValueAsString(mySQLController.getTourInfo(tourId)));
    }
}