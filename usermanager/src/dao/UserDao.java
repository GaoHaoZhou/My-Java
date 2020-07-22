package dao;

import entity.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDao {
    public int register(User registerUser){
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            String sql = "insert into usermessage(name,username,password,gender,age,address,qq,email) values(?,?,?,?,?,?,?,?)";
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1,registerUser.getName());
            ps.setString(2,registerUser.getUsername());
            ps.setString(3,registerUser.getPassword());
            ps.setString(4,registerUser.getGender());
            ps.setInt(5,registerUser.getAge());
            ps.setString(6,registerUser.getAddress());
            ps.setString(7,registerUser.getQq());
            ps.setString(8,registerUser.getEmail());

            int ret = ps.executeUpdate();
            return ret;
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,null);
        }
        return 0;
    }
    //登录
    public User login(User loginUser) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            String sql = "select * from usermessage where username=? and password=?";
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, loginUser.getUsername());
            ps.setString(2,loginUser.getPassword());
            rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setQq(rs.getString("qq"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,rs);
        }
        return user;

    }

    public int add(User addUser) {

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String sql="insert into usermessage(name,gender,age,address,qq,email) values(?,?,?,?,?,?)";
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, addUser.getName());
            ps.setString(2, addUser.getGender());
            ps.setInt(3,addUser.getAge());
            ps.setString(4, addUser.getAddress());
            ps.setString(5, addUser.getQq());
            ps.setString(6, addUser.getEmail());
            int ret = ps.executeUpdate();
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,null);
        }
        return 0;

        }
    public int delete(int id) {
        System.out.println("Delete："+id);
//删除成功返回1
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            String sql="delete from usermessage where id=?";
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int ret = ps.executeUpdate();
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,null);
        }
        return 0;

    }
    public  User find(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        String sql = "select * from usermessage where id=?";
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setQq(rs.getString("qq"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return user;

            }
    public int update(User updateUser) {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "update usermessage set name=?,age=?,gender=?,address=?,qq=?,email=? where id=?";
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, updateUser.getName());
            ps.setInt(2,updateUser.getAge());
            ps.setString(3, updateUser.getGender());
            ps.setString(4, updateUser.getAddress());
            ps.setString(5, updateUser.getQq());
            ps.setString(6, updateUser.getEmail());
            ps.setInt(7,updateUser.getId());
            int ret = ps.executeUpdate();
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,null);
        }
        return 0;
    }
    /**
     * 分页查询
     * start：开始查询的起始位置
     * rows：共查询的记录
     * map：包含：currentPage、rows、name、address、email
     */
    public List<User> findByPage(int start, int rows, Map<String, String[]> map) {
        String sql="select * from usermessage where 1=1";
        StringBuilder s=new StringBuilder(sql);
        Set<String> set = map.keySet();
        List<Object> list =new ArrayList<>();
        for(String key : set){
            String value=map.get(key)[0];
            if(value!=null && !"".equals(value)){
//有值
                s.append(" and ").append(key).append(" like ? ");
                list.add("%"+value+"%");
            }
        }
        s.append(" limit ?,? ");
        list.add(start);
        list.add(rows);
//s: select * from usermessage where 1=1 and name like ? and address like ? and email like
//? limit ?,?
//select * from usermessage where 1=1 and name like '%gaobo%' and address like '%陕西%'
        System.out.println("s: "+ s);
//list: [%gaobo%, %陕西%, %gaobo@bitedu.tech%, 0, 5] -->这是第一页
        System.out.println("list: "+ list);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(s.toString());
            setValues(ps,list.toArray());
            rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setQq(rs.getString("qq"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return users;

            }

    private void setValues(PreparedStatement ps, Object... arrays) throws SQLException {
        for (int i = 0; i <arrays.length ; i++) {
            ps.setObject(i+1,arrays[i]);
        }
    }

    /**
     * 查询共有多少条记录
     * @param map 包含name address email
     * @return
     */
    public int findAllRecord(Map<String, String[]> map) {
        String sql="select count(*) from usermessage where 1=1";
        StringBuilder s=new StringBuilder();
        s.append(sql);
        Set<String> keySet = map.keySet();
        List<Object> list=new ArrayList<>();
        for(String key:keySet){
            String value=map.get(key)[0];
            if(value!=null && !"".equals(value)){
//有值
                s.append(" and ").append(key).append(" like ?");
                list.add("%"+value+"%");
            }
        }
        System.out.println("findAllRecord::sql:" + s);
        System.out.println("findAllRecord::list:"+list);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(s.toString());
            setValues(ps,list.toArray());
            rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt(1); //对总记录数赋值等价于rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,ps,rs);
        }
        return count;

        }

//    public static void main(String[] args) {
//        User user = find(3);
//        System.out.println(user);
//    }

}
