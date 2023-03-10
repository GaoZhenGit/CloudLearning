package com.codetend.service.database.service.impl;

import com.codetend.common.response.BizException;
import com.codetend.common.response.ResponseResult;
import com.codetend.database.helper.core.MapperChecker;
import com.codetend.service.database.config.SnowflakeDistributeId;
import com.codetend.service.database.entity.Order;
import com.codetend.service.database.entity.User;
import com.codetend.service.database.entity.UserAndOrder;
import com.codetend.service.database.mapper.OrderMapper;
import com.codetend.service.database.mapper.UserMapper;
import com.codetend.service.database.service.IDatabaseService;
import com.codetend.service.database.service.RemoteOrderService;
import com.codetend.service.database.service.RemoteUserService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
@Slf4j
public class DatabaseServiceImpl implements IDatabaseService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private MapperChecker mapperChecker;

    @Autowired
    private RemoteUserService remoteUserService;
    @Autowired
    private RemoteOrderService remoteOrderService;
    @Autowired
    private SnowflakeDistributeId snowflakeDistributeId;

    @Override
    public List<User> getUsers(int offset, int rows) {
        mapperChecker.checkMapper(userMapper);
        return userMapper.getUsers(offset, rows);
    }

    @Override
    public List<User> getUsersLimit() {
        return userMapper.getUsersLimit();
    }

    @Override
    public User getUser(long id) {
        return userMapper.getUser(id);
    }

    @Override
    @Transactional
    public void setUser(User user) {
        userMapper.setUser(user);
        if (user.name.contains("v")) {
            throw new BizException(-400, "invalid user name");
        }
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
        Integer ret = transactionTemplate.execute(action -> {
            orderMapper.setOrder(order);
            if (order.buyerId.startsWith("00")) {
                throw new BizException(-400, "invalid buyerId");
            }
            return 0;
        });
        log.info("setOrder finished");
    }

    @Override
    public void deleteOrder(long id) {
        orderMapper.deleteOrder(id);
    }

    @Override
    @GlobalTransactional
    public void addUserAndOrder(UserAndOrder userAndOrder) {
        log.info("begin tx");

        ResponseResult<?> result1 = remoteOrderService.addOrder(userAndOrder.order);
        if (result1.getCode() != 0) {
            log.info("order tx fail");
            throw new BizException(result1.getCode(), result1.getMsg());
        }

        ResponseResult<?> result2 = remoteUserService.addUser(userAndOrder.user);
        if (result2.getCode() != 0) {
            log.info("user tx fail");
            throw new BizException(result2.getCode(), result2.getMsg());
        }
        log.info("end tx");
    }

    @Override
    public void addUserRemote(User user) {
        user.uid = snowflakeDistributeId.nextId();
        userMapper.setUserFull(user);
        if (user.name.contains("err")) {
            throw new BizException(-502, "mock user tx err");
        }
    }

    @Override
    public void addOrderRemote(Order order) {
        order.oid = snowflakeDistributeId.nextId();
        orderMapper.setOrderFull(order);
    }
}
