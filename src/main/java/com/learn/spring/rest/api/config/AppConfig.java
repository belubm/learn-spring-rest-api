package com.learn.spring.rest.api.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.learn.spring.rest.api")
@EntityScan("com.learn.spring.rest.api.data.dao")
@EnableJpaRepositories("com.learn.spring.rest.api.repository")
public class AppConfig {
}
