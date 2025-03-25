package com.main.web.controller.recyclebin;

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
@WebServlet("/binRestore")
public class BinRestoreServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        try {
            String id = req.getParameter("id");
            String sql = "insert into teacher(id,name,sex,age,phone,address,teano) select id,name,sex,age,phone,address,teano from rubbishbin where id=?";
            System.out.println(sql);
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            System.out.println(id);
            int i =ps.executeUpdate();
            if(i!=0){
                System.out.println("还原成功");
                out.print("还原成功");
            }else {
                System.out.println("还原失败");
                out.print("还原失败");
            }
            String sql2 = "delete from rubbishbin where id=?";
            ps = connection.prepareStatement(sql2);
            ps.setString(1, id);
            if(ps.executeUpdate()!=0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
