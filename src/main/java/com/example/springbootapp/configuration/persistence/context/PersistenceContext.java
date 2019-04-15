package com.example.springbootapp.configuration.persistence.context;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

public interface PersistenceContext {
    DataSource dataSource() throws NamingException;

    Properties additionalProperties();
}
