package com.main.web.controller.recyclebin;

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

@WebServlet("/binQuery")
public class BinQueryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        String sql = "select * from rubbishbin order by teano ASC";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Teacher> list = new ArrayList<>();
        String teachers = "";

        try {
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
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

