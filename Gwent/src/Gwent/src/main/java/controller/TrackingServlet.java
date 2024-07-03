package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TrackingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("user");
        String uniqueId = request.getParameter("id");

        // Log the click (e.g., save to a database)
        System.out.println("Link clicked by user: " + userId + " with ID: " + uniqueId);

        // Redirect to the intended destination
        response.sendRedirect("https://www.example.com");
    }
}