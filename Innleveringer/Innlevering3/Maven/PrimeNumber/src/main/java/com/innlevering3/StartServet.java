package com.innlevering3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * A servlet that displays the frontpage
 *
 * </p>
 * @author Anita Ilieva
 * created on 17.04.2016
 */

@WebServlet("/")
public class StartServet extends HttpServlet {


    /**
     * This method displays the front page.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException exception
     * @throws IOException exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("webapp/index.jsp").forward(request, response);
    }
}
