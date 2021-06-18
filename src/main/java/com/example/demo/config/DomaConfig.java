package com.example.demo.config;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DomaConfig {

    @Bean()
    public DynamicRoutingDataSourceResolver dataSource() {
        DynamicRoutingDataSourceResolver resolver = new DynamicRoutingDataSourceResolver();

        //Load list tenant ở dây, sample này đang hardcode
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/tenant1?useSSL=false");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
        DataSource dataSource1 = dataSourceBuilder.build();

        DataSourceBuilder dataSourceBuilder2 = DataSourceBuilder.create();
        dataSourceBuilder2.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder2.url("jdbc:mysql://localhost:3306/tenant2?useSSL=false");
        dataSourceBuilder2.username("root");
        dataSourceBuilder2.password("root");
        DataSource dataSource2 = dataSourceBuilder2.build();

        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("dataSource1", dataSource1);
        dataSources.put("dataSource2", dataSource2);

        resolver.setTargetDataSources(dataSources);

        return resolver;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public Dialect dialect() {
        return new MysqlDialect();
    }

    @Bean
    public Config daoConfig() {
        return new Config() {
            @Override
            public Dialect getDialect() {
                return dialect();
            }

            @Override
            public DataSource getDataSource() {
                return new TransactionAwareDataSourceProxy(dataSource());
            }
        };
    }
}
