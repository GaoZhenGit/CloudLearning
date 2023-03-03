package com.codetend.database.helper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Setter
@Getter
@ConfigurationProperties(prefix = "com.codetend.database-helper")
@RefreshScope
public class DatabaseHelperProperties {
    public String level;
}
