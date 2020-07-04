package dao;

import DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegister {
    public int regiter(String username,String password){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "insert into account (username,password) values(?,?)";
            connection = DBUtil.getConnection(true);
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            int ret =ps.executeUpdate();
            return ret;
        }catch (SQLException e){
             e.printStackTrace();
         }finally{
              DBUtil.close(connection,ps,null);
         }
         return 0;
    }
}
