package com.codetend.database.helper;

import com.codetend.database.helper.checker.MapperChecker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DatabaseHelperProperties.class)
public class DatabaseHelperAutoConfiguration {
    @Bean
    public MapperChecker mapperChecker() {
        return new MapperChecker();
    }
}