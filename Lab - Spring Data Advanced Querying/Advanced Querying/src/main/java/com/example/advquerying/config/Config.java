package com.example.advquerying.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
public class Config {

    private final Environment environment;

    public Config(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource createDataSource() {
        DriverManagerDataSource manager  = new DriverManagerDataSource();
        manager.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
        manager.setUrl(environment.getProperty("spring.datasource.url"));
        manager.setUsername(environment.getProperty("spring.datasource.username"));
        manager.setPassword(environment.getProperty("spring.datasource.password"));

        return manager;
    }
}
