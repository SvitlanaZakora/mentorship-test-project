package com.mentorship.jdbc.dao.impl;

import com.mentorship.jdbc.ConnectorDB;
import com.mentorship.jdbc.dao.UserDao;
import com.mentorship.models.User;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public static final String SQL_INSERT_USER = "INSERT INTO MENTORSHIP.USER VALUES (default, ?, ?, ?)";
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM MENTORSHIP.USER";
    public static final String SQL_SELECT_USER_ID =
            "SELECT * FROM MENTORSHIP.USER WHERE id=?";
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (rs.next()) {
                int user_id = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                Date birthdate = rs.getDate(4);
                users.add(new User(user_id, name, surname, birthdate.toLocalDate()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = ConnectorDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ID)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int user_id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                Date birthdate = resultSet.getDate(4);

                user = new User(user_id, name, surname, birthdate.toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    @Override
    public boolean create(User entity) {
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setDate(3, Date.valueOf(entity.getBirthday()));
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
