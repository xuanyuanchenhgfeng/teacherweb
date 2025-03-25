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

@WebServlet("/binDelete")
public class BinDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        String sql = "delete from rubbishbin where id = ?";
        System.out.println(sql);
        String id = req.getParameter("id");
        System.out.println(id);
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            if(ps.executeUpdate()!=0){
                out.print("删除成功");
            }else {
                out.print("删除失败");
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
