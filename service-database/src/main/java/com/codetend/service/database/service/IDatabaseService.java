package com.codetend.service.database.service;

import com.codetend.service.database.entity.Order;
import com.codetend.service.database.entity.User;

import java.util.List;

public interface IDatabaseService {
    List<User> getUsers(int offset, int rows);
    List<User> getUsersLimit();
    User getUser(long id);
    void setUser(User user);
    void deleteUser(long id);

    List<Order> getOrders(int offset, int rows);
    Order getOrder(long id);
    void setOrder(Order user);
    void deleteOrder(long id);
}
