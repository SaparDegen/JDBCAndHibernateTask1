package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private final Connection conn = Util.getConnection();

    public UserDaoJdbcImpl() {
    }

    public void createUsersTable() {
        String sql = "create table users (id serial primary key, name varchar(50) not null, last_name varchar(50) not null, age int not null);";
        try (Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("User table was created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table users;";
        try (Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("Table was dropped");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users (name, last_name, age) values (?,?,?);";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
            System.out.println("A new user " + name + " was inserted into the table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Current id: " + id + " was deleted from the table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "select * from users;";
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql = "truncate users;";
        try (Statement statement = conn.createStatement()) {
            statement.execute(sql);
            System.out.println("All data was cleaned from the table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}