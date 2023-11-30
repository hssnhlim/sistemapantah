package com.lms.landing;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uemail = request.getParameter("email");
        String upwd = request.getParameter("password");
        Connection conn = null;
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cruddata", "root", "root");
            Statement stmt = conn.createStatement();

            
            if (uemail.isEmpty() || upwd.isEmpty()) {
                request.setAttribute("status", "failed");
                request.setAttribute("errorMessage", "Please fill in all the fields.");
                dispatcher = request.getRequestDispatcher("login.jsp");
            } else {
                PreparedStatement pst = conn.prepareStatement("select * from users where uemail = ? and upwd = ?");
                pst.setString(1, uemail);
                pst.setString(2, upwd);
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()) {
                    session.setAttribute("name", rs.getString("uname"));
                    dispatcher = request.getRequestDispatcher("homepage.jsp");
                } else {
                    request.setAttribute("status", "failed");
                    request.setAttribute("errorMessage", "Username or password wrong! Please try again.");
                    dispatcher = request.getRequestDispatcher("login.jsp");
                }
            }

            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
