package com.codetend.datasource.multi.mapper.master;

import com.codetend.datasource.multi.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MasterUserMapper {
    List<User> getUsers();
}
