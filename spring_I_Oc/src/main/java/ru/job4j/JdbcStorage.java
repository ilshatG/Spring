package ru.job4j;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class JdbcStorage implements Storage {
    private Connection connection;
    private static final String TABLE = "springusers";

    JdbcStorage() {
        init();
    }

    public boolean init() {
        try (InputStream in = JdbcStorage.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            if (!isTableExist()) {
                createTable();
            }
        } catch (Exception e) {
            connection = null;
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public void add(User user) {

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO " + TABLE + " (name) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE " + TABLE + " SET name = ? where id = ?")) {
            ps.setString(1, user.getName());
            ps.setLong(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public void delete(User user) {

        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM " + TABLE + " WHERE ID = ?")) {
            ps.setLong(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    @Override
    public User findById(User user) {
        User result = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + TABLE + " WHERE id = ?")) {
            ps.setLong(1, user.getId());
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    result = new User(rs.getLong("id"), rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + TABLE)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new User(rs.getLong("id"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean isTableExist() {
        boolean result = false;
        try {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, TABLE, null);
            result = rs.next();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean createTable() {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + TABLE + " (id SERIAL PRIMARY KEY, name varchar(225))")) {
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
