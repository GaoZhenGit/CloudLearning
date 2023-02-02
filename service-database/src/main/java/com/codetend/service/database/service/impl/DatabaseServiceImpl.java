package com.codetend.service.database.service.impl;

import com.codetend.service.database.entity.Order;
import com.codetend.service.database.entity.User;
import com.codetend.service.database.mapper.OrderMapper;
import com.codetend.service.database.mapper.UserMapper;
import com.codetend.service.database.service.IDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseServiceImpl implements IDatabaseService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<User> getUsers(int offset, int rows) {
        return userMapper.getUsers(offset, rows);
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

    @Override
    public List<Order> getOrders(int offset, int rows) {
        return orderMapper.getOrders(offset, rows);
    }

    @Override
    public Order getOrder(long id) {
        return orderMapper.getOrder(id);
    }

    @Override
    public void setOrder(Order order) {
        orderMapper.setOrder(order);
    }

    @Override
    public void deleteOrder(long id) {
        orderMapper.deleteOrder(id);
    }
}
