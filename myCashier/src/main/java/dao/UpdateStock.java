package dao;

import DBUtil.DBUtil;
import entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStock {
    public boolean updateStock(Goods goods, int buyGoodsNum ) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String sql = "update goods set stock=? where id=?";
            connection = DBUtil.getConnection(true);
            ps = connection.prepareStatement(sql);
            ps.setInt(1,goods.getStock()-buyGoodsNum);
            ps.setInt(2,goods.getId());

            if(ps.executeUpdate() == 0) {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,null);
        }
        return true;
    }
}
