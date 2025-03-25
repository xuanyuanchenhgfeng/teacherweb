package com.main.web.controller;

import com.alibaba.fastjson.JSON;
import com.main.web.JDBCUtils;
import com.main.web.bean.Teacher;

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
import java.util.ArrayList;

@WebServlet("/queryServlet")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();

//        Enumeration<String> parameterNames = req.getParameterNames();
//        while (parameterNames.hasMoreElements()){
//            String name = parameterNames.nextElement();
//            String value = req.getParameter(name);
//            System.out.println(name+"--"+value);
//        }

        String sql = "select * from teacher where 1=1";
        if(req.getParameter("teano")!=null){
            sql+=" and teano like '%"+req.getParameter("teano")+"%'";

        }
        if(req.getParameter("name")!=null){
            sql+=" and name like '%"+req.getParameter("name")+"%'";

        }
        if(req.getParameter("age")!=null){
            sql+=" and age like '%"+req.getParameter("age")+"%'";

        }
        if(req.getParameter("sex")!=null){
            sql+=" and sex like '%"+req.getParameter("sex")+"%'";
        }
        if(req.getParameter("phone")!=null){
            sql+=" and phone like '%"+"?"+"%'";
        }
        if(req.getParameter("address")!=null){
            sql+=" and address like '%"+req.getParameter("address")+"%'";
        }

        System.out.println(sql);
        sql += " order by teano ASC";
        System.out.println(sql);
        Connection connection = JDBCUtils.getConnection();


        PreparedStatement ps=null;
        ResultSet resultSet=null;

        ArrayList<Teacher> list = new ArrayList<>();

        String teachers = "";
        try {
            ps=connection.prepareStatement(sql);

            resultSet = ps.executeQuery();
            while(resultSet.next()){
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getString("id"));
                teacher.setTeano(resultSet.getString("teano"));
                teacher.setName(resultSet.getString("name"));
                teacher.setAge(resultSet.getString("age"));
                teacher.setSex(resultSet.getString("sex"));
                teacher.setPhone(resultSet.getString("phone"));
                teacher.setAddress(resultSet.getString("address"));
                list.add(teacher);
            }
            teachers = JSON.toJSONString(list);
            //System.out.println(teachers);;
            writer.println(teachers);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JDBCUtils.close(connection,ps,resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
