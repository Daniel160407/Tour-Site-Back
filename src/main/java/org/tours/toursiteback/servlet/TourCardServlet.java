package org.tours.toursiteback.servlet;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.tours.toursiteback.dao.MySQLController;

@WebServlet("/tourCard")
public class TourCardServlet extends HttpServlet {
    private final MySQLController mySQLController = new MySQLController();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(new ObjectMapper().writeValueAsString(mySQLController.getTours()));
    }
}