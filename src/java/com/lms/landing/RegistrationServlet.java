package com.lms.landing;

import com.mysql.cj.xdevapi.PreparableStatement;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("name");
        String uemail = request.getParameter("email");
        String upwd = request.getParameter("password");
        RequestDispatcher dispatcher = null;
        Connection conn = null;
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cruddata", "root", "root");
            Statement stmt = conn.createStatement();

            dispatcher = request.getRequestDispatcher("registration.jsp");
            if (uname.isEmpty() || uemail.isEmpty() || upwd.isEmpty()) {
                request.setAttribute("status", "failed");
                request.setAttribute("errorMessage", "Please fill in all the fields.");
                dispatcher = request.getRequestDispatcher("registration.jsp");
            } else {
                PreparedStatement pst = conn.prepareStatement("insert into users(uname,upwd,uemail) values(?,?,?)");
                pst.setString(1, uname);
                pst.setString(2, upwd);
                pst.setString(3, uemail);

                int rowCount = pst.executeUpdate();
                if (rowCount > 0) {
                    request.setAttribute("status", "success");
                    dispatcher = request.getRequestDispatcher("login.jsp");
                } else {
                    request.setAttribute("status", "failed");
                    request.setAttribute("errorMessage", "Failed to register user. Please try again.");
                    dispatcher = request.getRequestDispatcher("registration.jsp");
                }
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
