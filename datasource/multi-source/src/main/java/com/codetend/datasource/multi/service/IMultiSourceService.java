package com.codetend.datasource.multi.service;

import com.codetend.datasource.multi.entity.User;

import java.util.List;

public interface IMultiSourceService {
    List<User> getUsersMaster();
    List<User> getUsersSecond();
}
