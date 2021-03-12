package com.californiasense.tracker.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan(basePackages = "com.californiasense.tracker.model")
@EnableJpaRepositories(basePackages = "com.californiasense.tracker.repository")
@EnableTransactionManagement
public class DomainConfig {
}
