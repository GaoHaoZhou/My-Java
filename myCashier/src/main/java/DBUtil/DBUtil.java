package DBUtil;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static DataSource dataSource;
    static{
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("mysql");
        mysqlDataSource.setDatabaseName("cash");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");
        dataSource =mysqlDataSource;
    }
    public static Connection getConnection(boolean autocommit) throws SQLException {
        Connection con = dataSource.getConnection();
        con.setAutoCommit(autocommit);
        return con;
    }
    public static void close(Connection con, PreparedStatement ps, ResultSet rs){

        try{
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if (con!=null){
                con.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
