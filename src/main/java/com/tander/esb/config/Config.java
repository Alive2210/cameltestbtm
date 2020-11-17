package com.tander.esb.config;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;
import bitronix.tm.resource.jms.PoolingConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.jta.JtaTransactionManager;

import java.util.Properties;


@Configuration
@ComponentScan("com.tander.esb")
public class Config {


   /* @Bean(name = "btmConfig")
    public bitronix.tm.Configuration getConfig() {
        bitronix.tm.Configuration configuration = TransactionManagerServices.getConfiguration();
        configuration.setServerId("camel_test");
        return configuration;
    }

    @DependsOn("btmConfig")
    @Bean(name = "transactionManagerServices", destroyMethod = "shutdown")
    public BitronixTransactionManager getManagerServices() {
        return TransactionManagerServices.getTransactionManager();
    }

    @Autowired
    @Bean(name = "jtaTransactionManager")
    public JtaTransactionManager getJtaTransactionManager(BitronixTransactionManager transactionManagerServices) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(transactionManagerServices);
        jtaTransactionManager.setUserTransaction(transactionManagerServices);
        return jtaTransactionManager;

    }

    @DependsOn("jtaTransactionManager")
    @Bean(name = "activeMQXAConnectionFactory",initMethod = "init", destroyMethod = "close")
    public PoolingConnectionFactory getConnectionFactory() {
        PoolingConnectionFactory connectionFactory = new PoolingConnectionFactory();
        connectionFactory.setClassName("org.apache.activemq.ActiveMQXAConnectionFactory");
        Properties brokerURL = new Properties();
        brokerURL.setProperty("brokerURL", "failover:(tcp://localhost:61616)?jms.prefetchPolicy.queuePrefetch=1");
        connectionFactory.setDriverProperties(brokerURL);
        connectionFactory.setUniqueName("xaBrokerPool");
        connectionFactory.setMaxPoolSize(10);
        connectionFactory.setMinPoolSize(1);
        connectionFactory.setAllowLocalTransactions(true);
        return connectionFactory;
    }

    @Autowired
    @Bean(name = "jms")
    public JmsComponent getJmsComponent(JtaTransactionManager jtaTransactionManager, PoolingConnectionFactory activeMQXAConnectionFactory) {
        JmsComponent component = new JmsComponent();
        component.setTransacted(true);
        component.setTransactionManager(jtaTransactionManager);
        component.setConnectionFactory(activeMQXAConnectionFactory);
        return component;
    }
    @DependsOn("jtaTransactionManager")
    @Bean(name = "poolingDataSource", initMethod = "init",destroyMethod = "close")
    public PoolingDataSource getPoolingDataSource() {
        PoolingDataSource poolingDataSource = new PoolingDataSource();
        poolingDataSource.setClassName("org.postgresql.xa.PGXADataSource");
        poolingDataSource.setUniqueName("xaDataPool");
        poolingDataSource.setMaxPoolSize(5);
        poolingDataSource.setAutomaticEnlistingEnabled(false);
        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "postgres");
        properties.setProperty("serverName", "localhost");
        properties.setProperty("portNumber", "5432");
        properties.setProperty("databaseName", "jms");
        poolingDataSource.setDriverProperties(properties);
        poolingDataSource.setMinPoolSize(1);
        return poolingDataSource;
    }

    @Autowired
    @Bean(name = "sql")
    public SqlComponent getSqlComponent(PoolingDataSource poolingDataSource) {
        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setDataSource(poolingDataSource);
        return sqlComponent;
    }*/

}
