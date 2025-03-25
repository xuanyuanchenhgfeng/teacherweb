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
import java.sql.SQLException;
import java.util.Enumeration;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Enumeration<String> parameterNames = req.getParameterNames();

        System.out.println(1);
        Connection connection = JDBCUtils.getConnection();
        System.out.println(2);
        PreparedStatement ps = null;
        String sql = "insert into teacher(name,sex,age,phone,address,teano) values(?,?,?,?,?,?)";
        System.out.println(3);
        try {
            ps = connection.prepareStatement(sql);
            while (parameterNames.hasMoreElements()){
                String name = parameterNames.nextElement();
                System.out.println(name+" : "+req.getParameter(name));
            }
            ps.setObject(1,req.getParameter("name"));
            ps.setObject(2,req.getParameter("sex"));
            ps.setObject(3,Integer.parseInt(req.getParameter("age")));
            ps.setObject(4,req.getParameter("phone"));
            ps.setObject(5,req.getParameter("address"));
            ps.setObject(6,req.getParameter("teano"));
            int i = ps.executeUpdate();
            System.out.println("i = " + i);
            if(i != 0){
                writer.write("添加成功");
            }else{
                writer.write("添加失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCUtils.close(connection,ps);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
