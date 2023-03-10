package com.codetend.service.database.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseSourceConfiguration {
//    @Bean
//    public DataSource dataSource(DruidDataSource dataSource) {
//        return new DataSourceProxy(dataSource);
//    }
    @Bean
    SnowflakeDistributeId snowflakeDistributeId() {
        return new SnowflakeDistributeId(31L, 31L);
    }
}
