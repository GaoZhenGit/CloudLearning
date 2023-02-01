package com.codetend.service.database.service;

import com.codetend.service.database.entity.User;

import java.util.List;

public interface IDatabaseService {
    List<User> getUsers();
    User getUser(long id);
    void setUser(User user);
    void deleteUser(long id);
}
