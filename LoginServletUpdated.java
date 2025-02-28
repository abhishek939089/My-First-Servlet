package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Abhishek"),
                @WebInitParam(name = "password", value = "Abhi@045")
        }
)
public class LoginServletUpdated extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameRegex = "^[A-Z][a-zA-Z]{2,}$";  // Starts with Capital, minimum 3 characters
        String passRegex = "(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%]).{8,20}";

        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        if (!Pattern.matches(nameRegex, user)) {
            out.println("<font color='red'>Invalid Name! It must start with a capital letter and have at least 3 characters.</font>");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, resp);
            return;
        }

        if ((userID.equals(user) && password.equals(pwd)) && Pattern.matches(passRegex, password)) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            out.println("<font color='red'>Either username or password is incorrect</font>");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.include(req, resp);
        }
    }
}
