package com.zdravko.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

//import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="${entitymanager.packagesToScan}")
public class DatabaseConfig {

    @Value("${db.driverClassName}")
    private String DB_DRIVER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.username}")
    private String DB_USERNAME;

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.show_sql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    /**
     * DataSource definition for database connection.
     * Settings are read from the application.properties file.
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }

    /**
     * Declare the JPA entity factory.
     *
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);

        //Vendor adapter
        //HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        JpaVendorAdapter vendorAdapter = jpaVendorAdapter();

        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        //Hibernate properties
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
        hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        entityManagerFactory.setJpaProperties(hibernateProperties);

        //EclipseLink properties
       /* Properties eclipseProperties = new Properties();
        eclipseProperties.setProperty("eclipselink.ddl-generation", "create-tables");
        eclipseProperties.setProperty("eclipselink.weaving", "false");
        eclipseProperties.setProperty("eclipselink.logging.level", "FINEST");
        eclipseProperties.setProperty("eclipselink.logging.parameters", "true");
        eclipseProperties.setProperty("eclipselink.target-server", "SunAS9");
        entityManagerFactory.setJpaProperties(eclipseProperties);*/

        return entityManagerFactory;
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.ORACLE);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(false);
        //vendorAdapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
        return vendorAdapter;

       /* EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.ORACLE);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        //vendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.SQLServerPlatform");
        return vendorAdapter;*/
    }

    /**
     * Declare the transaction manager.
     *
     * @return
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post-processor that adds
     an adviser to any bean that’s annotated with @Repository so that any platform-specific
     exceptions are caught and then rethrown as one of Spring’s unchecked data-access
     exceptions.
     * @return BeanPostProcessor
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

   /* @Bean
    public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }*/
}
