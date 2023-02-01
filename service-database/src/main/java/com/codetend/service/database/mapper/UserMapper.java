package com.codetend.service.database.mapper;

import com.codetend.service.database.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUsers();
    User getUser(@Param("uid") long uid);
    void setUser(User user);
    void deleteUser(@Param("uid") long uid);
}
