package com.codetend.datasource.multi.service.impl;


import com.codetend.datasource.multi.entity.User;
import com.codetend.datasource.multi.mapper.master.MasterUserMapper;
import com.codetend.datasource.multi.mapper.second.SecondUserMapper;
import com.codetend.datasource.multi.service.IMultiSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiSourceServiceImpl implements IMultiSourceService {
    @Autowired
    private MasterUserMapper masterMapper;
    @Autowired
    private SecondUserMapper secondMapper;
    @Override
    public List<User> getUsersMaster() {
        return masterMapper.getUsers();
    }

    @Override
    public List<User> getUsersSecond() {
        return secondMapper.getUsers();
    }
}
