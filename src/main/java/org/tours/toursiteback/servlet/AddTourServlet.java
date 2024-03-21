package org.tours.toursiteback.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.tours.toursiteback.dao.mongoDB.MongoDBController;
import org.tours.toursiteback.dao.mysql.MySQLController;
import org.tours.toursiteback.request.AddNewTourRequest;

import java.io.IOException;

@WebServlet("/newTour")
public class AddTourServlet extends HttpServlet {
    private final MySQLController mySQLController = new MySQLController();
    private final MongoDBController mongoDBController = new MongoDBController();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");

        AddNewTourRequest tour = new AddNewTourRequest(
                request.getParameter("title"),
                request.getParameter("description"),
                request.getParameter("direction"),
                request.getParameter("duration")
        );
        mySQLController.addNewTour(tour);

        Part imagePart = request.getPart("image");
    }
}