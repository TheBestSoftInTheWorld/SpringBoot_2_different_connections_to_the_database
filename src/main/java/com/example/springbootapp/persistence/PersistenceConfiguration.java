package com.example.springbootapp.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springbootapp.configuration.persistence.context.PersistenceContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.naming.NamingException;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import javax.persistence.EntityManagerFactory;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.example.springbootapp.configuration.persistence.context"})
@EnableJpaRepositories(basePackages = {"com.example.springbootapp.jpa"})
public class PersistenceConfiguration {

    final PersistenceContext persistenceContext;

    @Autowired
    public PersistenceConfiguration(PersistenceContext persistenceContext) {
        this.persistenceContext = persistenceContext;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(this.persistenceContext.dataSource());
        factory.setPackagesToScan("com.example.springbootapp.jpa.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);

        factory.setJpaProperties(this.persistenceContext.additionalProperties());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {

        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

        return new PersistenceExceptionTranslationPostProcessor();
    }

}
