package com.codetend.service.database.controller;

import com.codetend.common.entity.CommonDataItem;
import com.codetend.common.response.BaseResponse;
import com.codetend.service.database.entity.Order;
import com.codetend.service.database.entity.User;
import com.codetend.service.database.entity.UserAndOrder;
import com.codetend.service.database.service.IDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/database")
@BaseResponse
public class ServiceDatabaseController {
    @Autowired
    private IDatabaseService databaseService;

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") long id) {
        return databaseService.getUser(id);
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getUsers(
            @RequestParam(required = false, name = "offset") Integer offset,
            @RequestParam(required = false, name = "rows") Integer rows) {
        if (offset == null || rows == null) {
            return databaseService.getUsersLimit();
        } else {
            return databaseService.getUsers(offset, rows);
        }
    }

    @PostMapping("/user")
    @ResponseBody
    public void addUser(@RequestBody User user) {
        databaseService.setUser(user);
    }

    @GetMapping("/order/{id}")
    @ResponseBody
    public Order getOrder(@PathVariable("id") long id) {
        return databaseService.getOrder(id);
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<Order> getOrders(
            @RequestParam(required = false, name = "offset") int offset,
            @RequestParam(required = false, name = "rows") int rows) {
        return databaseService.getOrders(offset, rows);
    }

    @PostMapping("/order")
    @ResponseBody
    public void addOrder(@RequestBody Order order) {
        databaseService.setOrder(order);
    }

    @RequestMapping("/addUserAndOrder")
    @ResponseBody
    public void addUserAndOrder(@RequestBody UserAndOrder userAndOrder) {
        databaseService.addUserAndOrder(userAndOrder);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public void addUserRemote(@RequestBody User user) {
        databaseService.addUserRemote(user);
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public void addOrderRemote(@RequestBody Order order) {
        databaseService.addOrderRemote(order);
    }
}
