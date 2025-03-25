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

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
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
        String data = "set ";
        int count = 0;
        if (req.getParameter("name")!=null){
            count++;
            data = data + "name='"+req.getParameter("name")+"',";
        }
        if (req.getParameter("sex")!=null){
            count++;
            data = data + "sex='"+req.getParameter("sex")+"',";
        }
        if (req.getParameter("age")!=null){
            count++;
            data = data + "age="+Integer.parseInt(req.getParameter("age"))+",";
        }
        if (req.getParameter("phone")!=null){
            count++;
            data = data + "phone='"+req.getParameter("phone")+"',";

        }
        if (req.getParameter("address")!=null){
            count++;
            data = data + "address='"+req.getParameter("address")+"',";

        }
        if(count!=0){
            data = data.substring(0,data.length()-1);
        }else {
            data = "";
        }
        String sql = "update teacher  "+ data +" where teano=?";
        System.out.println(sql);
        try {
            ps = connection.prepareStatement(sql);
            while (parameterNames.hasMoreElements()){
                String name = parameterNames.nextElement();
                System.out.println(name+" : "+req.getParameter(name));
            }
            ps.setObject(1,req.getParameter("teano"));
            System.out.println(sql);
            if(ps.executeUpdate()!=0){
                writer.write("success");
            }else{
                writer.write("fail");
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
