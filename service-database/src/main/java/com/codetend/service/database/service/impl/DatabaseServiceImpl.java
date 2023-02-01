package com.codetend.service.database.service.impl;

import com.codetend.service.database.entity.User;
import com.codetend.service.database.mapper.UserMapper;
import com.codetend.service.database.service.IDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseServiceImpl implements IDatabaseService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public User getUser(long id) {
        return userMapper.getUser(id);
    }

    @Override
    public void setUser(User user) {
        userMapper.setUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userMapper.deleteUser(id);
    }
}
