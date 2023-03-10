package com.codetend.service.database.mapper;

import com.codetend.service.database.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getOrders(int offset, int rows);
    Order getOrder(@Param("oid") long uid);
    void setOrder(Order Order);
    void setOrderFull(Order order);
    void deleteOrder(@Param("oid") long uid);
}
