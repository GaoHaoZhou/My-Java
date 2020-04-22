package com.glp.model;

import com.glp.util.Database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class User {
    static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public int id;
    public String username;
    public String nickname;
    public String brief;
    private Date registeredAt;

    public String getRegisteredAt() {
        return dateFormat.format(registeredAt);
    }

    public User(int id, String username, String nickname, String brief, Date registeredAt) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.brief = brief;
        this.registeredAt = registeredAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", brief='" + brief + '\'' +
                ", registeredAt=" + registeredAt +
                '}';
    }

    public static User register(String username, String nickname, String password) throws SQLException {
        // 把密码 Hash 后再保存进数据库，放置因为数据库被拖库，进而导致的用户密码泄露
        // MD5 -> SHA264
        password = hash(password);

        int id;
        String brief = "懒得写。";
        Date registeredAt = new Date();

        // SQL
        String sql = "INSERT INTO users (username, password, nickname, brief, registered_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Database.getConnection()) {
            // Statement.RETURN_GENERATED_KEYS) 目的是获取插入后的 自增 id
            try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, nickname);
                stmt.setString(4, brief);
                stmt.setString(5, dateFormat.format(registeredAt));

                stmt.executeUpdate();

                // 插入成功，就可以通过 getGeneratedKeys 获取自增 id
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                        return new User(id, username, nickname, brief, registeredAt);
                    }
                }
            }
        }

        return null;
    }

    public static User getByUsername(String username) throws SQLException, ParseException {
        String sql = "SELECT id, nickname, brief, registered_at FROM users WHERE username = ?";
        User user = null;

        try (Connection con = Database.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        String nickname  = rs.getString(2);
                        String brief = rs.getString(3);
                        String registeredAtStr  = rs.getString(4);
                        Date registeredAt = dateFormat.parse(registeredAtStr);
                        user = new User(id, username, nickname, brief, registeredAt);
                    }
                }
            }
        }

        return user;
    }

    private static String hash(String password) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encrypted = digest.digest(password.getBytes("UTF-8"));
            for (byte b : encrypted) {
                sb.append(String.format("%02x", b));
            }
            System.out.println(sb.toString());
            System.out.println(sb.toString().length());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return password;
        }
    }

    public static void update(int id, String nickname, String brief) throws SQLException {
        String sql = "UPDATE users SET nickname = ?, brief = ? WHERE id = ?";
        try (Connection con = Database.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, nickname);
                stmt.setString(2, brief);
                stmt.setInt(3, id);

                stmt.executeUpdate();
            }
        }
    }

    public static User login(String username, String password) throws SQLException {
        String sql = "SELECT id, nickname, brief, registered_at FROM users WHERE username = ? AND PASSWORD = ?";
        try (Connection con = Database.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, hash(password));  // 密码在保存时已经被 Hash 过了，查找也得进行一次 Hash
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String nickname = rs.getString("nickname");
                        String brief = rs.getString("brief");
                        Date registeredAt = dateFormat.parse(rs.getString("registered_at"));
                        return new User(id, username, nickname, brief, registeredAt);
                    } else {
                        return null;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
