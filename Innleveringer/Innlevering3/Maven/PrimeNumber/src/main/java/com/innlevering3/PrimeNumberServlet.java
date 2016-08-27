package com.innlevering3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * <p>
 *     A servletclass that display an answer based on users input on to another page
 * </p>
 * @author Anita Ilieva
 * created onß 17.04.2016
 */

@WebServlet("/primenumber")

public class PrimeNumberServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PrimeNumberServlet.class);

    /**
     * This method returns back to the front page
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException exception
     * @throws IOException exception
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath());
    }
    /**
     * The POST method reads the input and returns a answer.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException exception
     * @throws IOException exception
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num = request.getParameter("primenumber");
        logger.info(new Date().toString() + " - " + num);
        PrimeCheck p = new PrimeCheck(num);


        PrintWriter out = response.getWriter();

        if (!p.isInt()) {
            out.println(p.getError());
            return;
        }


        int number = Integer.parseInt(num);
        if(number <= 0){
            out.println("You need to enter a positive number.");
            return;
        }

        int prime = p.getNum();
        out.println(p.answer(prime));

    }
}