package dao;

import DBUtil.DBUtil;
import entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsBrowse {
    public List<Goods> browse(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Goods> goodsList = new ArrayList<>();
        try{
            String sql = "select id,name,introduce,stock,unit,price,discount from goods";
            connection = DBUtil.getConnection(true);
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Goods goods = new Goods();
                goods.setId(rs.getInt("id"));
                goods.setName(rs.getString("name"));
                goods.setIntroduce(rs.getString("introduce"));
                goods.setStock(rs.getInt("stock"));
                goods.setUnit(rs.getString("unit"));
                goods.setPrice(rs.getInt("price"));
                goods.setDiscount(rs.getInt("discount"));
                goodsList.add(goods);
             }
             return goodsList;
             }catch (SQLException e){
                e.printStackTrace();
             }finally {
                 DBUtil.close(connection,ps,rs);
             }
            return null;
    }
}

