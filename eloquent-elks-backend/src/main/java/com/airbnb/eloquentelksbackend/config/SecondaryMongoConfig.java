package com.airbnb.eloquentelksbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.airbnb.eloquentelksbackend.repository.secondary",
        mongoTemplateRef = "primaryMongoTemplate")
public class SecondaryMongoConfig {
}
