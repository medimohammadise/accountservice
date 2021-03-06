package com.booxware.test.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableJpaRepositories (basePackages = {"com.booxware.test.repository"})
@EnableTransactionManagement
public class PersistenceConfiguration {
    private static final Logger LOG = Logger.getLogger(PersistenceConfiguration.class);

    private EmbeddedDatabase embeddedDb;

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Value("${hibernate.default_schema}")
    private String hibernateSchema;

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());
        return jpaTransactionManager;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory
                = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(getDataSource());
        factory.setPackagesToScan("com.booxware.test.domain");
        factory.setPersistenceUnitName("boxware");
        factory.setJpaVendorAdapter(getJpaVendorAdapter());
        factory.setJpaProperties(getJpaProperties());
        factory.afterPropertiesSet();

        return factory;
    }
    private DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        embeddedDb = builder.setType(EmbeddedDatabaseType.H2)
                .addScript("initialdata/initialdata-load.sql")
                .build();
        return embeddedDb;
    }
    private JpaVendorAdapter getJpaVendorAdapter() {
        HibernateJpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
        adaptor.setDatabase(Database.H2);
        adaptor.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return adaptor;
    }
    private Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.default_schema", hibernateSchema);
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("flushMode", "COMMIT/AUTO");
       // ps.put("hibernate.enable_lazy_load_no_trans","true");
        return properties;
    }


}
