package com.codetend.datasource.multi.mapper.second;

import com.codetend.datasource.multi.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SecondUserMapper {
    List<User> getUsers();
}
