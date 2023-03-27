package com.mentorship.jdbc.dao;

import com.mentorship.models.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findById(int id);

    boolean create(User entity);
}
