package dao;

import DBUtil.DBUtil;
import entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {
    public Account login(String username, String password){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            String sql = "select id,username,password from account where username = ? ";
            connection = DBUtil.getConnection(true);
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            Account user = new Account();
            if(rs.next()){
                Integer id = rs.getInt("id");
                user.setId(id);
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
            }
            return user;
        } catch(SQLException e){
             e.printStackTrace();
         }finally {
            DBUtil.close(connection,ps,rs);
      }
      return null;
    }
}