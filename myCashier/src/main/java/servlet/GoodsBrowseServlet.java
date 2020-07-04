package servlet;


import DBUtil.DBUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Goods;
import service.GoodsBrowseService;

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
import java.util.List;

@WebServlet("/goods")
public class GoodsBrowseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset = utf-8");

        PrintWriter writer = resp.getWriter();

        GoodsBrowseService browse = new GoodsBrowseService();
        List<Goods> goodsList=browse.browse();
        System.out.println("列表：");
        System.out.println(goodsList);

        ObjectMapper objectMapper = new ObjectMapper();
        //将goodsList写到流writer中
        objectMapper.writeValue(writer,goodsList);
        //转为字符串，推到前端
        writer.write(writer.toString());
    }
}
