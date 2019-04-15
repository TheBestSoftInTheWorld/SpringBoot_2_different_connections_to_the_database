package com.example.springbootapp.configuration.persistence.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;

import java.util.Properties;
import javax.sql.DataSource;

@Profile("dev")
@PropertySource(value = "classpath:jpa/persistence-dev.properties")
@Service
public class DEVPersistenceContext implements PersistenceContext{

    private final ConfigurableEnvironment env;

    @Autowired
    public DEVPersistenceContext(ConfigurableEnvironment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {

        return DataSourceBuilder
                .create()
                .username(env.getProperty("app.datasource.username"))
                .password(env.getProperty("app.datasource.password"))
                .url(env.getProperty("app.datasource.url"))
                .driverClassName(env.getProperty("app.datasource.driverClassName"))
                .build();
    }

    public Properties additionalProperties() {

        Properties properties = new Properties();
        /*properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.jdbc.use_get_generated_keys", env.getProperty("hibernate.jdbc.use_get_generated_keys"));

        properties.setProperty("hibernate.timeout", env.getProperty("hibernate.timeout"));
        properties.setProperty("hibernate.default_schema", env.getProperty("hibernate.default_schema"));*/
        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", env.getProperty("hibernate.jdbc.lob.non_contextual_creation"));

        return properties;
    }

}
