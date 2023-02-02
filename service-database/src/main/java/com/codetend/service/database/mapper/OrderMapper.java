package com.codetend.service.database.mapper;

import com.codetend.service.database.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getOrders();
    Order getOrder(@Param("oid") long uid);
    void setOrder(Order Order);
    void deleteOrder(@Param("oid") long uid);
}
