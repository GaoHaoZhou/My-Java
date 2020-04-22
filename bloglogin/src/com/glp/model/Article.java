package com.glp.model;

import com.glp.util.Database;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Article {
    public int id;
    public String title;
    public String body;
    public Date publishedAt;
    public String coverImage;

    public Article(int id, String title, String body, Date publishedAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.publishedAt = publishedAt;
    }

    public static Article publish(User user, String title, String body, String coverImageUrl) throws SQLException {
        Date publishedAt = new Date();
        String sql = "INSERT INTO articles (author_id, cover_image, title, body, published_at) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = Database.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, user.id);
                stmt.setString(2, coverImageUrl);
                stmt.setString(3, title);
                stmt.setString(4, body);
                stmt.setString(5, User.dateFormat.format(publishedAt));
                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (!rs.next()) {
                        return null;
                    }

                    int id = rs.getInt(1);
                    return new Article(id, title, body, publishedAt);
                }
            }
        }
    }

    public static Article getById(int id) throws SQLException {
        String sql = "SELECT cover_image, title, body, published_at FROM articles WHERE id = ?";
        try (Connection con = Database.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        return null;
                    }

                    String coverImage = rs.getString(1);
                    String title = rs.getString(2);
                    String body = rs.getString(3);
                    Date publishedAt = User.dateFormat.parse(rs.getString(4));
                    Article article = new Article(id, title, body, publishedAt);
                    article.coverImage = coverImage;//这里构造方法中没有写明，所以采用了这种方式，手动赋值
                    return article;
                } catch (ParseException e) {
                    return null;
                }
            }
        }
    }

    public static List<Article> list() throws SQLException {
        String sql = "SELECT id, cover_image, title, body, published_at FROM articles ORDER BY id DESC";
        List<Article> articleList = new ArrayList<>();
        try (Connection con = Database.getConnection()) {
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String coverImage = rs.getString(2);
                        String title = rs.getString(3);
                        String body = rs.getString(4);
                        try {
                            Date publishedAt = User.dateFormat.parse(rs.getString(5));
                            Article article = new Article(id, title, body, publishedAt);
                            article.coverImage = coverImage;
                            articleList.add(article);
                        } catch (ParseException e) {
                            continue;
                        }
                    }
                }
            }
        }

        return articleList;
    }
}
