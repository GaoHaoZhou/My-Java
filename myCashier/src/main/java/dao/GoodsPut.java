package dao;

import DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsPut {
    public int put(String name,String stock,String introduce,String unit,String price,String discount){
        double doublePrice = Double.valueOf(price)*100;//先将参数字符串转换为小数
        int realPrice = (int) doublePrice;

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "insert into goods(name,introduce,stock,unit,price,discount) values(?,?,?,?,?,?)";
            connection = DBUtil.getConnection(true);

            ps = connection.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,introduce);
            ps.setInt(3,Integer.valueOf(stock));
            ps.setString(4,unit);
            ps.setInt(5,realPrice);
            ps.setInt(6,Integer.valueOf(discount));

            int ret = ps.executeUpdate();
            return ret;
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return 0;
    }
}
