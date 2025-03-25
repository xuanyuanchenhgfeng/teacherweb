package com.main.web.controller;

import com.main.web.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Connection connection = JDBCUtils.getConnection();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sql = "select * from user where username = ? and userpwd = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println("success");
                out.print("success");
            } else {
                out.print("fail");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCUtils.close(connection, ps);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
