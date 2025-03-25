package com.main.web.controller;

import com.alibaba.fastjson.JSON;
import com.main.web.JDBCUtils;
import com.main.web.bean.Result;

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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || username.equals("") || password == null || password.equals("")) {
            out.print("用户名或密码不能为空");
            return;

        }
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        String sql = "select username from user where username = ?";
        String sql1 = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                out.print(new Result().error("用户名已存在"));
                System.out.println(new Result().error("用户名已存在"));
            } else {
                sql1 = "insert into user(username,userpwd) values(?,?)";
                ps = connection.prepareStatement(sql1);
                ps.setString(1, username);
                ps.setString(2, password);
                int i = ps.executeUpdate();
                if (i!=0){
                    out.print(new Result().success("注册成功"));
                    System.out.println(new Result().success("注册成功"));
                }else {
                    out.print(new Result().error("注册失败"));
                    System.out.println(new Result().error("注册失败"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
