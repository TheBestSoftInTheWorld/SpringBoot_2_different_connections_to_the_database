package com.example.springbootapp.configuration.persistence.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

@Profile("dev-prod")
@PropertySource(value = "classpath:jpa/persistence-prod.properties")
@Service
public class JndiPersistenceContext implements PersistenceContext{

    private final ConfigurableEnvironment env;

    @Autowired
    public JndiPersistenceContext(ConfigurableEnvironment env) {

        this.env = env;
    }

    @Bean
    public DataSource dataSource() throws NamingException {

        return (DataSource) new JndiTemplate().lookup(env.getProperty("jdbc.url"));
    }

    public Properties additionalProperties() {

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
      // properties.setProperty("hibernate.jdbc.use_get_generated_keys", env.getProperty("hibernate.jdbc.use_get_generated_keys"));
      //  properties.setProperty("hibernate.search.default.directory_provider", env.getProperty("hibernate.search.default.directory_provider"));

        properties.setProperty("hibernate.timeout", env.getProperty("hibernate.timeout"));
       // properties.setProperty("wildfly.jpa.hibernate.search.module", env.getProperty("org.hibernate.search.orm:5.6.0.Beta1"));

        return properties;
    }
}
