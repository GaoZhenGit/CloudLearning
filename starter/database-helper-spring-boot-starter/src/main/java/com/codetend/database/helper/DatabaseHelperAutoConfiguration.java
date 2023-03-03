package com.codetend.database.helper;

import com.codetend.database.helper.core.LimitSqlInterceptor;
import com.codetend.database.helper.core.MapperChecker;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DatabaseHelperProperties.class)
@Slf4j
public class DatabaseHelperAutoConfiguration {
    @Autowired
    DatabaseHelperProperties databaseHelperProperties;
    @Bean
    public MapperChecker mapperChecker() {
        return new MapperChecker();
    }

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        log.info("loaded database helper interceptor");
        return configuration -> {
            configuration.addInterceptor(new LimitSqlInterceptor(databaseHelperProperties));
        };
    }
}