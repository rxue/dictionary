package org.springframework.mydictionary.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("org.springframework.mydictionary")
@EnableJpaRepositories(basePackages = "org.springframework.mydictionary.jparepository")
public class RootConfigurer {

}
