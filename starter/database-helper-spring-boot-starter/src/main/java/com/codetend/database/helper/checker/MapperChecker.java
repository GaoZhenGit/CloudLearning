package com.codetend.database.helper.checker;

import com.codetend.database.helper.DatabaseHelperProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MapperChecker {
    @Autowired
    DatabaseHelperProperties properties;
    public void checkMapper(Object mapper) {
        log.info("check mapper:{}, level:{}", mapper.getClass().getName(), properties.level);
    }
}
